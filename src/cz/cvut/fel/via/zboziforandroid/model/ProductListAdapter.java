package cz.cvut.fel.via.zboziforandroid.model;

import java.util.List;
import cz.cvut.fel.via.zboziforandroid.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListAdapter extends ArrayAdapter<Product>{
	
    private int resource;
    private LayoutInflater inflater;    
    private List<Product> products;
    private Context context;

	public ProductListAdapter(Context context, int textViewResourceId, List<Product> objects) {
		super(context, textViewResourceId, objects);
        this.resource = textViewResourceId;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
        this.products = objects;
        this.context = context;
	}	
	
    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {  
    	
        convertView = inflater.inflate(resource, null);          
        Product product = this.products.get(position);
        
        TextView productName = (TextView) convertView.findViewById(R.id.product_row_name);          
        ImageView productImage = (ImageView) convertView.findViewById(R.id.product_row_image);          
        
        productName.setText(product.getProductName());          
        //productImage.setImageDrawable(product.getImage());    	          
        productImage.setImageDrawable(context.getResources().getDrawable(R.drawable.example));
                             
        return convertView;
    }          
}
