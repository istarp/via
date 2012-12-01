package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.client.items.Item;
import cz.cvut.fel.via.zboziforandroid.model.Database;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OfferDetailFragment extends Fragment {

    public static final String OFFER_ID = "offer_id";
    private Item mOffer;    

    public OfferDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        if (getArguments().containsKey(OFFER_ID)) {
        	mOffer = Database.ITEMS.get(getArguments().getInt(OFFER_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offer_detail, container, false);
        if (mOffer != null){        	        	
        	setComponents(rootView);    		
	    }else{
	    	Toast.makeText(getActivity(), getResources().getString(R.string.offer_detail_blank), Toast.LENGTH_LONG).show();
	    }
        return rootView;
    }
    
    private String preparePrice(String price){
    	String tmp = price.substring(0, price.length() - 3);
    	if (tmp.length() > 3){
    		tmp = tmp.substring(0, tmp.length() - 3) + " " + tmp.substring(tmp.length() - 3, tmp.length());
    	}
    	return tmp + " ";
    }
    
    private void setComponents(View rootView){
    	((TextView) rootView.findViewById(R.id.offer_detail_shopName)).setText(mOffer.getPremiseName());
    	((TextView) rootView.findViewById(R.id.offer_detail_price)).setText(preparePrice(mOffer.getVatPrice()));
    	((TextView) rootView.findViewById(R.id.offer_detail_status)).setTextColor(mOffer.getMinStockAvailability().equals("immediately") ? Color.parseColor("#518000") : Color.parseColor("#9E9E9E"));
    	((TextView) rootView.findViewById(R.id.offer_detail_status)).setText(R.string.availability_default);
		if (mOffer.getMinStockAvailability().equals("immediately")){
			((TextView) rootView.findViewById(R.id.offer_detail_status)).setText(R.string.availability_immediately);
		}
		if (mOffer.getMinStockAvailability().equals("in_week")){
			((TextView) rootView.findViewById(R.id.offer_detail_status)).setText(R.string.availability_in_week);
		}
		if (mOffer.getMinStockAvailability().equals("all")){
			((TextView) rootView.findViewById(R.id.offer_detail_status)).setText(R.string.availability_all);
		}
		((TextView) rootView.findViewById(R.id.offer_detail_depots)).setText(mOffer.getDepotsStrings());
		((TextView) rootView.findViewById(R.id.offer_detail_stores)).setText(mOffer.getStoresStrings());
		((TextView) rootView.findViewById(R.id.offer_detail_depots)).setMovementMethod(new ScrollingMovementMethod());
		((TextView) rootView.findViewById(R.id.offer_detail_stores)).setMovementMethod(new ScrollingMovementMethod());
		((ImageView) rootView.findViewById(R.id.offer_detail_goToShop)).setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mOffer.getURL()));
				getActivity().startActivity(browserIntent);					
			}
		});	
    }
}
