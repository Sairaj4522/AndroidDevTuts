package com.wbh.sairajmchavan.simpleapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class StartingPoint extends Activity {

	private int counter;
	Button add, sub;
	TextView display;

	public void onCreate(Bundle savedInstaceState){
		super.onCreate(savedInstaceState);
		setContentView(R.layout.starting_point);



		counter = 0;
		add = (Button) findViewById(R.id.bAdd);
		sub = (Button) findViewById(R.id.bSub);
		display = (TextView) findViewById(R.id.tvDisplay);


		AdView mAdView = (AdView) findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		add.setOnClickListener(new View.OnClickListener(){
						public void onClick(View view){
							counter++;
							display.setText("Your total is " + counter);
						}			
					}
		);

		sub.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				counter--;
				display.setText("Your total is " + counter);
			}
		});
	}
}
