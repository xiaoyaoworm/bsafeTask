package com.xiaoyaoworm.bsafetask;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	public RelativeLayout topLevelLayout = null;
	public EditText filter = null;
	public Button button = null;
	public TextView instruction1 = null;
	public TextView instruction2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		topLevelLayout = (RelativeLayout) findViewById(R.id.top_layout);
		filter = (EditText) findViewById(R.id.filter);
		button = (Button) findViewById(R.id.button);
		instruction1 = (TextView) findViewById(R.id.instruction1);
		instruction2 = (TextView) findViewById(R.id.instruction2);
		
		
		Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/Damion-Regular.ttf");
		button.setTypeface(myTypeface);
		instruction1.setTypeface(myTypeface);
		instruction2.setTypeface(myTypeface);
		
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				if(imm.isAcceptingText()){
					imm.hideSoftInputFromWindow(filter.getWindowToken(), 0);
					button.setVisibility(View.INVISIBLE);
				} 
			}
		});

		filter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(filter, 0);
				button.setVisibility(View.VISIBLE);
			}
		});
		
		
		if (filter.isFocused()) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.showSoftInput(filter, 0);
			button.setVisibility(View.VISIBLE);
		}

		// if (isFirstTime()) {
		// topLevelLayout.setVisibility(View.INVISIBLE);
		// }

		ImageView close = (ImageView) findViewById(R.id.close);
		close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				topLevelLayout.setVisibility(View.INVISIBLE);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	private boolean isFirstTime() {
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		boolean ranBefore = preferences.getBoolean("RanBefore", false);
		if (!ranBefore) {

			SharedPreferences.Editor editor = preferences.edit();
			editor.putBoolean("RanBefore", true);
			editor.commit();
			topLevelLayout.setVisibility(View.VISIBLE);
			topLevelLayout.setOnTouchListener(new View.OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					topLevelLayout.setVisibility(View.INVISIBLE);
					return false;
				}

			});

		}
		return ranBefore;

	}

}
