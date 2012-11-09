package cz.cvut.fel.via.zboziforandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class OfferDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);        
        
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        if (savedInstanceState == null) {
        	String key = getIntent().getExtras().containsKey(OfferDetailFragment.OFFER_ID) ? OfferDetailFragment.OFFER_ID : OfferDetailFragment.PRODUCT_ID;        		        	
            Bundle arguments = new Bundle();
            arguments.putInt(key,
            		getIntent().getExtras().getInt(key));
            OfferDetailFragment fragment = new OfferDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.offer_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpTo(this, new Intent(this, StartupActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
