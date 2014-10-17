package com.area51.adapters;

import com.area51.fragments.DetalleFragment;
import com.area51.utils.Contants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		DetalleFragment fragment=new DetalleFragment();
		Bundle bundle=new Bundle();
		bundle.putInt(Contants.posicion, arg0);
		fragment.setArguments(bundle);
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Contants.listaImagenes.size();
	}

}
