package io.osu.mobile.android.stateviewer.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import io.osu.mobile.android.stateviewer.R;
import io.osu.mobile.android.stateviewer.model.Model;
import io.osu.mobile.android.stateviewer.view.StateFragment;
import io.osu.mobile.android.stateviewer.view.WelcomeFragment;


// Note that there are 2 types of Fragments.
// android.support.v4.app.Fragment and android.app.Fragment.
// use the support.v4 type of fragment to support older versions of android.


/*
	Again, this is the entry point to our application. Notice that we extend FragmentActivity
	now, because we're going to use Fragments in this activity.
 */
public class MainActivity extends FragmentActivity {

	// This is a key used to transfer the state name from our activity
	// to our fragment. What a fragment is will be described in the WelcomeFragment class
	public final static String ARG_STATE_NAME = "ARG_STATE_NAME";

	// DrawerLayout is the view that holds our navigation drawer. This is also our
	// top level view
	private DrawerLayout mDrawerLayout;
	// mListView is the list view inside of DrawerLayout. This is used
	// to list all of the states the user can click on
	private ListView mListView;
	// An Adapter is used to populate a ListView. This means if we want to add
	// or remove items from the list of states, we can do that through this object.
	private ArrayAdapter<String> mListAdapter;
	// This is our model in the MVC pattern.
	private Model mModel;
	// This object makes it so the "hamburger" icon in the top-left corner of the screen
	// will be visible for the user to tap to open the navigation drawer
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Go to the definition of Model for an explanation of getInstance()
		mModel = Model.getInstance();
		// Here we get the main view form the xml file as usual
		mDrawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
		// The drawer toggle is what allows the user to open the nav drawer from the icon in the
		// top-left. Here we pass it the activity, the DrawerLayout to open/close, and then
		// 2 strings which are descriptions of the drawer opening and closing
		mDrawerToggle = new ActionBarDrawerToggle(
				this, mDrawerLayout, R.string.drawer_open_description, R.string.drawer_close_description);
		// Here we tell our drawer layout to pass its events to our drawer toggle. That is,
		// when the user closes or opens the drawer, the drawer will now tell our mDrawerToggle
		// object that the user did so.
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		// These two lines actually show the button in the action bar that mDrawerToggle controls
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		// Get the listview inside of our navigation drawer.
		mListView = (ListView) mDrawerLayout.findViewById(R.id.topic_lv);
		// Get the list of state names from the model to display
		List<String> stateNames = mModel.getStateNames();
		// Now create an adapter that contains the state names. Display each state name
		// in the layout provided by R.layout.state_topic
		mListAdapter = new ArrayAdapter<String>(this, R.layout.state_topic, stateNames);
		// Set the adapter for the list view so that our states actually show up
		mListView.setAdapter(mListAdapter);
		// Set the onclick listener for the list view. This will be called whenever the user clicks an item in our
		// navigation drawer. Don't worry about this syntax, but if you're interested in learning more,
		// google "java anonymous classes"
		mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				selectItem(position);
			}
		});
		// If the user just opened the app, there is no savedInstanceState, so we should display
		// a welcome fragment. If there is a savedInstanceState, that means the user rotated the
		// screen or something, which made the activity reload, so we don't need to re-show the
		// welcome fragment
		if(savedInstanceState == null) {
			// Create a welcome fragment
			Fragment welcomeFragment = new WelcomeFragment();
			// Set the welcome fragment to be displayed
			replaceFragment(welcomeFragment);
		}
		// Display our main view
		setContentView(mDrawerLayout);
	}

	// This method replaces whatever fragment is currently being shown with the fragment
	// supplied by the argument
	public void replaceFragment(Fragment fragment){
		// This gets Android's fragment manager, which manages which fragments are visible.
		FragmentManager fm = getSupportFragmentManager();
		// beginTransaction() creates a FragmentTransaction which the FragmentManager can execute.
		// .replace() tells the FragmentManager to take whatever fragment is in R.id.content_frame,
		// and replace it with "fragment".
		// addToBackStack() tells the fragment manager to keep track of this fragment. Basically,
		// if the user presses the back button, then the Fragment Manager will go back to the most
		// recently viewed fragment. Without this line, the back button will just quit the application
		// instead of going back to the most recent fragment.
		// commit() is the call that actually executes this FragmentTransaction and makes the
		// fragment visible to the user.
		fm.beginTransaction()
				.replace(R.id.content_frame, fragment)
				.addToBackStack(null)
				.commit();
	}

	// selectItem() is called when the user clicks on an item in the navigation drawer
	public void selectItem(int position){
		// First, get the corresponding state name to the position from the list adapter.
		String stateName = mListAdapter.getItem(position);
		// Now create a new StateFragment
		Fragment fragment = new StateFragment();
		// This is similar to the intent business from the last project. A Bundle can be thought of
		// as a map -- just a bunch of key/value pairs.
		Bundle args = new Bundle();
		// We add the key/value pair of "ARG_STATE_NAME"/[the state name] to the bundle.
		args.putString(ARG_STATE_NAME, stateName);
		// Now we tell the fragment to save these arguments. These can then be accessed
		// inside the Fragment.
		fragment.setArguments(args);
		// Call replace fragment, described above, to show this StateFragment
		replaceFragment(fragment);
		// This line sets the title of the action bar (at the top of the screen) to be the state name
		setTitle(stateName);
		// Without this, the drawer would remain open even though the user already clicked on an item
		mDrawerLayout.closeDrawers();
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		// this lets the toggle know whether or not the drawer is open or closed in instances
		// where the activity may have been restored (e.g. screen rotation)
		mDrawerToggle.syncState();
	}


	// onOptionsItemSelected is the callback for when the user hits an item in the action bar
	// at the top of the screen. For us, this just handles when the user hits our toggle.
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the user's touch.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
