package io.osu.mobile.android.stateviewer.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.osu.mobile.android.stateviewer.R;
import io.osu.mobile.android.stateviewer.controller.MainActivity;
import io.osu.mobile.android.stateviewer.model.Model;
import io.osu.mobile.android.stateviewer.model.State;


/**
 * Created by Rniemo on 5/24/15.
 */
public class StateFragment extends Fragment {

	private Model mModel;
	private String mStateName;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mModel = Model.getInstance();
		// get the state name that this fragment is supposed to represent
		// very similar to how getIntent().getStringExtra() works.
		Bundle args = getArguments();
		mStateName = args.getString(MainActivity.ARG_STATE_NAME);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// inflate our main view from fragment_state xml file
		View mainView = inflater.inflate(R.layout.fragment_state, container, false);
		// get the correct state from the model
		State state = mModel.getStateByName(mStateName);
		// find our views in the mainView
		TextView nameView = (TextView) mainView.findViewById(R.id.state_name_tv);
		TextView populationView = (TextView) mainView.findViewById(R.id.state_population_tv);
		// populate our views with the correct data (name, population)
		nameView.setText(state.getName());
		populationView.setText("Population: " + state.getPopulation());
		// return our view
		return mainView;
	}

}
