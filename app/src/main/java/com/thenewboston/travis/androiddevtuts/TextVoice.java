package com.thenewboston.travis.androiddevtuts;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class TextVoice extends Activity implements OnClickListener {

	final static String[] texts = {
		"Whaaat's up Gangstas!", "You smell!"
	};
	TextToSpeech tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textvoice);
		Button b = (Button) findViewById(R.id.bTextVoice);
		b.setOnClickListener(this);
		tts = new TextToSpeech(TextVoice.this, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				if(status != TextToSpeech.ERROR){
					tts.setLanguage(Locale.US);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		Random r = new Random();
		String random = texts[r.nextInt(2)];
		if (random.length() < TextToSpeech.getMaxSpeechInputLength()){
			tts.speak(random, TextToSpeech.QUEUE_FLUSH , null);
		} else {
			Toast.makeText(TextVoice.this, "Some error occured", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onPause() {
		
		if(tts != null){
			tts.stop();
			tts.shutdown();
		}
		
		super.onPause();
	}

}