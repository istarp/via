package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.client.ViaClientHttp;
import cz.cvut.fel.via.zboziforandroid.client.products.ProductsResponse;
import cz.cvut.fel.via.zboziforandroid.model.Const;
import cz.cvut.fel.via.zboziforandroid.model.Database;
import cz.cvut.fel.via.zboziforandroid.model.QueryDatabase;
import cz.cvut.fel.via.zboziforandroid.model.ProductListDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class ProductListActivity extends FragmentActivity implements ProductListFragment.Callbacks, SearchView.OnQueryTextListener, View.OnFocusChangeListener, ProductListDialog.NoticeDialogListener {
	
    private SearchView mSearchView; 
    private Menu mMenu; 
    private Handler handler;
    private String searchedString = "";
    private Context context;
    
    public static String SEARCHED_STRING = "searched_string";    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);        

        getActionBar().setDisplayHomeAsUpEnabled(true);                                              
        
        if (savedInstanceState == null){    	     
        	        	
        	ProgressFragment progressFragment = new ProgressFragment();
        	progressFragment.setArguments(new Bundle());
            getFragmentManager().beginTransaction().add(R.id.product_list_container, progressFragment).commit();                                    
            
            this.searchedString = getIntent().getExtras().getString(SEARCHED_STRING);
            this.handler = new Handler();
            this.context = getApplicationContext();
            this.loadProducts();            
            
	        if (findViewById(R.id.product_detail_container) != null) {
	        	BlankFragment blankFragment = new BlankFragment();	           
	        	blankFragment.setArguments(new Bundle());
	            getFragmentManager().beginTransaction().add(R.id.product_detail_container, blankFragment).commit();
	        }        
        }
        
        handleIntent(getIntent());
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @SuppressWarnings("deprecation")
	private void handleIntent(Intent intent) {    	
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {                    	
            Uri uri = intent.getData();
            Cursor cursor = managedQuery(uri, null, null, null, null);
            if (cursor != null) {            	
                cursor.moveToFirst();
                int wIndex = cursor.getColumnIndexOrThrow(QueryDatabase.KEY_WORD);
                Toast.makeText(getApplicationContext(), cursor.getString(wIndex), Toast.LENGTH_SHORT).show();                          	
            }
        }
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
        }
    }    

    @Override
    public void onItemSelected(int id) {     	
        Intent detailIntent = new Intent(this, OfferListActivity.class);               
        detailIntent.putExtra(ProductListFragment.PRODUCT_LIST_ID, id);
        detailIntent.putExtra(ProductListFragment.PRODUCT_ID, Database.PRODUCTS.get(id).getId());
        startActivity(detailIntent);           	
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
	        case R.id.action_filter_set:
	        	ProductListDialog dialog = new ProductListDialog();
	            dialog.show(getFragmentManager(), "ProductListDialog");         	        		
	    		return true;       		
        	case android.R.id.home:
        		finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_product_list, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);                      
        mSearchView = (SearchView) searchItem.getActionView();  
        mSearchView.setOnQueryTextListener(this);               
        mSearchView.setOnQueryTextFocusChangeListener(this);
        mSearchView.setMaxWidth(getWindowManager().getDefaultDisplay().getWidth());
                
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);        
      
        return true;
    }
     
	@Override
	public boolean onQueryTextChange(String arg0) {		
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {			
		collapseSearchMenu();
		return false;
	}
	
	@Override
	public void onResume() {
		if(mMenu != null){
			collapseSearchMenu();
		}
		super.onResume();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if(!hasFocus){
			collapseSearchMenu();
		}
		
	}	
	
	private void collapseSearchMenu(){
		MenuItem searchItem = mMenu.findItem(R.id.action_search);
		searchItem.collapseActionView();
	}
	
    @Override
    public boolean onSearchRequested() {
    	return true;
    }
    
    private void loadProducts(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            	ViaClientHttp c = new ViaClientHttp();
            	SharedPreferences settings = getSharedPreferences(Database.settingsPreferences, MODE_PRIVATE);  
		      	ProductsResponse response = c.getProducts(searchedString, 1, 
		      			settings.getInt(Database.productLimit, 10), 
		      			Const.PRODUCT_CRITERIONS[settings.getInt(Database.productCriterion, 0)], 
		      			Const.PRODUCT_DIRECTION[settings.getInt(Database.productDirection, 0)], 
		      			settings.getInt(Database.productMinPrice, 0), 
		      			settings.getInt(Database.productMaxPrice, -1));            	
		      	if (response != null && response.getProducts() != null){
			      	Database.fillProducts(response.getProducts());
	                handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();        	
	                		ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
	                		ProductListFragment productListFragment = new ProductListFragment();
	                    	productListFragment.setArguments(new Bundle());
	                    	ft.replace(R.id.product_list_container, productListFragment, "productListFragment");        		
	                		ft.commit();
	                    }
	                });
		      	}else{
		      		handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                    	Toast.makeText(context, context.getResources().getString(R.string.connection_problem), Toast.LENGTH_LONG).show();
	                    	getFragmentManager().beginTransaction().replace(R.id.product_list_container, new Fragment()).commit();
	                    	Database.clearProducts();
	                    }
	                });
		      	}
            }
        };
        new Thread(runnable).start();    	
    }

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();        	
		ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
		ProgressFragment progressFragment = new ProgressFragment();
		progressFragment.setArguments(new Bundle());
    	ft.replace(R.id.product_list_container, progressFragment, "progressFragment");        		
		ft.commit();
		loadProducts();
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {				
	}

}
