package io.osu.mobile.android.stateviewer.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.osu.mobile.android.stateviewer.R;


/**
 * Created by Rniemo on 5/24/15.
 */

/*
		A very simple Fragment. Fragments, like Activities, have lifecycles. In onCreate(),
		setup any private instance variables. in onCreateView() is where we set up the fragment's
		view
 */
public class WelcomeFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		// inflate our view from the fragment_welcome resource. Fairly straightforward,
		// just takes our xml and makes it a java object.
		View mainView = inflater.inflate(R.layout.fragment_welcome, container, false);
		return mainView;
	}
}
