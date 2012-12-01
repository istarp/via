package cz.cvut.fel.via.zboziforandroid.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import cz.cvut.fel.via.zboziforandroid.client.items.Item;
import cz.cvut.fel.via.zboziforandroid.client.product.ProductAttributes;
import cz.cvut.fel.via.zboziforandroid.client.products.Products;

public class Database {	
	
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
        
}
