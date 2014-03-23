package com.example.storagemanager2;

import com.devspark.sidenavigation.ISideNavigationCallback;
import com.devspark.sidenavigation.SideNavigationView;
import com.devspark.sidenavigation.SideNavigationView.Mode;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * BasActivity is a container for navigationpanel and the related activities
 * @author Christian
 *
 */
public class BaseActivity extends Activity implements ISideNavigationCallback
{
	protected SideNavigationView sideNavigationView;
	public static final String EXTRA_TITLE = "com.devspark.sidenavigation.sample.extra.MTGOBJECT";
	public static final String EXTRA_RESOURCE_ID = "com.devspark.sidenavigation.sample.extra.RESOURCE_ID";
	public static final String EXTRA_MODE = "com.devspark.sidenavigation.sample.extra.MODE";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan); //Call activity_main for optional home screen
		// other code

		sideNavigationView = (SideNavigationView) findViewById(R.id.side_navigation_view);
		sideNavigationView.setMenuItems(R.menu.side_navigation_view);
		sideNavigationView.setMenuClickCallback(this);
		sideNavigationView.setMode(Mode.LEFT);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			sideNavigationView.toggleMenu();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	protected void invokeActivity(Intent intent, String title)
	{

		intent.putExtra(EXTRA_TITLE, title);
		// intent.putExtra(EXTRA_RESOURCE_ID, resId);
		intent.putExtra(EXTRA_MODE,
				sideNavigationView.getMode() == Mode.LEFT ? 0 : 1);

		// all of the other activities on top of it will be closed and this
		// Intent will be delivered to the (now on top) old activity as a
		// new Intent.
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(intent);
		// no animation of transition
		overridePendingTransition(0, 0);
	}

	@Override
	public void onSideNavigationItemClick(int itemId)
	{
		Intent intent = null;
		switch (itemId)
		{

		case R.id.side_navigation_menu_item1:
			intent = new Intent(this, ScanActivity.class);
			invokeActivity(intent, "Home" /* , R.drawable.ic_android1 */);
			break;
		case R.id.side_navigation_menu_item2:
			intent = new Intent(this, SearchActivity.class);
			invokeActivity(intent, "Search" /* , R.drawable.ic_android1 */);
			break;
		case R.id.side_navigation_menu_item3:
			intent = new Intent(this, BarCodeActivity.class);
			invokeActivity(intent, "Scan" /* , R.drawable.ic_android1 */);
			break;
		case R.id.side_navigation_menu_item4:
			break;
		default:
			break;
		}
		// Validation clicking on side navigation item
	}
}
