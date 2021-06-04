package com.heathendean.catfarts.settings;


import com.heathendean.catfarts.MainActivity;
import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.instructions.Instructions;
import com.heathendean.catfarts.settings.sound.Sound;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Settings extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		setContentView(R.layout.activity_settings);
	}

	public void cat_options(View view) {
		Intent intent = new Intent(this, Instructions.class);
		startActivity(intent);
	}

	public void fart_options(View view) {
		Intent intent = new Intent(this, Sound.class);
		startActivity(intent);
	}

	public void back_to_main(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			return inflater.inflate(R.layout.fragment_settings, container, false);
		}
	}

}
