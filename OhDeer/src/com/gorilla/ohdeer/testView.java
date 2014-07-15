package com.gorilla.ohdeer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class testView extends View{
	
	Bitmap ball;
	int x, y;
	
	public testView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		Rect myRectangle = new Rect();
		myRectangle.set(0, 0, canvas.getWidth(), canvas.getHeight()/2);
		
		Paint rectColor = new Paint();
		rectColor.setColor(Color.YELLOW);
		rectColor.setStyle(Paint.Style.FILL);
		
		canvas.drawRect(myRectangle, rectColor);
		
	}

}
