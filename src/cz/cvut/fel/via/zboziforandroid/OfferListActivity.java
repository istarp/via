package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.client.ViaClientHttp;
import cz.cvut.fel.via.zboziforandroid.client.items.ItemsResponse;
import cz.cvut.fel.via.zboziforandroid.client.product.ProductResponse;
import cz.cvut.fel.via.zboziforandroid.client.products.Products;
import cz.cvut.fel.via.zboziforandroid.model.Database;
import cz.cvut.fel.via.zboziforandroid.model.OfferListDialog;
import cz.cvut.fel.via.zboziforandroid.model.QueryDatabase;
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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.SearchView;
import android.widget.Toast;

public class OfferListActivity extends FragmentActivity implements OfferListDialog.NoticeDialogListener, OfferListFragment.Callbacks, SearchView.OnQueryTextListener, View.OnFocusChangeListener {
		
    private boolean mTwoPane;
    private SearchView mSearchView; 
    private Menu mMenu; 
    private Handler handler;
    private int id; 
    private boolean productReady = true;
    private boolean listReady = true;
    private Context context;
    private SharedPreferences settings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);        

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
        	ProgressFragment progressFragment = new ProgressFragment();
        	progressFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(R.id.offer_list_container, progressFragment).commit();            
        }
        
        this.id = getIntent().getExtras().getInt(ProductListFragment.PRODUCT_ID);
        this.handler = new Handler();        
        this.listReady = false;
        this.productReady = false;
        this.context = getApplicationContext();
        this.settings = getSharedPreferences(Database.settingsPreferences, MODE_PRIVATE);
        
        this.setComponents();
        this.loadProduct();
        this.loadItems();        
                
        if (findViewById(R.id.offer_detail_container) != null) {        	
            mTwoPane = true;
            if (savedInstanceState == null){
	        	BlankFragment blankFragment = new BlankFragment();
	        	blankFragment.setArguments(new Bundle());
	            getFragmentManager().beginTransaction().add(R.id.offer_detail_container, blankFragment).commit();
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
        startOfferDetail(id);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				//NavUtils.navigateUpTo(this, new Intent(this, ProductListActivity.class));
				finish();
				return (true);
			case R.id.action_filter_set:
	        	OfferListDialog dialog = new OfferListDialog();
	            dialog.show(getFragmentManager(), "OfferListDialog");         	        		
	    		return true;
			case R.id.action_sort:
				SharedPreferences.Editor prefEditor = settings.edit();
				if(settings.getBoolean(Database.itemListSorted, true)){
					item.setTitle(getResources().getString(R.string.sort_down));
					item.setIcon(getResources().getDrawable(R.drawable.ic_menu_sort_by_size_down));					
					prefEditor.putBoolean(Database.itemListSorted, false);			    	
				}else{
					item.setTitle(getResources().getString(R.string.sort_up));
					item.setIcon(getResources().getDrawable(R.drawable.ic_menu_sort_by_size_up));					
					prefEditor.putBoolean(Database.itemListSorted, true);			    	
				}	
				prefEditor.commit();
				this.refreshList();
				return (true);
		}
        return super.onOptionsItemSelected(item);
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
 
        this.mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_offer_list, menu);        
        MenuItem searchItem = menu.findItem(R.id.action_search);                      
        mSearchView = (SearchView) searchItem.getActionView();  
        mSearchView.setOnQueryTextListener(this);               
        mSearchView.setOnQueryTextFocusChangeListener(this);  
        mSearchView.setMaxWidth(getWindowManager().getDefaultDisplay().getWidth());
        
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);
        
        MenuItem sortItem = menu.findItem(R.id.action_sort);
        if(!settings.getBoolean(Database.itemListSorted, true)){
        	sortItem.setTitle(getResources().getString(R.string.sort_down));
        	sortItem.setIcon(getResources().getDrawable(R.drawable.ic_menu_sort_by_size_down));					
		}else{
			sortItem.setTitle(getResources().getString(R.string.sort_up));
			sortItem.setIcon(getResources().getDrawable(R.drawable.ic_menu_sort_by_size_up));					
		}
        
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
	
	private void startOfferDetail(int id){					
		Bundle arguments = new Bundle(getIntent().getExtras());				
		arguments.putInt(OfferDetailFragment.OFFER_ID, id);			
        if (mTwoPane) {        	
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();        	
    		ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
    		OfferDetailFragment fragment = new OfferDetailFragment();
            fragment.setArguments(arguments);
        	ft.replace(R.id.offer_detail_container, fragment, "fragment");        		
    		ft.commit();
        	
            //OfferDetailFragment fragment = new OfferDetailFragment();
            //fragment.setArguments(arguments);
            //getFragmentManager().beginTransaction()
            //        .replace(R.id.offer_detail_container, fragment)
            //        .commit();
        } else {        	
            Intent detailIntent = new Intent(this, OfferDetailActivity.class);
            detailIntent.putExtras(arguments);
            startActivity(detailIntent);           
        }				
	}
	
	private void startProductDetail(){				
		Bundle arguments = new Bundle(getIntent().getExtras());				
		arguments.remove(OfferDetailFragment.OFFER_ID);
        if (mTwoPane) {                        
            android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();        	
    		ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
    		ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(arguments);
        	ft.replace(R.id.offer_detail_container, fragment, "fragment");        		
    		ft.commit();
        	
            //ProductDetailFragment fragment = new ProductDetailFragment();
            //fragment.setArguments(arguments);
            //getFragmentManager().beginTransaction()
            //        .replace(R.id.offer_detail_container, fragment)
            //        .commit();
        } else {        	
            Intent detailIntent = new Intent(this, OfferDetailActivity.class);
            detailIntent.putExtras(arguments);
            startActivity(detailIntent);           
        }				
	}	
	
    @Override
    public boolean onSearchRequested() {
    	return true;
    }
    
    private void loadProduct(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            	ViaClientHttp c = new ViaClientHttp();	      	
		      	ProductResponse response = c.getProduct(id);            	
		      	if (response != null && response.getProductAttributes() != null){
			      	Database.fillProduct(response.getProductAttributes());
			      	productReady = true;		      	
	                handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                    	((ImageView) findViewById(R.id.productOverview_arrow)).setVisibility(View.VISIBLE);
	                        TextView productDescription = (TextView) findViewById(R.id.productOverview_description);                       
	                        productDescription.setText(Database.PRODUCT.getDescription());
	                    }
	                });
		      	}else{
	                handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                    	Toast.makeText(context, context.getResources().getString(R.string.connection_problem), Toast.LENGTH_LONG).show();
	                    	Database.clearProduct();
	                    }
	                });		      		
		      	}
            }
        };
        new Thread(runnable).start();    	
    }
    
    private void loadItems(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            	ViaClientHttp c = new ViaClientHttp();            	
		      	ItemsResponse response = c.getItems(id, 1, 
		      			settings.getInt(Database.itemLimit, 10),
		      			"", false, "", false, -1, false,
		      			settings.getBoolean(Database.itemAtStoreOnly, false));
		      	if (response != null && response.getItems() != null){
			      	Database.fillItems(response.getItems());
	                handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                        refreshList();
	                		if(Database.ITEMS.isEmpty())
	                			Toast.makeText(context, context.getResources().getString(R.string.no_results), Toast.LENGTH_LONG).show();
	                    }
	                });
		      	}else{
		      		handler.post(new Runnable() {
	                    @Override
	                    public void run() {
	                    	Toast.makeText(context, context.getResources().getString(R.string.connection_problem), Toast.LENGTH_LONG).show();
	                    	getFragmentManager().beginTransaction().replace(R.id.offer_list_container, new Fragment()).commit();
	                    	Database.clearItems();
	                    }
	                });
		      	}
		      	listReady = true;
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
    	ft.replace(R.id.offer_list_container, progressFragment, "progressFragment");        		
		ft.commit();
		loadItems();		
	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {		
	}
	
	private void setComponents(){
        TextView productName = (TextView) findViewById(R.id.productOverview_name);
        ImageView productImage = (ImageView) findViewById(R.id.productOverview_image);
        Products products = Database.PRODUCTS.get(getIntent().getExtras().getInt(ProductListFragment.PRODUCT_LIST_ID));
        productName.setText(products.getProductName());
        if (products.getImg() == null){
        	productImage.setImageDrawable(getResources().getDrawable(R.drawable.no_image));
        }else{
        	productImage.setImageBitmap(products.getImg());
        }        
        LinearLayout productOverview = (LinearLayout) findViewById(R.id.productOverview);                        
        productOverview.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				if(listReady && productReady){
					startProductDetail();
				}
			}
		});		
        ((ImageView) findViewById(R.id.productOverview_arrow)).setVisibility(View.GONE);
	}
	
	private void refreshList(){
		android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();        	
		ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);                		
		OfferListFragment offerListFragment = new OfferListFragment(); ;
		offerListFragment.setArguments(getIntent().getExtras());                                        		
    	ft.replace(R.id.offer_list_container, offerListFragment, "offerListFragment");        		
		ft.commit();
	}	
	

}
