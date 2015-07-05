package com.thenewboston.travis.androiddevtuts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class Splash extends Activity {
	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Fabric.with(this, new Crashlytics());

		// Adding git hash to the app release through crashlytics
		// Source: http://www.donnfelker.com/why-you-should-use-a-git-sha-in-your-crash-reporting/
        Crashlytics.setString("git_sha", BuildConfig.GIT_SHA);
		setContentView(R.layout.splash);

		ourSong = MediaPlayer.create(this, R.raw.splashsound);
		
		
		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		if(music){
			ourSong.start();
		}
		
		Thread timer = new Thread(new Runnable(){
			public void run(){
				try{
					Thread.sleep(2800);
				}catch(InterruptedException ex){
				
				}finally{
					Intent intent = new Intent("com.thenewboston.travis.COOLMENU");
					startActivity(intent);
				}
			}
		});
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.release();
		finish();
	}
	
}
