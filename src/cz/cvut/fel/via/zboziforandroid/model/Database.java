package cz.cvut.fel.via.zboziforandroid.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    public static List<Product> PRODUCTS = new ArrayList<Product>();    

    static {
    	Product p = new Product();
    	p.setId(1);
    	p.setProductName("HTC Sensation");
    	p.setProductNameExt("Delsi HTC Sensation");
    	p.setDescription("neco neco neco neco");
    	p.setMaxPrice(6500);
    	p.setMinPrice(4500);
    	
    	Map<Integer, Offer> o = new HashMap<Integer, Offer>();    	
    	o.put(2, new Offer(2, "Mall.cz", "5500", -1));
    	o.put(1, new Offer(1, "Alza.cz", "5000", 0));
    	o.put(3, new Offer(3, "czc.cz", "6000", 0));
    	
    	p.setOffers(o);
        addItem(p);
    }

    private static void addItem(Product item) {
    	PRODUCTS.add(item);    	
    }
        
}
