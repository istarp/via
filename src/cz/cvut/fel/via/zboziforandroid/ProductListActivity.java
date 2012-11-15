package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.QuerryDatabase;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

public class ProductListActivity extends FragmentActivity implements ProductListFragment.Callbacks, SearchView.OnQueryTextListener, View.OnFocusChangeListener {
	
    private SearchView mSearchView; 
    private Menu mMenu;    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);        

        getActionBar().setDisplayHomeAsUpEnabled(true);                                              
        
        if (savedInstanceState == null){
	        if (findViewById(R.id.product_detail_container) != null) {
	            ((ProductListFragment) getSupportFragmentManager()
	                    .findFragmentById(R.id.product_list))
	                    .setActivateOnItemClick(true);
	        	OfferDetailFragment offerDetailFragment = new OfferDetailFragment();
	        	offerDetailFragment.setArguments(new Bundle());
	            getSupportFragmentManager().beginTransaction().add(R.id.product_detail_container, offerDetailFragment).commit();
	        }        
        }
        
    }

    @Override
    public void onItemSelected(int id) {       	
        Intent detailIntent = new Intent(this, OfferListActivity.class);
        detailIntent.putExtra(ProductListFragment.PRODUCT_LIST_ID, id);
        startActivity(detailIntent);           
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {        	
            //NavUtils.navigateUpTo(this, new Intent(this, StartupActivity.class));
        	finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
 
        this.mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu, menu);        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);               
        mSearchView = (SearchView) searchItem.getActionView();  
        mSearchView.setOnQueryTextListener(this);               
        mSearchView.setOnQueryTextFocusChangeListener(this);
        
        //String[] from = {"text"};
        
        //String[] from = {SearchManager.SUGGEST_COLUMN_TEXT_1};
        //int[] to = {R.id.action_search};
        //CursorAdapter cursor = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, QuerryDatabase.CURSOR, from, to, 1);
        //mSearchView.setSuggestionsAdapter(cursor);        
                
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
	
	

}
