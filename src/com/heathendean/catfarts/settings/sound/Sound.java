package com.heathendean.catfarts.settings.sound;


import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.Settings;
import com.heathendean.catfarts.settings.options.Options;
import com.yariksoffice.lingver.Lingver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

public class Sound extends AppCompatActivity {
    public static final String PREF_NAME = "CF_PREFS" ;
    public static SharedPreferences sharedpreferences;
    public static int volumeLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_activity);
        // Get app-wide Shared Preferences.
        sharedpreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        // Set public volumeLevel if saved from previous session, if not set to 0 (mute).
        volumeLevel = sharedpreferences.getInt("Volume", 0);
        Log.d("Sound.java", "Saved volume is: " + volumeLevel);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new Sound.PlaceholderFragment()).commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.sound_fragment, container, false);
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            SeekBar volumeSB = getActivity().findViewById(R.id.volumeSelectSeekBar);
            // Set SeekBar's UI based on volumeLevel.
            volumeSB.setProgress(volumeLevel);
            volumeSB.setOnSeekBarChangeListener(new Sound.MySeekBarChangeListener());
        }
    }

    private static class MySeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        // SeekBar value is an int 0-100.
        // volumeLevel is a percentage of system volume. 0 will always be muted, 10 for VL and mute sys
        // will be mute, 10 for VL and max sys will be loud, etc.

        public void onProgressChanged(SeekBar seekBar, int volume, boolean fromUser) {
            Log.d("Sound.java", "Volume is: " + volume);
            // Set Shared Preference for this value.
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putInt("Volume", volume);
            editor.apply();
            volumeLevel = sharedpreferences.getInt("Volume", 0);
        }

        public void onStartTrackingTouch(SeekBar seekBar) {}

        public void onStopTrackingTouch(SeekBar seekBar) {}

    }



    public void upload_fart(View view){
        // Create Upload Far class and set intent/navigation here
    }

    public void view_fart_gallery(View view){
        // Create Fart Gallery class and set intent/navigation here
    }

    public void back_to_settings(View view) {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}