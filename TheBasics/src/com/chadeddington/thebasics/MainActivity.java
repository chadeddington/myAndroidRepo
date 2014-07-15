package com.chadeddington.thebasics;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//uses Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.splash_menu);
		
		final MediaPlayer logoMusic = MediaPlayer.create(MainActivity.this, R.raw.lefthook);
		
		Timer timer = new Timer();
		timer.schedule(new playMusic(logoMusic),500); 
		
		Thread menuTimer = new Thread() {
			public void run() {
				try {
					sleep(2000);
					Intent menuIntent = new Intent("com.chadeddington.thebasics.MENU");
					startActivity(menuIntent);					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}			
			}
		}; 
		menuTimer.start();
		
	}
}

/******
 * 
 * TimerTask class that starts the sound
 *
 *****/
class playMusic extends TimerTask {
	private MediaPlayer soundToPlay;
	
	public playMusic(MediaPlayer song) {
		 soundToPlay = song;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		soundToPlay.start();
		
	} 
	
} 
