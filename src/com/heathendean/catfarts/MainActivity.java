package com.heathendean.catfarts;


import com.heathendean.catfarts.play.Play;
import com.heathendean.catfarts.settings.Settings;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import cat.ereza.customactivityoncrash.config.CaocConfig;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

	public final static String EXTRA_MESSAGE = "com.heathendean.catfarts.MESSAGE";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		CaocConfig.Builder.create()
				.backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
				.enabled(true) //default: true
				.showErrorDetails(true) //default: true
				.showRestartButton(true) //default: true
				.logErrorOnRestart(true) //default: true
				.trackActivities(true) //default: false
				.minTimeBetweenCrashesMs(2000) //default: 3000
				.errorDrawable(R.drawable.ic_red_male) //default: bug image
				.restartActivity(MainActivity.class) //default: null (your app's launch activity)
				.errorActivity(null) //default: null (default error activity)
				.eventListener(null) //default: null
				.apply();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
	/** Called when the user clicks the Start Game button */
	public void startGame(View view) {
		Intent intent = new Intent(this, Play.class);
		//EditText editText = (EditText) findViewById(R.id.edit_message);
		//String message = editText.getText().toString();
		//intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
	
	public void settings(View view) {
		Intent intent = new Intent(this, Settings.class);
		startActivity(intent);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
