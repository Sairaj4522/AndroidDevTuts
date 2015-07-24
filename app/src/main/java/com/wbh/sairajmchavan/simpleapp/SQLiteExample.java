package com.wbh.sairajmchavan.simpleapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.stetho.Stetho;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;


public class SQLiteExample extends Activity implements OnClickListener {

	Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Fabric.with(this, new Crashlytics());
		Stetho.initialize(
				Stetho.newInitializerBuilder(this)
						.enableDumpapp(
								Stetho.defaultDumperPluginsProvider(this))
						.enableWebKitInspector(
								Stetho.defaultInspectorModulesProvider(this))
						.build());
		// Adding git hash to the app release through crashlytics
		// Source: http://www.donnfelker.com/why-you-should-use-a-git-sha-in-your-crash-reporting/
		Crashlytics.setString("git_sha", BuildConfig.GIT_SHA);
		Crashlytics.setString("buildTime", BuildConfig.BUILD_TIME);

		setContentView(R.layout.sqliteexample);
		
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLopenView);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		
		sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
		sqlModify= (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);
		
		sqlModify.setOnClickListener(this);
		sqlGetInfo.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.bSQLUpdate:
				boolean didItWork = true;
				try{
					String name = sqlName.getText().toString();
					String hotness = sqlHotness.getText().toString();
					
					HotOrNot entry = new HotOrNot(SQLiteExample.this);
					entry.open();
					
					entry.createEntry(name, hotness);
					
					entry.close();
				} catch (Exception e){
					didItWork = false;
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Dang it!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show();
				} finally {
					if(didItWork){
						Dialog d = new Dialog(this);
						d.setTitle("Heck Yeah!");
						TextView tv = new TextView(this);
						tv.setText("Success");
						d.setContentView(tv);
						d.show();
					}
				}
				break;
			
			case R.id.bSQLopenView:
				Intent i = new Intent("com.wbh.sairajmchavan.simpleapp.SQLVIEW");
				startActivity(i);
				break;
			
			case R.id.bgetInfo:
				try{
					String s = sqlRow.getText().toString();
					long l = Long.parseLong(s);
					HotOrNot hon = new HotOrNot(this);
					hon.open();
					String returnedName = hon.getName(l);
					String returnedHotness = hon.getHotness(l);
					hon.close();
					
					sqlName.setText(returnedName);
					sqlHotness.setText(returnedHotness);
				} catch (Exception e){
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Dang it!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show();
				}
				break;
			
			case R.id.bSQLmodify:
				try{
					String mName = sqlName.getText().toString();
					String mHotness = sqlHotness.getText().toString();
					String sRow = sqlRow.getText().toString();
					long lRow = Long.parseLong(sRow);
					
					HotOrNot ex = new HotOrNot(this);
					ex.open();
					ex.updateEntry(lRow, mName, mHotness);
					
				} catch (Exception e){
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Dang it!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show();
				}
				break;
				
			case R.id.bSQLdelete:
				try{
					String sRow1 = sqlRow.getText().toString();
					long lRow1 = Long.parseLong(sRow1);
					HotOrNot ex1 = new HotOrNot(this);
					ex1.open();
					ex1.deleteEntry(lRow1);
				} catch (Exception e){
					String error = e.toString();
					Dialog d = new Dialog(this);
					d.setTitle("Dang it!");
					TextView tv = new TextView(this);
					tv.setText(error);
					d.setContentView(tv);
					d.show();
				}
				break;
			
	}
	}

}
