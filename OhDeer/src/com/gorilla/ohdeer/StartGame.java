package com.gorilla.ohdeer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class StartGame extends Activity implements OnTouchListener {

	GameView v;
	float startX = 0;
	float startY = 0;
	float drawX = 0;
	float drawY = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		v = new GameView(this);
		v.setOnTouchListener(this);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(v);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		v.resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		v.pause();
	}

	public class GameView extends SurfaceView implements Runnable {

		SurfaceHolder holder;
		boolean isReady = false;
		boolean spriteLoaded = false;
		int[][] map = GameMap.mapArray;
		Bitmap grass, bush, flowers, tTL, tTR, tBL, tBR;
		
		Thread t = null;

		public GameView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			holder = getHolder();

			grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
			bush = BitmapFactory.decodeResource(getResources(), R.drawable.bush);
			flowers = BitmapFactory.decodeResource(getResources(),R.drawable.flowers);
			tTL = BitmapFactory.decodeResource(getResources(),R.drawable.treetl);
			tTR = BitmapFactory.decodeResource(getResources(),R.drawable.treetr);
			tBL = BitmapFactory.decodeResource(getResources(),R.drawable.treebl);
			tBR = BitmapFactory.decodeResource(getResources(),R.drawable.treebr);

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			while (isReady) {
				// perform canvas actions
				if (!holder.getSurface().isValid()) {
					continue;
				}

				Canvas c = holder.lockCanvas();
				// c.drawARGB(255, 0, 255, 0); // alpha sets transparency?

				draw(c);

				holder.unlockCanvasAndPost(c);
			}
		}

		public void draw(Canvas canvas) {
			
			canvas.drawARGB(255, 0, 0, 0);

			for (int i = 0; i < map.length; i++){
				for (int k = 0; k < map[i].length;k++){
					if (map[i][k] == 0) {
						canvas.drawBitmap(grass, drawX, drawY, null);			
					} else if (map[i][k] == 1) {
						canvas.drawBitmap(bush, drawX, drawY, null);
					} else if (map[i][k] == 2) {
						canvas.drawBitmap(flowers, drawX, drawY, null);
					} else if (map[i][k] == 11) {
						canvas.drawBitmap(tTL, drawX, drawY, null);
					} else if (map[i][k] == 12) {
						canvas.drawBitmap(tTR, drawX, drawY, null);				
					} else if (map[i][k] == 13) {
						canvas.drawBitmap(tBL, drawX, drawY, null);				
					}else if (map[i][k] == 14) {
						canvas.drawBitmap(tBR, drawX, drawY, null);		
					} drawX += grass.getWidth();
				}
				drawY += grass.getHeight();
				drawX = startX;
			}
			drawY = startY;

		}

		public void pause() {
			isReady = false;
			while (true) {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
			t = null;
		}

		public void resume() {
			isReady = true;
			t = new Thread(this);
			t.start();
		}

	}

	@Override
	public boolean onTouch(View v, MotionEvent me) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(me.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//startX = me.getX();
			//startY = me.getY();
			break;
		
		case MotionEvent.ACTION_UP:
			//startX = me.getX() - 320;
			//startY = me.getY() - 320;
			break;
			
		case MotionEvent.ACTION_MOVE:
			break;
			
			
		}
		
		return true;
	}

}
