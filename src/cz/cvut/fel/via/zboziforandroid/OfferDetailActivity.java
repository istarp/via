package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.QueryDatabase;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class OfferDetailActivity extends FragmentActivity implements SearchView.OnQueryTextListener, View.OnFocusChangeListener{

	private SearchView mSearchView; 
    private Menu mMenu; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);        
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
        	if (getIntent().getExtras().containsKey(OfferDetailFragment.OFFER_ID)){
        		this.setTitle(R.string.title_offer_detail);
        		OfferDetailFragment fragment = new OfferDetailFragment();
        		fragment.setArguments(getIntent().getExtras());
        		getFragmentManager().beginTransaction().add(R.id.offer_detail_container, fragment).commit();
        	}else{
        		this.setTitle(R.string.title_product_detail);
        		ProductDetailFragment fragment = new ProductDetailFragment();
        		fragment.setArguments(getIntent().getExtras());
        		getFragmentManager().beginTransaction().add(R.id.offer_detail_container, fragment).commit();
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
    public boolean onOptionsItemSelected(MenuItem item) {
    	
        switch (item.getItemId()) {
        	case R.id.action_search:
        		onSearchRequested();
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
        inflater.inflate(R.menu.basic_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);               
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

}
