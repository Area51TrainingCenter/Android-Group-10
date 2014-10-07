package com.area51.adapters;

import com.area51.fragments.MostrarFragment;
import com.area51.utils.Constant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int posicion) {
		MostrarFragment fragmento=new MostrarFragment();
		Bundle bundle=new Bundle();
		bundle.putInt(fragmento.FRAGMENTO_POSICION, posicion);
		fragmento.setArguments(bundle);
		return fragmento;
	}

	@Override
	public int getCount() {
		return Constant.listaGrid.size();
	}

}
