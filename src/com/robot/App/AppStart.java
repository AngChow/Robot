package com.robot.App;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.example.robot.R;
import com.robot.Activity.MainActivity;
import com.robot.Activity.Setting;

public class AppStart extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		View view = View.inflate(this, R.layout.start, null);
		setContentView(view);
		// 渐变展示启动屏
		AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
		aa.setDuration(3000);
		view.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
			}

		});
	}

	/**
	 * 导航到
	 */
	private void redirectTo() {
		AppContext appContext = (AppContext) getApplication();
		if (!appContext.isSet()) {
			Intent it = new Intent(AppStart.this, Setting.class);
			startActivity(it);
			finish();
		} else {
			Intent it = new Intent(AppStart.this, MainActivity.class);
			startActivity(it);
			finish();
		}

	}

}
