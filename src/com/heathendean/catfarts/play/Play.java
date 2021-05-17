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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.heathendean.catfarts.MainActivity;
import com.heathendean.catfarts.R;

public class Play extends AppCompatActivity {
	//TODO: Make these values global? Config file?
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.start_game, menu);
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

	public void new_cat(View view) {
		// Release resource if something went wrong last time.
		mediaPlayer.release();
		// Setup audio manager.
		AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 20, 0);

		// TODO: handle repeats/ persistence?
		// Create and Display random image from resources
		ImageView image = (ImageView) findViewById(R.id.imageView2);
		Random randPic = new Random();
		String picResSuffix = String.valueOf(randPic.nextInt(PIC_NUM) + 1);
		String picResName = "cat_" + picResSuffix;
		int catPictureResourceId = this.getResources().getIdentifier(picResName, "drawable", this.getPackageName());
		image.setImageResource(catPictureResourceId);

		// Create and Play random sound from resources
		Random randSound = new Random();
		String soundResSuffix = String.valueOf(randSound.nextInt(SOUND_NUM) + 1);
		String soundResName = "fart_" + soundResSuffix;
		int catSoundResourceId = this.getResources().getIdentifier(soundResName, "raw", this.getPackageName());
		mediaPlayer = MediaPlayer.create(this.getApplicationContext(), catSoundResourceId);
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
