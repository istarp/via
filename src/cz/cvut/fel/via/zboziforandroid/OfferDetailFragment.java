package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.Database;
import cz.cvut.fel.via.zboziforandroid.model.Offer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OfferDetailFragment extends Fragment {

    public static final String OFFER_ID = "offer_id";
    public static final String PRODUCT_ID = "product_id";
    private Offer mOffer;

    public OfferDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(OFFER_ID)) {
            mOffer = Database.PRODUCTS.get(0).getOffers().get(getArguments().getInt(OFFER_ID));
        }
        else{
        	mOffer = null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_offer_detail, container, false);    	
        if (mOffer != null) {
            ((TextView) rootView.findViewById(R.id.offer_detail)).setText("Selected Offer: \r\n" + mOffer.getPremiseName());
        }else{
        	((TextView) rootView.findViewById(R.id.offer_detail)).setText("Selected product detail");
        }
        return rootView;
    }
}
