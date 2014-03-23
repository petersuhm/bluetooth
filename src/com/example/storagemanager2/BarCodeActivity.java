package com.example.storagemanager2;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class BarCodeActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bar_code);

	}

	public void redirect(View view)
	{
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bar_code, menu);
		return true;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		if (scanResult != null)
		{
			// handle scan result
		}
		// else continue with any other code you need in the method
	}
}
