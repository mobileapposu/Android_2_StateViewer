package io.osu.mobile.android.stateviewer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rniemo on 5/24/15.
 */

/*
	This model is super simple. It's just a list of State objects. Each State object
	has a name and a population.

	Normally Models are responsible for storing data locally and retrieving data from the network.
	Both of these will come up in the next couple lessons.
 */
public class Model {

	private List<State> mStates;
	// This type of design pattern is called a singleton. It's where there's one instance of a
	// given class (in this case, Model). So instead of calling new Model() and then
	// passing that object as an argument to every class, every class can just get the single
	// Model instance by calling Model.getInstance();
	private static Model mInstance;


	public static Model getInstance(){
		// if the instance hasn't been created yet (first time calling getInstance()) then
		// create the instance.
		if(mInstance == null){
			mInstance = new Model();
		}
		return mInstance;
	}

	// Make the constructor private() so it can only be called inside the Model class.
	private Model(){
		// create our list of states
		mStates = new ArrayList<State>();
		// add a bunch of states to the list.
		// these populations were just googled (05/24/2015)
		mStates.add(new State("Alabama", 4779736));
		mStates.add(new State("Alaska", 710231));
		mStates.add(new State("Arizona", 6392017));
		mStates.add(new State("Arkansas", 2915918));
		mStates.add(new State("California", 37253956));
		mStates.add(new State("Colorado", 5029196));
		mStates.add(new State("Connecticut", 3574097));
		mStates.add(new State("Delaware", 897934));
		mStates.add(new State("Florida", 18801310));
		mStates.add(new State("Georgia", 9687653));
		mStates.add(new State("Hawaii", 1360301));
		mStates.add(new State("Idaho", 1567582));
		mStates.add(new State("Illinois", 12830632));
		mStates.add(new State("Indiana", 11));
		mStates.add(new State("Iowa", 6));
		mStates.add(new State("Kansas", 2853118));
		mStates.add(new State("Kentucky", 4339367));
		mStates.add(new State("Louisiana", 4533372));
		mStates.add(new State("Maine", 1328361));
		mStates.add(new State("Maryland", 5773552));
		mStates.add(new State("Massachusetts", 6547629));
		mStates.add(new State("Michigan", 9883640));
		mStates.add(new State("Minnesota", 5303925));
		mStates.add(new State("Mississippi", 2967297));
		mStates.add(new State("Missouri", 5988927));
		mStates.add(new State("Montana", 989415));
		mStates.add(new State("Nebraska", 1826341));
		mStates.add(new State("Nevada", 2700551));
		mStates.add(new State("New Hampshire", 1316470));
		mStates.add(new State("New Jersey", 8791894));
		mStates.add(new State("New Mexico", 2059179));
		mStates.add(new State("New York", 19378102));
		mStates.add(new State("North Carolina", 9535483));
		mStates.add(new State("North Dakota", 672591));
		mStates.add(new State("Ohio", 11536504));
		mStates.add(new State("Oklahoma", 3751351));
		mStates.add(new State("Oregon", 3831074));
		mStates.add(new State("Pennsylvania", 12702379));
		mStates.add(new State("Rhode Island", 1052567));
		mStates.add(new State("South Carolina", 4625364));
		mStates.add(new State("South Dakota", 814180));
		mStates.add(new State("Tennessee", 6346105));
		mStates.add(new State("Texas", 25145561));
		mStates.add(new State("Utah", 2763885));
		mStates.add(new State("Vermont", 625741));
		mStates.add(new State("Virginia", 8001024));
		mStates.add(new State("Washington", 6724540));
		mStates.add(new State("West Virginia", 1852994));
		mStates.add(new State("Wisconsin", 5686986));
		mStates.add(new State("Wyoming", 563626));
	}

	// step through each state and add the names to a list
	public List<String> getStateNames(){
		List<String> stateNames = new ArrayList<String>();
		for(State state : mStates){
			stateNames.add(state.getName());
		}
		return stateNames;
	}

	// Step through each state, if a given state's name matches the passed paramter
	// then return that state. Once each state has been gone through, return null because
	// no state was found.
	public State getStateByName(String stateName){
		for(State state : mStates) {
			if(state.getName().equals(stateName)){
				return state;
			}
		}
		return null;
	}

}
