package com.robot.App;

import com.robot.Bean.User;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppContext extends Application {
	private boolean isFirst = false;// 是否为第一次登陆
	private boolean isSet = false;// 是否设置了图标和昵称

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	/**
	 * 获取是否为第一次登陆
	 */
	public boolean isFirst() {
		return isFirst;
	}

	/**
	 * 设置第一次登陆的状态
	 */
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	/**
	 * 判断是否设置了信息
	 */
	public boolean isSet() {
		return isSet;
	}

	/**
	 * 设置记录信息的状态
	 */
	public void setSet(boolean isSet) {
		this.isSet = isSet;
	}

	/**
	 * 保存用户信息
	 */
	public void saveLoginInfo(User user) {
		SharedPreferences preferences = getApplicationContext()
				.getSharedPreferences("User", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("UserName", user.getUserName());
		editor.putInt("UserHead", user.getUsericon());
		editor.commit();
		setSet(true);
	}

	/**
	 * 获取用户与登陆信息
	 */
	public User getUserSharedPre() {
		User user = new User();
		SharedPreferences preferences = getApplicationContext()
				.getSharedPreferences("User", Context.MODE_PRIVATE);
		String userName = preferences.getString("UserName", "黑衣人");
		int usericon = preferences.getInt("UserHead", 0);
		user.setUserName(userName);
		user.setUsericon(usericon);
		return user;
	}

}
