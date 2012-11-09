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

	public static final String SEARCHED_STRING = "searched_string";
	
    private boolean mTwoPane;
    private SearchView mSearchView; 
    private Menu menu;    

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_list);        

        getActionBar().setDisplayHomeAsUpEnabled(true);                       
        
        TextView productName = (TextView) findViewById(R.id.productOverview_name);
        TextView productDescription = (TextView) findViewById(R.id.productOverview_description);
        ImageView productImage = (ImageView) findViewById(R.id.productOverview_image);
        LinearLayout productOverview = (LinearLayout) findViewById(R.id.productOverview);
        
        Product product = Database.PRODUCTS.get(0);
        productName.setText(product.getProductName());
        productDescription.setText(product.getDescription());
        //productImage.setImageDrawable(product.getImage());
        productImage.setImageDrawable(getResources().getDrawable(R.drawable.example));
        
        productOverview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startDetail(22, false);				
			}
		});
                               
        if (findViewById(R.id.offer_detail_container) != null) {
            mTwoPane = true;
            ((OfferListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.offer_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(int id) {
        startDetail(id, true);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {        	
            NavUtils.navigateUpTo(this, new Intent(this, StartupActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
 
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.searchview_in_menu, menu);        
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
		if(menu != null){
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
		MenuItem searchItem = menu.findItem(R.id.action_search);
		searchItem.collapseActionView();
	}
	
	private void startDetail(int id, boolean idOffer){
		
		String key = idOffer ? OfferDetailFragment.OFFER_ID : OfferDetailFragment.PRODUCT_ID;
		
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putInt(key, id);
            OfferDetailFragment fragment = new OfferDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.offer_detail_container, fragment)
                    .commit();
        } else {        	
            Intent detailIntent = new Intent(this, OfferDetailActivity.class);
            detailIntent.putExtra(key, id);
            startActivity(detailIntent);           
        }		
		
	}
	

}
