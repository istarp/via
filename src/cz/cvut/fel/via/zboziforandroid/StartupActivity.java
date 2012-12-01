package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.Database;
import cz.cvut.fel.via.zboziforandroid.model.QueryDatabase;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class StartupActivity extends FragmentActivity implements SearchView.OnQueryTextListener, View.OnFocusChangeListener, TextWatcher {	
	
	private AutoCompleteTextView searchedString;
	private SearchView mSearchView; 
    private Menu mMenu; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);               
        
        new QueryDatabase(getApplicationContext());
        
        //XXXXXXX
        QueryDatabase.refreshQueries(); 
    	QueryDatabase.saveQuerry("sracka2");
    	//XXXXXXX
    	        
        this.setPreferences();
    	
        final Button searchButton = (Button) findViewById(R.id.searchButton);
        searchedString = (AutoCompleteTextView) findViewById(R.id.searchString);        
        
        searchedString.addTextChangedListener(this);
        searchedString.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, QueryDatabase.QUERIES));
        
        searchedString.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                	if (searchedString.getText().length() > 0){
	    				if (isOnline()){
	    					doSearch(searchedString.getText().toString());
	    				}else{
	    					Toast.makeText(getApplicationContext(), getResources().getString(R.string.not_online), Toast.LENGTH_LONG).show();
	    				}	    				
                	}else{
                		Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_input), Toast.LENGTH_LONG).show();
                	}
                    return true;
                }
                return false;
            }

        });
              
        searchButton.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				if (searchedString.getText().length() > 0){
					if (isOnline()){
						doSearch(searchedString.getText().toString());
					}else{
						Toast.makeText(getApplicationContext(), getResources().getString(R.string.not_online), Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.no_input), Toast.LENGTH_LONG).show();
				}
			}
		});
            
        handleIntent(getIntent());
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @SuppressWarnings("deprecation")
	private void handleIntent(Intent intent) {    	
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {                    	
            Uri uri = intent.getData();
            Cursor cursor = managedQuery(uri, null, null, null, null);
            if (cursor != null) {            	
                cursor.moveToFirst();
                int wIndex = cursor.getColumnIndexOrThrow(QueryDatabase.KEY_WORD);
                Toast.makeText(getApplicationContext(), cursor.getString(wIndex), Toast.LENGTH_SHORT).show();
            }
        }
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
        }
    }       
    
    private void doSearch(String query){    	    
    	QueryDatabase.saveQuerry(query);    	
        Intent listIntent = new Intent(this, ProductListActivity.class);                
        listIntent.putExtra(ProductListActivity.SEARCHED_STRING, query);
        startActivity(listIntent);    	
    }

	@Override
	public void afterTextChanged(Editable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		searchedString.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, QueryDatabase.QUERIES));		
	}
	
    @SuppressWarnings("deprecation")
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        this.mMenu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.basic_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);                      
        mSearchView = (SearchView) searchItem.getActionView();  
        mSearchView.setOnQueryTextListener(this);               
        mSearchView.setOnQueryTextFocusChangeListener(this);                        
        mSearchView.setMaxWidth(getWindowManager().getDefaultDisplay().getWidth());
        
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);        
      
        return true;
    }
     
	@Override
	public boolean onQueryTextChange(String arg0) {		
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(String arg0) {			
		collapseSearchMenu();
		return false;
	}
	
	@Override
	public void onResume() {
		if(mMenu != null){
			collapseSearchMenu();
			searchedString.setText("");			
		}
		super.onResume();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if(!hasFocus){
			collapseSearchMenu();
		}
		
	}	
	
	private void collapseSearchMenu(){
		MenuItem searchItem = mMenu.findItem(R.id.action_search);
		searchItem.collapseActionView();
	}
	
    @Override
    public boolean onSearchRequested() {
    	return true;
    }
    
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    
    public void setPreferences(){
    	SharedPreferences settings = getSharedPreferences(Database.settingsPreferences, MODE_PRIVATE);  
    	SharedPreferences.Editor prefEditor = settings.edit();  
    	prefEditor.putInt(Database.productDirection, 0);
    	prefEditor.putInt(Database.productCriterion, 0);
    	prefEditor.putInt(Database.productLimit, 10);
    	prefEditor.putInt(Database.productMaxPrice, -1);
    	prefEditor.putInt(Database.productMinPrice, 0);
    	prefEditor.putInt(Database.itemLimit, 10);
    	prefEditor.putBoolean(Database.itemAtStoreOnly, false);
    	prefEditor.commit(); 
    }
	
}
