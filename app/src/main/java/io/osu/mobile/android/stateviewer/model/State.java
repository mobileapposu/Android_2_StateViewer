package io.osu.mobile.android.stateviewer.model;

/**
 * Created by Rniemo on 5/25/15.
 */

/*
	A state is just a name and population (For now).
 */
public class State {

	private String mName;
	private int mPopulation;

	// e.g. new State("Ohio", 3833137);
	public State(String name, int population){
		mName = name;
		mPopulation = population;
	}

	public String getName(){
		return mName;
	}

	public int getPopulation(){
		return mPopulation;
	}
}
