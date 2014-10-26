package com.diplomado.ejemploviewpager;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class ViewPagerSlidesActivity extends FragmentActivity {
	
	// Cantidad de p‡ginas a mostrar
	private final static int NUMERO_PAGINAS = 5;
	
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_pager_slides);
		
		// Aqu’ creamos el PagerAdapter y lo asignamos al ViewPager
		viewPager = (ViewPager) findViewById(R.id.pager);
		pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
	}
	
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

		public ScreenSlidePagerAdapter(FragmentManager fm) {
			// FragmentStatePagerAdapter no implementa un constructor por defecto
			// Por eso necesitamos implementar este
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return ScreenSlidePageFragment.crear(position);
		}

		@Override
		public int getCount() {
			return NUMERO_PAGINAS;
		}
		
	}
}
