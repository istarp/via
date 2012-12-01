package cz.cvut.fel.via.zboziforandroid.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cz.cvut.fel.via.zboziforandroid.client.items.Item;
import cz.cvut.fel.via.zboziforandroid.client.product.ProductAttributes;
import cz.cvut.fel.via.zboziforandroid.client.products.Products;

public class Database {
	
	public static String settingsPreferences = "settings_preferences";
	public static String productDirection = "product_direction";
	public static String productLimit = "product_limit";
	public static String productCriterion = "product_criterion";
	public static String productMinPrice = "product_min_price";
	public static String productMaxPrice = "product_max_price";	
	public static String itemLimit = "item_limit";
	public static String itemAtStoreOnly = "item_at_store_only";	
	
	public static List<Products> PRODUCTS = new ArrayList<Products>();
	public static List<Item> ITEMS = new ArrayList<Item>();
	public static ProductAttributes PRODUCT = new ProductAttributes();
	
	public static void fillProducts(Products[] products){
		PRODUCTS = new ArrayList<Products>(Arrays.asList(products));
		for (Products p : PRODUCTS){
			p.setImg();
		}
	}
	
	public static void fillItems(Item[] items){
		ITEMS = new ArrayList<Item>(Arrays.asList(items));
	}
	
	public static void fillProduct(ProductAttributes product){
		PRODUCT = product;
		PRODUCT.setSmallImage();
		PRODUCT.setBigImage();
	}
	
	public static void clearProducts(){
		PRODUCTS = new ArrayList<Products>();
	}
	
	public static void clearItems(){		
		ITEMS = new ArrayList<Item>();		
	}
	
	public static void clearProduct(){		
		PRODUCT = new ProductAttributes();
	}	

	/*
    public static List<Product> PRODUCTS = new ArrayList<Product>();    

    static {
    	Product p = new Product();
    	p.setId(1);
    	p.setProductName("HTC Sensation");
    	p.setProductNameExt("Delsi HTC Sensation");
    	p.setDescription("neco neco neco neco neco neco neco neco neco neco neco neco neco neco neco neco");
    	p.setMaxPrice(6500);
    	p.setMinPrice(4500);
    	
    	Map<Integer, Offer> o = new HashMap<Integer, Offer>();    	
    	o.put(2, new Offer(2, "Mall.cz", "5500", -1, "http://mall.cz"));
    	o.put(1, new Offer(1, "Alza.cz", "5000", 0, "http://alza.cz"));
    	o.put(3, new Offer(3, "czc.cz", "6000", 0, "http://czc.cz"));
    	
    	p.setOffers(o);
        addItem(p);
        
        p = new Product();
    	p.setId(2);
    	p.setProductName("HTC Desire");
    	p.setProductNameExt("Delsi HTC Desire");
    	p.setDescription("neco neco neco neco");
    	p.setMaxPrice(4500);
    	p.setMinPrice(5500);
    	
    	o = new HashMap<Integer, Offer>();    	
    	o.put(2, new Offer(2, "Shop.cz", "4500", -1, "http://shop.cz"));
    	o.put(1, new Offer(1, "NoName.cz", "5000", 0, "http://google.cz"));
    	o.put(3, new Offer(3, "Nikdo.cz", "5500", 0, "http://seznam.cz"));
    	
    	p.setOffers(o);
        addItem(p);        
    }   

    private static void addItem(Product item) {
    	PRODUCTS.add(item);    	
    }
    */
        
}
