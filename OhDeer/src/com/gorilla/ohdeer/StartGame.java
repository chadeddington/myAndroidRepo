package com.gorilla.ohdeer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StartGame extends Activity {

	GameView v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		v = new GameView(this);
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
		Thread t = null;

		public GameView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			holder = getHolder();

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isReady) {
				// perform canvas asctions
				if (!holder.getSurface().isValid()) {
					continue;
				}
				Canvas c = holder.lockCanvas();
				c.drawARGB(255, 0, 255, 0); // alpha sets transparency?

				holder.unlockCanvasAndPost(c);
			}
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

}
