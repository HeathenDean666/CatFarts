package com.heathendean.catfarts.settings.sound;


import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.Settings;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Sound extends AppCompatActivity {
    public static int volumeLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound_activity);

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
    }

    // volumeLevel is a percentage of system volume. 0 will always be muted, 10 for VL and mute sys
    // will be mute, 10 for VL and max sys will be loud, etc.

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