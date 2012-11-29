package cz.cvut.fel.via.zboziforandroid.model;

import java.util.List;
import cz.cvut.fel.via.zboziforandroid.R;
import cz.cvut.fel.via.zboziforandroid.client.products.Products;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListAdapter extends ArrayAdapter<Products>{
	
    private int resource;
    private LayoutInflater inflater;    
    private List<Products> products;        

	public ProductListAdapter(Context context, int textViewResourceId, List<Products> objects) {
		super(context, textViewResourceId, objects);
        this.resource = textViewResourceId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        this.products = objects;       
	}	
	
	private static class ViewHolder {  
        public TextView productName;          
        public ImageView productImage; 
    }	
	
    @Override
    public View getView (int position, View convertView, ViewGroup parent ) { 
    	
		ViewHolder holder;  
		
		if (convertView == null) {  
			convertView = inflater.inflate(resource, null); 
  
	        TextView productName = (TextView) convertView.findViewById(R.id.product_row_name);          
	        ImageView productImage = (ImageView) convertView.findViewById(R.id.product_row_image);
            
            holder = new ViewHolder();  
            holder.productName = productName;  
            holder.productImage = productImage;             
            
            convertView.setTag(holder);
            
        } else {  
            holder = (ViewHolder) convertView.getTag();  
        }
		
		Products product = this.products.get(position);
        holder.productName.setText(product.getProductName());
		holder.productImage.setImageBitmap(product.getImg()); 
          
        return convertView;
    	
    	/*
        convertView = inflater.inflate(resource, null);          
        Products product = this.products.get(position);
        
        TextView productName = (TextView) convertView.findViewById(R.id.product_row_name);          
        ImageView productImage = (ImageView) convertView.findViewById(R.id.product_row_image);          
        
        productName.setText(product.getProductName());          
        productImage.setImageBitmap(product.getImg());    	                         
                             
        return convertView;
        */
    }          
}
