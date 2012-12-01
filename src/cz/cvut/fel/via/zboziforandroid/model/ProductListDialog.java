package cz.cvut.fel.via.zboziforandroid.model;

import cz.cvut.fel.via.zboziforandroid.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class ProductListDialog extends DialogFragment{
	
	public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
    NoticeDialogListener mListener;
    Spinner productLimit;
    Spinner productCriterion;
    Spinner productDirection;
    CheckBox productPrice;
    LinearLayout productPriceFrom;
    LinearLayout productPriceTo;
    SharedPreferences settings;
    EditText productPriceFrom_value;
    EditText productPriceTo_value;
        
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);        
        try {            
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {            
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }	

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        View layout = inflater.inflate(R.layout.dialog_product_list, null); 
        
        settings = getActivity().getSharedPreferences(Database.settingsPreferences, 0); 
        
        productLimit = (Spinner) layout.findViewById(R.id.productLimit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.product_limit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     
        productLimit.setAdapter(adapter);
        
        int position = adapter.getPosition(Integer.toString(settings.getInt(Database.productLimit, 10)));
        productLimit.setSelection(position);
        
        productCriterion = (Spinner) layout.findViewById(R.id.productCriterion);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.product_criterion_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     
        productCriterion.setAdapter(adapter);
                
        switch (settings.getInt(Database.productCriterion, 0)){        
        case 1: position = adapter.getPosition(getResources().getString(R.string.product_criterion_1)); break;
        case 2: position = adapter.getPosition(getResources().getString(R.string.product_criterion_2)); break;
        default: position = adapter.getPosition(getResources().getString(R.string.product_criterion_0)); break;
        }        	        		
        productCriterion.setSelection(position);
        
        productDirection = (Spinner) layout.findViewById(R.id.productDirection);
        adapter = ArrayAdapter.createFromResource(getActivity(), R.array.product_direction_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     
        productDirection.setAdapter(adapter); 
        
        switch (settings.getInt(Database.productDirection, 0)){        
        case 1: position = adapter.getPosition(getResources().getString(R.string.product_direction_1)); break;
        default: position = adapter.getPosition(getResources().getString(R.string.product_direction_0)); break;
        }        	        		       
        productDirection.setSelection(position);
        
        productPrice = (CheckBox) layout.findViewById(R.id.productPrice);
        productPriceFrom = (LinearLayout) layout.findViewById(R.id.productPrice_from_container);
        productPriceTo = (LinearLayout) layout.findViewById(R.id.productPrice_to_container);
        productPriceFrom_value = (EditText) layout.findViewById(R.id.productPrice_from);
        productPriceTo_value = (EditText) layout.findViewById(R.id.productPrice_to);
        
        productPrice.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked){
					productPriceFrom.setVisibility(View.INVISIBLE);
			        productPriceTo.setVisibility(View.INVISIBLE);
				}else{
					productPriceFrom.setVisibility(View.VISIBLE);
			        productPriceTo.setVisibility(View.VISIBLE);
				}
				
			}
		});
        
        if (settings.getInt(Database.productMaxPrice, -1) == -1 && settings.getInt(Database.productMinPrice, 0) == 0){
        	productPrice.setChecked(true);
        	productPriceFrom.setVisibility(View.INVISIBLE);
	        productPriceTo.setVisibility(View.INVISIBLE);
        }else{
        	productPrice.setChecked(false);
        	productPriceFrom.setVisibility(View.VISIBLE);
        	productPriceFrom_value.setText(Integer.toString(settings.getInt(Database.productMinPrice, 0)));        	
	        productPriceTo.setVisibility(View.VISIBLE);
	        if(settings.getInt(Database.productMaxPrice, -1) == -1)
	        	productPriceTo_value.setText("");
	        else
	        	productPriceTo_value.setText(Integer.toString(settings.getInt(Database.productMaxPrice, 1000)));
        }        
        builder.setView(layout)
        	   .setTitle(R.string.filter_set)
               .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) { 
                	                   	   
                	   SharedPreferences.Editor prefEditor = settings.edit();  
                   	   prefEditor.putInt(Database.productDirection, productDirection.getSelectedItemPosition());
                   	   prefEditor.putInt(Database.productCriterion, productCriterion.getSelectedItemPosition());
                   	   prefEditor.putInt(Database.productLimit, Integer.parseInt(productLimit.getSelectedItem().toString()));
                   	   if(productPriceFrom.getVisibility() == 0){
                   		   if(!productPriceTo_value.getText().toString().equals(""))
                   			   prefEditor.putInt(Database.productMaxPrice, Integer.parseInt(productPriceTo_value.getText().toString()));
               			   if(!productPriceFrom_value.getText().toString().equals(""))
               				   prefEditor.putInt(Database.productMinPrice, Integer.parseInt(productPriceFrom_value.getText().toString()));
                   	   }
                   	   prefEditor.commit();                		
            		   mListener.onDialogPositiveClick(ProductListDialog.this);                	                  	                         
                   }
               })
               .setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {                       
                       mListener.onDialogNegativeClick(ProductListDialog.this);
                   }
               });           
        
        return builder.create();
    }
	
}
