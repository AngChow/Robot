package com.robot.Activity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.robot.R;
import com.robot.Adapter.HeadAdapter;
import com.robot.App.AppContext;
import com.robot.Bean.User;

public class Setting extends Activity {
	private EditText nickname;
	private ImageView headImageView;
	private Button sureButton;

	private GridView gv;
	private AlertDialog myDialog;
	private Field[] fields = R.drawable.class.getDeclaredFields();
	private List<Integer> list = new ArrayList();
	private HeadAdapter adapter;

	private int T = 9;// 图片默认为9

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		nickname = (EditText) findViewById(R.id.et_setting_nc);
		headImageView = (ImageView) findViewById(R.id.iv_setting_head);
		sureButton = (Button) findViewById(R.id.bt_setting_sure);
		BtnListener listener = new BtnListener();
		headImageView.setOnClickListener(listener);
		sureButton.setOnClickListener(listener);
		// 装载头像
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().startsWith("png_")) {
				try {
					list.add(fields[i].getInt(R.drawable.class));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private class BtnListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (v.getId() == headImageView.getId()) {
				MyDialog();
			}
			if (v.getId() == sureButton.getId()) {
				if (nickname.getText().toString() != null
						&& !"".equals(nickname.getText().toString())) {
					User user = new User();
					user.setUserName(nickname.getText().toString());
					user.setUsericon(T);
					AppContext appContext = (AppContext) getApplication();
					appContext.saveLoginInfo(user);
					Intent it = new Intent(Setting.this, MainActivity.class);
					startActivity(it);
					finish();
				} else {
					nickname.setError("输入昵称");
				}
			}
		}
	}

	private void MyDialog() {
		myDialog = new AlertDialog.Builder(Setting.this).create();
		myDialog.show();
		myDialog.getWindow().setContentView(R.layout.head);
		myDialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);// 从新显示软键盘
		dialoggvListener listener = new dialoggvListener();
		gv = (GridView) myDialog.getWindow().findViewById(R.id.gv_head_head);
		gv.setOnItemClickListener(listener);
		adapter = new HeadAdapter(Setting.this, list);
		gv.setAdapter(adapter);

	}

	private class dialoggvListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			myDialog.dismiss();
			headImageView.setBackgroundResource(list.get(position));
			T = list.get(position);
		}
	}

}
