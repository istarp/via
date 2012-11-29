package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.client.items.Item;
import cz.cvut.fel.via.zboziforandroid.model.Database;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        	((TextView) rootView.findViewById(R.id.offer_detail)).setText("Selected Offer: \r\n" + mOffer.getPremiseName());
        }
        return rootView;
    }
}
