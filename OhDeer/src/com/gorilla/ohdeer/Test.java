package com.gorilla.ohdeer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Test extends Activity{
	
	TestView v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		v = new TestView(this);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(v);
	}
	
	public class TestView extends View {

		Bitmap grass;
		Bitmap bush;
		Bitmap flowers;
		float x = 0;
		float y = 0;
		int[][] map = GameMap.mapArray;

		public TestView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
			bush = BitmapFactory.decodeResource(getResources(), R.drawable.bush);
			flowers = BitmapFactory.decodeResource(getResources(), R.drawable.flowers);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			
			for (int i = 0; i < map.length; i++){
				for (int k = 0; k < map[i].length;k++){
					if (map[i][k] == 0) {
						canvas.drawBitmap(grass, x, y, null);			
					} else if (map[i][k] == 1) {
						canvas.drawBitmap(bush, x, y, null);
					} else if (map[i][k] == 2) {
						canvas.drawBitmap(flowers, x, y, null);
					} x += grass.getWidth();
				}
				y += grass.getHeight();
				x = 0;
			}
				
			
		}
		
	}
	
	



}
