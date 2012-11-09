package cz.cvut.fel.via.zboziforandroid.model;

import java.util.List;
import cz.cvut.fel.via.zboziforandroid.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class OfferListAdapter extends ArrayAdapter<Offer>{
	
    private int resource;
    private LayoutInflater inflater;    
    private List<Offer> offers;

	public OfferListAdapter(Context context, int textViewResourceId, List<Offer> objects) {
		super(context, textViewResourceId, objects);
        this.resource = textViewResourceId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        this.offers = objects;
	}
	
    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {          
          convertView = inflater.inflate(resource, null);          
          Offer offer = this.offers.get(position);
          
          TextView shopName = (TextView) convertView.findViewById(R.id.shopName);          
          TextView price = (TextView) convertView.findViewById(R.id.price);
          TextView status = (TextView) convertView.findViewById(R.id.status);
          TextView locality = (TextView) convertView.findViewById(R.id.locality);          
          
          shopName.setText(offer.getPremiseName());          
          price.setText(offer.getVatPrice() + " " + getContext().getResources().getString(R.string.currency));
          status.setText(offer.getStockAvailability() == 0 ? R.string.in_store : R.string.out_store);
          
          locality.setVisibility(View.GONE);
          status.setTextColor(offer.getStockAvailability() == 0 ? Color.parseColor("#21CE2C") : Color.parseColor("#9FA0A5"));
          
          return convertView;
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Offer getItem(int position) {
        return this.offers.get(position);
    }

    @Override
    public int getCount() {
        if (this.offers == null)
            return 0;
        return this.offers.size();
    }
        
}
