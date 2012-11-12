package cz.cvut.fel.via.zboziforandroid;

import cz.cvut.fel.via.zboziforandroid.model.QuerryDatabase;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class StartupActivity extends FragmentActivity implements TextWatcher {	
	
	private AutoCompleteTextView searchedString;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        
        final Button searchButton = (Button) findViewById(R.id.searchButton);
        searchedString = (AutoCompleteTextView) findViewById(R.id.searchString);        
        
        searchedString.addTextChangedListener(this);
        searchedString.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, QuerryDatabase.QUERIES));
        
        searchedString.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                	doSearch(searchedString.getText().toString());
                    return true;
                }
                return false;
            }

        });
              
        searchButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
		        doSearch(searchedString.getText().toString());				
			}
		});
        
    }
    
    private void doSearch(String query){
    	QuerryDatabase.saveQuerry(query);
        Intent listIntent = new Intent(this, ProductListActivity.class);        
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
		searchedString.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, QuerryDatabase.QUERIES));		
	}
	
}
