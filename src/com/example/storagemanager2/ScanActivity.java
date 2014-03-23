package com.example.storagemanager2;

import model.Scanner;

import com.devspark.sidenavigation.SideNavigationView;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ScanActivity extends BaseActivity
{

	private ProgressBar progressBar;
	private TextView textView;
	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			textView.setText(msg.arg1 + " Antal");
			progressBar.setProgress(msg.arg1);

			// TODO Auto-generated method stub
			super.handleMessage(msg);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan);
		// other code

		initMenu();

		getActionBar().setDisplayHomeAsUpEnabled(true);

		textView = (TextView) findViewById(R.id.textview1);
		progressBar = (ProgressBar) findViewById(R.id.progressBar2);

		listen();
	}

	private void initMenu()
	{
		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		sideNavigationView.setMenuItems(R.menu.side_navigation_view);
		sideNavigationView.setMenuClickCallback(this);
	}

	private void emulateScan()
	{
		/* Emulates a scan */
		Scanner scan = new Scanner(handler);
		try
		{
			scan.countEntries(120, null, null);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Listens to the incoming bluetooth stream
	 */
	private void listen()
	{
		emulateScan();

	}

}
