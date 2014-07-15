package com.chadeddington.thebasics;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		
		//button sound
		final MediaPlayer buttonSound = MediaPlayer.create(Menu.this, R.raw.buttonsound);
		
		//First Button
		Button newView1 = (Button) findViewById(R.id.new_view1);
		newView1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonSound.start();
				startActivity(new Intent("com.chadeddington.thebasics.NEW_VIEW1"));
				
			}
		});
		
		//Second Button
		Button newView2 = (Button) findViewById(R.id.new_view2);
		newView2.setOnClickListener(new View.OnClickListener() {			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonSound.start();
				startActivity(new Intent("com.chadeddington.thebasics.NEW_VIEW2"));
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	

}
