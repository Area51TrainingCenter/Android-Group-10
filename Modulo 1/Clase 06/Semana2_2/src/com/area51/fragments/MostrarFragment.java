package com.area51.fragments;

import com.area51.semana2_2.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MostrarFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View vistaFragment = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_detalle, container, false);
		return vistaFragment;
	}
}
