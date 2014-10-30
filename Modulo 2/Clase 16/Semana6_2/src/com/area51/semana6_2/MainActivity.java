package com.area51.semana6_2;

import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	Button btnSiguiente;
	ViewPager pager;
	CirclePageIndicator circleIndicator;
	int valor = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
		pager = (ViewPager) findViewById(R.id.pager);
		circleIndicator = (CirclePageIndicator) findViewById(R.id.indicator);

		pager.setAdapter(new ImagePagerAdapter());
		circleIndicator.setViewPager(pager);
	}

	private class ImagePagerAdapter extends PagerAdapter {
		private final int[] imagenes = new int[] { R.drawable.image1,
				R.drawable.image2, R.drawable.image3, R.drawable.image4 };

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return imagenes.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == ((ImageView) arg1);
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			Context context = MainActivity.this;
			ImageView imageView = new ImageView(context);
			int padding = context.getResources().getDimensionPixelSize(
					R.dimen.padding_medium);
			imageView.setPadding(padding, padding, padding, padding);
			imageView.setScaleType(ScaleType.CENTER_INSIDE);
			imageView.setImageResource(imagenes[position]);
			((ViewPager) container).addView(imageView, 0);
			return imageView;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			// TODO Auto-generated method stub
			Log.d("TAG", "->" + position);
			if (valor == 0) {
				if (position == 1) {
					valor = 1;
					btnSiguiente.setVisibility(View.VISIBLE);
				}
			} else {
				valor = 0;
				btnSiguiente.setVisibility(View.GONE);
			}
			((ViewPager) container).removeView((ImageView) object);
		}
	}

}
