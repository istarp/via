package cz.cvut.fel.via.zboziforandroid.model;

import java.util.List;
import cz.cvut.fel.via.zboziforandroid.R;
import cz.cvut.fel.via.zboziforandroid.client.items.Item;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OfferListAdapter extends ArrayAdapter<Item> implements OnClickListener{
	
    private int resource;
    private LayoutInflater inflater;    
    private List<Item> offers;
    private Context context;    

	public OfferListAdapter(Context context, int textViewResourceId, List<Item> objects) {
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
        public ImageView button; 
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
            ImageView button = (ImageView) convertView.findViewById(R.id.goToShop); 
            
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
		
		Item offer = this.offers.get(position);
        holder.shopName.setText(offer.getPremiseName());
		holder.price.setText(preparePrice(offer.getVatPrice()));		
		holder.locality.setVisibility(View.GONE);
		holder.status.setTextColor(offer.getMinStockAvailability().equals("immediately") ? Color.parseColor("#518000") : Color.parseColor("#9E9E9E"));
		holder.status.setText(R.string.availability_default);
		if (offer.getMinStockAvailability().equals("immediately")){
			holder.status.setText(R.string.availability_immediately);
		}
		if (offer.getMinStockAvailability().equals("in_week")){
			holder.status.setText(R.string.availability_in_week);
		}
		if (offer.getMinStockAvailability().equals("all")){
			holder.status.setText(R.string.availability_all);
		}	
		holder.button.setOnClickListener(this);;
        holder.position = position;
          
        return convertView;
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Item getItem(int position) {
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
	
    private String preparePrice(String price){
    	String tmp = price.substring(0, price.length() - 3);
    	if (tmp.length() > 3){
    		tmp = tmp.substring(0, tmp.length() - 3) + " " + tmp.substring(tmp.length() - 3, tmp.length());
    	}
    	return tmp + " ";
    }	
        
}
