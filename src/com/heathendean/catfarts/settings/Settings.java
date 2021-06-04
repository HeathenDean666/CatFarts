package com.heathendean.catfarts.settings;


import com.heathendean.catfarts.MainActivity;
import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.cheats.Cheats;
import com.heathendean.catfarts.settings.contact.Contact;
import com.heathendean.catfarts.settings.gameplay.Gameplay;
import com.heathendean.catfarts.settings.instructions.Instructions;
import com.heathendean.catfarts.settings.lore.Lore;
import com.heathendean.catfarts.settings.networking.Networking;
import com.heathendean.catfarts.settings.options.Options;
import com.heathendean.catfarts.settings.pictures.Pictures;
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

	public void instructions_menu(View view) {
		Intent intent = new Intent(this, Instructions.class);
		startActivity(intent);
	}

	public void sound_menu(View view) {
		Intent intent = new Intent(this, Sound.class);
		startActivity(intent);
	}

	public void pictures_menu(View view) {
		Intent intent = new Intent(this, Pictures.class);
		startActivity(intent);
	}

	public void gameplay_menu(View view) {
		Intent intent = new Intent(this, Gameplay.class);
		startActivity(intent);
	}

	public void options_menu(View view) {
		Intent intent = new Intent(this, Options.class);
		startActivity(intent);
	}

	public void networking_menu(View view) {
		Intent intent = new Intent(this, Networking.class);
		startActivity(intent);
	}

	public void cheats_menu(View view) {
		Intent intent = new Intent(this, Cheats.class);
		startActivity(intent);
	}

	public void contact_menu(View view) {
		Intent intent = new Intent(this, Contact.class);
		startActivity(intent);
	}

	public void lore_menu(View view) {
		Intent intent = new Intent(this, Lore.class);
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
