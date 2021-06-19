package com.heathendean.catfarts.play;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.heathendean.catfarts.MainActivity;
import com.heathendean.catfarts.R;
import com.heathendean.catfarts.settings.gameplay.Gameplay;
import com.heathendean.catfarts.settings.sound.Sound;

public class Play extends AppCompatActivity {
	//TODO: Make these values global? Config file?
	public static String lastPic = "";
	public static String lastSound = "";
	private static final int PIC_NUM = 15;
	private static final int SOUND_NUM = 6;
	MediaPlayer mediaPlayer = new MediaPlayer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_start_game);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public void new_cat(View view) {
		// Release resource if something went wrong last time.
		mediaPlayer.release();

		// Create and Display random image from resources
		ImageView image = findViewById(R.id.imageView1);
		Random randPic = new Random();
		String picResSuffix = String.valueOf(randPic.nextInt(PIC_NUM) + 1);
		if (!Gameplay.catsRepeat){
			Log.d("Play.java", "Repeats not allowed.");
			Log.d("Play.java","last pic: " + lastPic);
			Log.d("Play.java","pic suffix: " + picResSuffix);
			while (lastPic.equals(picResSuffix)){
				Log.e("Play.java","Cat Repeat!!");
				picResSuffix = String.valueOf(randPic.nextInt(PIC_NUM) + 1);
			}
		}
		lastPic = picResSuffix;
		String picResName = "cat_" + picResSuffix;
		int catPictureResourceId = this.getResources().getIdentifier(picResName, "drawable", this.getPackageName());
		image.setImageResource(catPictureResourceId);

		// Create and Play random sound from resources
		Random randSound = new Random();
		String soundResSuffix = String.valueOf(randSound.nextInt(SOUND_NUM) + 1);
		String soundResName = "fart_" + soundResSuffix;
		int catSoundResourceId = this.getResources().getIdentifier(soundResName, "raw", this.getPackageName());
		mediaPlayer = MediaPlayer.create(this.getApplicationContext(), catSoundResourceId);
		Log.d("Play.java","Sound.volumeLevel: " + String.valueOf(Sound.volumeLevel));
		mediaPlayer.setVolume(Sound.volumeLevel, Sound.volumeLevel);
		mediaPlayer.start();

		// Listen for sound completion and release resource.
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
		    public void onCompletion(MediaPlayer mp) {
		        mp.release();
		    };
		});
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
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_start_game,
					container, false);
			return rootView;
		}
	}

}
