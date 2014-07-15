package com.chadeddington.thebasics;

import android.app.Activity;
import android.os.Bundle;

public class New_View2 extends Activity{
	
	Drawing v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		v = new Drawing(this);
		setContentView(v);
	}

}
