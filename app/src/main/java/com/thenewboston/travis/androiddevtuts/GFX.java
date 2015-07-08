package com.thenewboston.travis.androiddevtuts;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {
	
	MyBringBack ourView;
	WakeLock wL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		//wake-lock
		PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "whatever");
		
		super.onCreate(savedInstanceState);
		wL.acquire();
		
		ourView  = new MyBringBack(this);
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(wL.isHeld()) //If the wL doesn't hold any Wakelock,wL.release() will lead into a java.lang.RuntimeException
			wL.release();
	}

}
