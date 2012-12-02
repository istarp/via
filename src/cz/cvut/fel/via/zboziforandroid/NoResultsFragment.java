package cz.cvut.fel.via.zboziforandroid;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoResultsFragment extends Fragment{

	private String query;
	
	public static final String NO_RESULTS_QUERY = "no_results_query";
	
    public NoResultsFragment() {    	
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.query = getArguments().getString(NO_RESULTS_QUERY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_no_results, container, false);
        TextView text = (TextView) rootView.findViewById(R.id.no_results_text);
        text.setText(String.format(getString(R.string.no_result_text), query));
        return rootView;
    }
}
