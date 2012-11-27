package cz.cvut.fel.via.zboziforandroid.model;

import java.util.HashMap;
import java.util.HashSet;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;

public class QueryDatabase {

    public static final String KEY_WORD = SearchManager.SUGGEST_COLUMN_TEXT_1;

    private static final String DATABASE_NAME = "dictionary";
    private static final String FTS_VIRTUAL_TABLE = "FTSdictionary";
    private static final int DATABASE_VERSION = 2;

    private static QueryOpenHelper mDatabaseOpenHelper;
    private static final HashMap<String,String> mColumnMap = buildColumnMap();
    
    public static String[] QUERIES = {};	
    private static HashSet<String> queries = new HashSet<String>();

    public QueryDatabase(Context context) {
        mDatabaseOpenHelper = new QueryOpenHelper(context);
    }

    private static HashMap<String,String> buildColumnMap() {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put(KEY_WORD, KEY_WORD);
        map.put(BaseColumns._ID, "rowid AS " + BaseColumns._ID);
        map.put(SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID, "rowid AS " + SearchManager.SUGGEST_COLUMN_INTENT_DATA_ID);
        map.put(SearchManager.SUGGEST_COLUMN_SHORTCUT_ID, "rowid AS " + SearchManager.SUGGEST_COLUMN_SHORTCUT_ID);
        return map;
    }

    public Cursor getWord(String rowId, String[] columns) {
        String selection = "rowid = ?";
        String[] selectionArgs = new String[] {rowId};
        return query(selection, selectionArgs, columns);
    }

    public Cursor getWordMatches(String query, String[] columns) {
        String selection = KEY_WORD + " MATCH ?";
        String[] selectionArgs = new String[] {query+"*"};
        return query(selection, selectionArgs, columns);
    }

    private Cursor query(String selection, String[] selectionArgs, String[] columns) {
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables(FTS_VIRTUAL_TABLE);
        builder.setProjectionMap(mColumnMap);
        Cursor cursor = builder.query(mDatabaseOpenHelper.getReadableDatabase(), columns, selection, selectionArgs, null, null, null);
        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;
    }
    
	public static void saveQuerry(String query){
		if(!queries.contains(query)){
			queries.add(query);
			QUERIES = toArray();
	
			ContentValues initialValues = new ContentValues();
	        initialValues.put(KEY_WORD, query);
	        mDatabaseOpenHelper.getWritableDatabase().insert(FTS_VIRTUAL_TABLE, null, initialValues);	
		}
	}
	
	public static void deleteQuerries(){	
        mDatabaseOpenHelper.getWritableDatabase().delete(FTS_VIRTUAL_TABLE, null, null);	
	}	
	
	private static String[] toArray(){
		return queries.toArray(new String[0]);
	}
    
    private static class QueryOpenHelper extends SQLiteOpenHelper {
        
        private SQLiteDatabase mDatabase;
        
        private static final String FTS_TABLE_CREATE =
                    "CREATE VIRTUAL TABLE " + FTS_VIRTUAL_TABLE +
                    " USING fts3 (" +
                    KEY_WORD + ");";

        QueryOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            mDatabase = db;
            mDatabase.execSQL(FTS_TABLE_CREATE);
            loadWords();
        }
        
        private void loadWords(){
        	for(String q : QUERIES){
        		addWord(q);
        	}
        }

        public long addWord(String word) {        
            ContentValues initialValues = new ContentValues();
            initialValues.put(KEY_WORD, word);
            return mDatabase.insert(FTS_VIRTUAL_TABLE, null, initialValues);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + FTS_VIRTUAL_TABLE);
            onCreate(db);
        }
    }

}
