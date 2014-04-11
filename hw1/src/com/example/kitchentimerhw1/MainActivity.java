package com.example.kitchentimerhw1;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	private long counter;
	private CountDownTimer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		counter = 0;
		timer = null;
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		counter = 0;
		timer = null;
	}
	
	@Override
	protected void onPause(){
		if(timer != null){
			timer.cancel();
			timer = null;
		}
		super.onPause();
	}
	
	public void clickStartStop(View v){
		if(timer != null){
			//Timer is running, stops it leaving at current value
			timer.cancel();
			timer = null;
		} else {
			//Timer not running. Start it.
			restartCounter(true);
		}
	}
	
	private void restartCounter(boolean always){
		//Restart a counter always, or only if running.
		boolean isRunning = (timer != null);
		if (isRunning){
			timer.cancel();
			timer = null;
		}
		
		if(always || isRunning){
			displayCount(counter);
			if(counter > 0){
				timer = new CountDownTimer(counter, 1000){
					public void onTick(long remainingTimeMillis){
						counter = remainingTimeMillis;
						displayCount(counter);
					}
					
					public void onFinish(){
						displayCount(0);
						counter = 0;
					}
				};
				timer.start();
			}
		}
	}
	
	private void displayCount(long c){
		TextView mCount = (TextView) findViewById(R.id.textView1);
		long seconds = c / 1000;
		long minutes = seconds / 60;
		seconds -= minutes * 60;
		mCount.setText(minutes + ":" + seconds);
	}
	
	public void clickPlus(View v){
		counter += 60 * 1000;
		displayCount(counter);
		restartCounter(true);
	}
	
	public void clickMinus(View v){
		counter -= 60 * 1000;
		counter = Math.max(0, counter);
		displayCount(counter);
		restartCounter(true);
	}
	
	public void clickReset(View v){
		counter = 0;
		displayCount(counter);
		restartCounter(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
