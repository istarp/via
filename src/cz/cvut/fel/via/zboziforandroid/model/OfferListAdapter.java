package cz.cvut.fel.via.zboziforandroid.model;

import java.util.List;
import cz.cvut.fel.via.zboziforandroid.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class OfferListAdapter extends ArrayAdapter<Offer> implements OnClickListener{
	
    private int resource;
    private LayoutInflater inflater;    
    private List<Offer> offers;
    private Context context;    

	public OfferListAdapter(Context context, int textViewResourceId, List<Offer> objects) {
		super(context, textViewResourceId, objects);
        this.resource = textViewResourceId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        this.offers = objects;
        this.context = context;
	}
	
	private static class ViewHolder {  
        public TextView shopName;          
        public TextView price;
        public TextView status;
        public TextView locality; 
        public Button button; 
        public int position;
    }
	
    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {
    	    
		ViewHolder holder;  
		
		if (convertView == null) {  
			convertView = inflater.inflate(resource, null); 
  
            TextView shopName = (TextView) convertView.findViewById(R.id.shopName);          
            TextView price = (TextView) convertView.findViewById(R.id.price);
            TextView status = (TextView) convertView.findViewById(R.id.status);
            TextView locality = (TextView) convertView.findViewById(R.id.locality); 
            Button button = (Button) convertView.findViewById(R.id.goToShop); 
            
            holder = new ViewHolder();  
            holder.shopName = shopName;  
            holder.price = price;  
            holder.status = status; 
            holder.locality = locality;
            holder.button = button;            
            
            convertView.setTag(holder);
            
        } else {  
            holder = (ViewHolder) convertView.getTag();  
        }
		
		Offer offer = this.offers.get(position);
        holder.shopName.setText(offer.getPremiseName());
		holder.price.setText(offer.getVatPrice() + " " + getContext().getResources().getString(R.string.currency));
		holder.status.setText(offer.getStockAvailability() == 0 ? R.string.in_store : R.string.out_store);
		holder.locality.setVisibility(View.GONE);
		holder.status.setTextColor(offer.getStockAvailability() == 0 ? Color.parseColor("#21CE2C") : Color.parseColor("#9FA0A5"));
		holder.button.setOnClickListener(this);;
        holder.position = position;
          
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

	@Override
	public void onClick(View v) {
		ViewHolder o = (ViewHolder) ((View)v.getParent()).getTag();
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(offers.get(o.position).getURL()));
		context.startActivity(browserIntent);			
	}
        
}
