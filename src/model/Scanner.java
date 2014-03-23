package model;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import financeAccessor.WebAPI;
import android.os.Handler;
import android.os.Message;

public class Scanner extends Thread
{
	private Handler handler;
	private BlockingQueue<String> incomingTags;
	private ArrayList<Product> products;
	private WebAPI mysql;
	public Scanner(Handler handler)
	{
		mysql = new WebAPI(null);
		
		this.handler = handler;
		incomingTags = new ArrayBlockingQueue<String>(1000);
		//incomingTags.offer() <-- Putter noget i køen
		//incomingTags.take() tager noget fra køen eller venter
	}
	
	public void scan()
	{
		// 1. Start listening to bluetooth
		// 2. Scan first tag and check on db
		// 3. Call countries with data form db - productname and sKU
		// 4. 
	}
	
	/***
	 * 
	 * @param actual result given by db
	 * @throws InterruptedException 
	 */
	public void countEntries(int actual, String productName, String sKU) throws InterruptedException
	{
//		products = new ArrayList<Product>();
//		
//		progressBar.setMax(actual);
//		String tag = null;
//		int i = 1;
//		while(!incomingTags.isEmpty())
//		{
//			tag = incomingTags.take();
//			progressBar.setProgress(i++);
//			textView.setText("Antal: " + i);
//			
//			products.add(new Product(tag, productName, sKU)); // Ads to a list
//			if(i < actual)
//			{
//				continue;
//			}
//			
//			
//		}
		
		new Thread(new Runnable()
		{
			
			@Override
			public void run()
			{
				
				for(int i = 0 ; i < 100; i++)
				{
					Message msg = new Message();
					msg.arg1 = i;
					handler.sendMessage(msg);
					try
					{
						Thread.sleep(50);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
	}
}
