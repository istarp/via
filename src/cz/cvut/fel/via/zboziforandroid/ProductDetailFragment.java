package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.Database;
import cz.cvut.fel.via.zboziforandroid.model.Product;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProductDetailFragment extends Fragment {
    
    public static final String PRODUCT_ID = "product_id";    
    private Product mProduct;

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(PRODUCT_ID)) {
            mProduct = Database.PRODUCTS.get(getArguments().getInt(PRODUCT_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);
        if (mProduct != null){        	
        	((TextView) rootView.findViewById(R.id.product_detail)).setText("Selected Product: \r\n" + mProduct.getProductName());
        }
        return rootView;
    }
}
