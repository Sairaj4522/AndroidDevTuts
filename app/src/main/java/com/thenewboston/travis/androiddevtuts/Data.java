package com.thenewboston.travis.androiddevtuts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class Data extends Activity {
	Button start, startFor;
	EditText sendET;
	TextView gotAnswer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();

		AdView mAdView = (AdView) findViewById(R.id.adView_get);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

	}
	
	private void initialize() {
		start = (Button) findViewById(R.id.bSA);
		startFor = (Button) findViewById(R.id.bSAFR);
		sendET = (EditText) findViewById(R.id.etSend);
		gotAnswer = (TextView) findViewById(R.id.tvGot);
	}
	
	public void onStartActivityButtonClick(View view){
		String bread = sendET.getText().toString();
		Bundle basket = new Bundle();
		basket.putString("bread", bread);
		Intent a = new Intent(Data.this, OpenedClass.class);
		a.putExtras(basket);
		startActivity(a);
	}
	
	public void onStartActivityForResultButtonClick(View view){
		String bread = sendET.getText().toString();
		Bundle basket = new Bundle();
		basket.putString("bread", bread);
		Intent a = new Intent(Data.this, OpenedClass.class);
		a.putExtras(basket);
		startActivityForResult(a, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
			if(requestCode == 0){
				if(resultCode == RESULT_OK){
					Bundle basket = data.getExtras();
					String s = basket.getString("answer");
					gotAnswer.setText(s);
				}
			}
	}
}
