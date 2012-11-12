package cz.cvut.fel.via.zboziforandroid;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class OfferDetailActivity extends FragmentActivity {

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
        		getSupportFragmentManager().beginTransaction().add(R.id.offer_detail_container, fragment).commit();
        	}else{
        		this.setTitle(R.string.title_product_detail);
        		ProductDetailFragment fragment = new ProductDetailFragment();
        		fragment.setArguments(getIntent().getExtras());
        		getSupportFragmentManager().beginTransaction().add(R.id.offer_detail_container, fragment).commit();
        	}            
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
        	//Bundle b = new Bundle(getIntent().getExtras());
        	//Intent i = new Intent(this, OfferListActivity.class);
        	//i.putExtras(b);
            //NavUtils.navigateUpTo(this, i);
        	finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
