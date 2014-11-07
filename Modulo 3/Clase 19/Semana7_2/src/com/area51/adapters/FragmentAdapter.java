package com.area51.adapters;

import com.area51.fragments.HashTagFragment;
import com.area51.fragments.SearchFragment;
import com.area51.fragments.TimeLineFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Object fragment = null;
		switch (arg0) {
		case 0:
			fragment = new HashTagFragment();
			break;
		case 1:
			fragment = new TimeLineFragment();
			break;
		case 2:
			fragment = new SearchFragment();
			break;
		}
		return (Fragment) fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
