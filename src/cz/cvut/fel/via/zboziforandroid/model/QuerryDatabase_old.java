package cz.cvut.fel.via.zboziforandroid.model;

import java.util.HashSet;

public class QuerryDatabase_old {
	
	public static String[] QUERIES = {};	
	private static HashSet<String> queries = new HashSet<String>();
	
	public static void saveQuerry(String query){
		if(!queries.contains(query)){
			queries.add(query);
			QUERIES = toArray();
		}
	}
	
	private static String[] toArray(){
		return queries.toArray(new String[0]);
	}
	

}
