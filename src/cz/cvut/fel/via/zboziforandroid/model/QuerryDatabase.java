package cz.cvut.fel.via.zboziforandroid.model;

import java.util.HashSet;

import android.app.SearchManager;
import android.database.MatrixCursor;
import android.provider.BaseColumns;

public class QuerryDatabase {
	
	public static String[] QUERIES = {};	
	private static HashSet<String> queries = new HashSet<String>();
	public static MatrixCursor CURSOR;
	
	public static void saveQuerry(String query){
		if(!queries.contains(query)){
			queries.add(query);
			QUERIES = toArray();
			if (CURSOR == null){
				String[] columnNames = {BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1};
			    CURSOR = new MatrixCursor(columnNames);
			    String[] temp = new String[2];	    	   
		        temp[0] = "1";
		        temp[1] = "content://your.authority/optional.suggest.path/SUGGEST_URI_PATH_QUERY/puppies";
		        CURSOR.addRow(temp);
			}else{
				//addToCursor(query);
			}
		}
	}
	
	private static String[] toArray(){
		return queries.toArray(new String[0]);
	}
	
	private static void addToCursor(String query){			  
	    String[] temp = new String[2];	    	   
        temp[0] = Integer.toString(queries.size() - 1);
        temp[1] = query;
        CURSOR.addRow(temp);	    
	}

}
