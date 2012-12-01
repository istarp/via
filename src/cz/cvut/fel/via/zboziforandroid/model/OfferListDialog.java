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
import android.widget.Spinner;

public class OfferListDialog extends DialogFragment{
	
	public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
	
    private NoticeDialogListener mListener;
    private Spinner offerLimit;
    private CheckBox offerAtStore;
    private SharedPreferences settings;
    
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
        
        View layout = inflater.inflate(R.layout.dialog_offer_list, null); 
        
        settings = getActivity().getSharedPreferences(Database.settingsPreferences, 0); 
        
        offerLimit = (Spinner) layout.findViewById(R.id.offerLimit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.offer_limit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     
        offerLimit.setAdapter(adapter);
        
        int position = adapter.getPosition(Integer.toString(settings.getInt(Database.itemLimit, 10)));
        offerLimit.setSelection(position);        
        
        offerAtStore = (CheckBox) layout.findViewById(R.id.offerAtStore);        
        
        if (settings.getBoolean(Database.itemAtStoreOnly, false)){
        	offerAtStore.setChecked(true);
        }else{
        	offerAtStore.setChecked(false);
        }        
        builder.setView(layout)
        	   .setTitle(R.string.filter_set)
               .setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) { 
                	                   	   
                	   SharedPreferences.Editor prefEditor = settings.edit();  
                   	   prefEditor.putInt(Database.itemLimit, Integer.parseInt(offerLimit.getSelectedItem().toString()));
                   	   prefEditor.putBoolean(Database.itemAtStoreOnly, offerAtStore.isChecked());
                   	   prefEditor.commit();                		
            		   mListener.onDialogPositiveClick(OfferListDialog.this);                	                  	                         
                   }
               })
               .setNegativeButton(R.string.negative_button, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {                       
                       mListener.onDialogNegativeClick(OfferListDialog.this);
                   }
               });           
        
        return builder.create();
    }    

}
