package com.robot.Adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.robot.R;

public class HeadAdapter extends BaseAdapter {
	private Context context;
	private List<Integer> list;

	public HeadAdapter(Context context, List<Integer> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view;
		Views chilview;
		if (convertView == null) {
			view = View.inflate(context, R.layout.hd, null);
			chilview = new Views();
			chilview.iView = (ImageView) view.findViewById(R.id.hd);
			view.setTag(chilview);
		} else {
			view = convertView;
			chilview = (Views) view.getTag();
		}

		chilview.iView.setBackgroundResource(list.get(position));
		return view;
	}

	class Views {
		ImageView iView;
	}
}
