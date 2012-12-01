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
        	if(mProduct.getBigImage() != null)
        		((ImageView) rootView.findViewById(R.id.prodcut_detail_image)).setImageBitmap(mProduct.getBigImage());
        	else
        		if (mProduct.getSmallImage() != null)
        			((ImageView) rootView.findViewById(R.id.prodcut_detail_image)).setImageBitmap(mProduct.getSmallImage());
        		else
        			((ImageView) rootView.findViewById(R.id.prodcut_detail_image)).setImageDrawable(getResources().getDrawable(R.drawable.no_image));
        	
        	((TextView) rootView.findViewById(R.id.prodcut_detail_name)).setText(mProduct.getProductName());
        	((TextView) rootView.findViewById(R.id.prodcut_detail_price_from)).setText(" " + mProduct.getMinPrice().substring(0, mProduct.getMinPrice().length() - 2) + " ");
        	((TextView) rootView.findViewById(R.id.prodcut_detail_price_to)).setText(" " + mProduct.getMaxPrice().substring(0, mProduct.getMaxPrice().length() - 2) + " ");
        	((TextView) rootView.findViewById(R.id.prodcut_detail_description)).setText(mProduct.getDescription());
        	((TextView) rootView.findViewById(R.id.prodcut_detail_description)).setMovementMethod(new ScrollingMovementMethod());
        }else{
        	Toast.makeText(getActivity(), getResources().getString(R.string.product_detail_blank), Toast.LENGTH_LONG).show();
        }
        return rootView;
    }
}
