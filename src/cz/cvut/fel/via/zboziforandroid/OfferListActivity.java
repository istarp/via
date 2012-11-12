package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.Database;
import cz.cvut.fel.via.zboziforandroid.model.Product;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.SearchView;

public class OfferListActivity extends FragmentActivity implements OfferListFragment.Callbacks, SearchView.OnQueryTextListener, View.OnFocusChangeListener {
		
    private boolean mTwoPane;
    private SearchView mSearchView; 
    private Menu mMenu; 
    private boolean sorted = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);        

        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
        	OfferListFragment offerListFragment = new OfferListFragment();
            offerListFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.offer_list_container, offerListFragment).commit();            
        }
        
        TextView productName = (TextView) findViewById(R.id.productOverview_name);
        TextView productDescription = (TextView) findViewById(R.id.productOverview_description);
        ImageView productImage = (ImageView) findViewById(R.id.productOverview_image);
        LinearLayout productOverview = (LinearLayout) findViewById(R.id.productOverview);
        
        Product product = Database.PRODUCTS.get(getIntent().getExtras().getInt(ProductListFragment.PRODUCT_LIST_ID));
        productName.setText(product.getProductName());
        productDescription.setText(product.getDescription());
        //productImage.setImageDrawable(product.getImage());
        productImage.setImageDrawable(getResources().getDrawable(R.drawable.example));
        
        productOverview.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				startProductDetail(getIntent().getExtras().getInt(ProductListFragment.PRODUCT_LIST_ID));								
			}
		}); 
                
        if (findViewById(R.id.offer_detail_container) != null) {        	
            mTwoPane = true;
            if (savedInstanceState == null){
	        	OfferDetailFragment offerDetailFragment = new OfferDetailFragment();
	        	offerDetailFragment.setArguments(new Bundle());
	            getSupportFragmentManager().beginTransaction().add(R.id.offer_detail_container, offerDetailFragment).commit();
            }
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
				NavUtils.navigateUpTo(this, new Intent(this, StartupActivity.class));
				return (true);
			case R.id.action_sort:			
	        	OfferListFragment offerListFragment = new OfferListFragment();
	        	Bundle b = getIntent().getExtras();
				if(this.sorted){
					item.setTitle(getResources().getString(R.string.sort_down));
					b.putString(OfferListFragment.RESORT, "");
					this.sorted = false;
				}else{
					item.setTitle(getResources().getString(R.string.sort_up));					
					b.remove(OfferListFragment.RESORT);
					this.sorted = true;
				}	        	
	            offerListFragment.setArguments(b);
	            getSupportFragmentManager().beginTransaction().add(R.id.offer_list_container, offerListFragment).commit();;
				return (true);
		}
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
 
        this.mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu_sort, menu);        
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);               
        mSearchView = (SearchView) searchItem.getActionView();  
        mSearchView.setOnQueryTextListener(this);               
        mSearchView.setOnQueryTextFocusChangeListener(this);        	        
        
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
		Bundle arguments = new Bundle();		
		arguments.putInt(OfferDetailFragment.PRODUCT_ID, getIntent().getExtras().getInt(ProductListFragment.PRODUCT_LIST_ID));
		arguments.putInt(OfferDetailFragment.OFFER_ID, id);		
        if (mTwoPane) {                        
            OfferDetailFragment fragment = new OfferDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.offer_detail_container, fragment)
                    .commit();
        } else {        	
            Intent detailIntent = new Intent(this, OfferDetailActivity.class);
            detailIntent.putExtras(arguments);
            startActivity(detailIntent);           
        }				
	}
	
	private void startProductDetail(int id){					
		Bundle arguments = new Bundle();		
		arguments.putInt(OfferDetailFragment.PRODUCT_ID, id);		
        if (mTwoPane) {                        
            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.offer_detail_container, fragment)
                    .commit();
        } else {        	
            Intent detailIntent = new Intent(this, OfferDetailActivity.class);
            detailIntent.putExtras(arguments);
            startActivity(detailIntent);           
        }				
	}	
	

}
