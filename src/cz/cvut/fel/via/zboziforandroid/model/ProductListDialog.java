package cz.cvut.fel.via.zboziforandroid.model;

import java.util.regex.Pattern;

import cz.cvut.fel.via.zboziforandroid.R;
import cz.cvut.fel.via.zboziforandroid.client.Utils;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class ProductListDialog extends DialogFragment {

	public interface NoticeDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog);

		public void onDialogNegativeClick(DialogFragment dialog);
	}

	private NoticeDialogListener mListener;
	private Spinner productLimit;
	private Spinner productCriterion;
	private Spinner productDirection;
	private CheckBox productPrice;
	private LinearLayout productPriceFrom;
	private LinearLayout productPriceTo;
	private SharedPreferences settings;
	private EditText productPriceFrom_value;
	private EditText productPriceTo_value;

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
		settings = getActivity().getSharedPreferences(Const.settingsPreferences, 0);
		setComponents(layout);

		builder.setView(layout)
				.setTitle(R.string.filter_set)
				.setPositiveButton(R.string.positive_button, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						SharedPreferences.Editor prefEditor = settings.edit();
						prefEditor.putInt(Const.productDirection, productDirection.getSelectedItemPosition());
						prefEditor.putInt(Const.productCriterion, productCriterion.getSelectedItemPosition());
						prefEditor.putInt(Const.productLimit, Integer.parseInt(productLimit.getSelectedItem().toString()));
						if (productPrice.isChecked()) {
							prefEditor.putInt(Const.productMaxPrice, -1);
							prefEditor.putInt(Const.productMinPrice, 0);
						} else {
							if (!productPriceTo_value.getText().toString().equals(""))
								prefEditor.putInt(Const.productMaxPrice, Integer.parseInt(productPriceTo_value.getText().toString()));
							if (!productPriceFrom_value.getText().toString().equals(""))
								prefEditor.putInt(Const.productMinPrice, Integer.parseInt(productPriceFrom_value.getText().toString()));
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

	private void setComponents(final View layout) {
		productLimit = (Spinner) layout.findViewById(R.id.productLimit);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.product_limit_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		productLimit.setAdapter(adapter);

		int position = adapter.getPosition(Integer.toString(settings.getInt(Const.productLimit, 10)));
		productLimit.setSelection(position);

		productCriterion = (Spinner) layout.findViewById(R.id.productCriterion);
		adapter = ArrayAdapter.createFromResource(getActivity(), R.array.product_criterion_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		productCriterion.setAdapter(adapter);

		switch (settings.getInt(Const.productCriterion, 0)) {
		case 1:
			position = adapter.getPosition(getResources().getString(R.string.product_criterion_1));
			break;
		case 2:
			position = adapter.getPosition(getResources().getString(R.string.product_criterion_2));
			break;
		default:
			position = adapter.getPosition(getResources().getString(R.string.product_criterion_0));
			break;
		}
		productCriterion.setSelection(position);

		productDirection = (Spinner) layout.findViewById(R.id.productDirection);
		adapter = ArrayAdapter.createFromResource(getActivity(), R.array.product_direction_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		productDirection.setAdapter(adapter);

		switch (settings.getInt(Const.productDirection, 0)) {
		case 1:
			position = adapter.getPosition(getResources().getString(R.string.product_direction_1));
			break;
		default:
			position = adapter.getPosition(getResources().getString(R.string.product_direction_0));
			break;
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
				if (isChecked) {
					productPriceFrom.setVisibility(View.INVISIBLE);
					productPriceTo.setVisibility(View.INVISIBLE);
				} else {
					productPriceFrom.setVisibility(View.VISIBLE);
					productPriceTo.setVisibility(View.VISIBLE);
				}

			}
		});

		if (settings.getInt(Const.productMaxPrice, -1) == -1 && settings.getInt(Const.productMinPrice, 0) == 0) {
			productPrice.setChecked(true);
			productPriceFrom.setVisibility(View.INVISIBLE);
			productPriceTo.setVisibility(View.INVISIBLE);
		} else {
			productPrice.setChecked(false);
			productPriceFrom.setVisibility(View.VISIBLE);
			productPriceFrom_value.setText(Integer.toString(settings.getInt(Const.productMinPrice, 0)));
			productPriceTo.setVisibility(View.VISIBLE);
			if (settings.getInt(Const.productMaxPrice, -1) == -1)
				productPriceTo_value.setText("");
			else
				productPriceTo_value.setText(Integer.toString(settings.getInt(Const.productMaxPrice, 1000)));
		}

		Button mButtonDeleteHistory = (Button) layout.findViewById(R.id.deleteHistoryButton);

		mButtonDeleteHistory.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Utils.deleteWordsHistory(Utils.getEmail(layout.getContext()));
				return false;
			}
		});

	}

}
