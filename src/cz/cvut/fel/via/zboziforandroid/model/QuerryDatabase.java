package cz.cvut.fel.via.zboziforandroid.model;

import java.util.ArrayList;

public class QuerryDatabase {
	
	public static String[] QUERIES = {};	
	private static ArrayList<String> queries = new ArrayList<String>();	
	
	public static void saveQuerry(String query){
		queries.add(query);
		QUERIES = toArray();
	}
	
	private static String[] toArray(){
		return queries.toArray(new String[0]);
	}

}
