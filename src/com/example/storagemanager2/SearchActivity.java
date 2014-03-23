package com.example.storagemanager2;

import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import android.os.Bundle;

public class SearchActivity extends BaseActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);

		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		sideNavigationView.setMenuItems(R.menu.side_navigation_view);
		sideNavigationView.setMenuClickCallback(this);
		sideNavigationView.setMode(Mode.LEFT);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

}
