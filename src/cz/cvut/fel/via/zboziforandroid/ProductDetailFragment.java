package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.client.product.ProductAttributes;
import cz.cvut.fel.via.zboziforandroid.model.Database;
import android.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailFragment extends Fragment {
        
    private ProductAttributes mProduct;

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           mProduct = Database.PRODUCT;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        if (mProduct != null){
        	setComponents(rootView);
        }else{
        	Toast.makeText(getActivity(), getResources().getString(R.string.product_detail_blank), Toast.LENGTH_LONG).show();
        }
        return rootView;
    }
    
    private void setComponents(View rootView){
    	if(mProduct.getBigImage() != null)
    		((ImageView) rootView.findViewById(R.id.prodcut_detail_image)).setImageBitmap(mProduct.getBigImage());
    	else
    		if (mProduct.getSmallImage() != null)
    			((ImageView) rootView.findViewById(R.id.prodcut_detail_image)).setImageBitmap(mProduct.getSmallImage());
    		else
    			((ImageView) rootView.findViewById(R.id.prodcut_detail_image)).setImageDrawable(getResources().getDrawable(R.drawable.no_image));    	
    	((TextView) rootView.findViewById(R.id.prodcut_detail_name)).setText(mProduct.getProductName());
    	((TextView) rootView.findViewById(R.id.prodcut_detail_name)).setMovementMethod(new ScrollingMovementMethod());
    	((TextView) rootView.findViewById(R.id.prodcut_detail_price_from)).setText(preparePrice(mProduct.getMinPrice()));
    	((TextView) rootView.findViewById(R.id.prodcut_detail_price_to)).setText(preparePrice(mProduct.getMaxPrice()));
    	((TextView) rootView.findViewById(R.id.prodcut_detail_description)).setText(mProduct.getDescription());
    	((TextView) rootView.findViewById(R.id.prodcut_detail_description)).setMovementMethod(new ScrollingMovementMethod());
    }
    
    private String preparePrice(String price){
    	String tmp = price.substring(0, price.length() - 2);
    	if (tmp.length() > 3){
    		tmp = tmp.substring(0, tmp.length() - 3) + " " + tmp.substring(tmp.length() - 3, tmp.length());
    	}
    	return " " + tmp + " ";
    }    
}
