package com.area51.adapters;

import com.area51.fragments.TabDosFragment;
import com.area51.fragments.TabUnoFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {

	public TabPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Object fragmento = null;
		switch (arg0) {
		case 0:
			fragmento = new TabUnoFragment();
			break;
		case 1:
			fragmento = new TabDosFragment();
			break;
		}
		return (Fragment) fragmento;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
