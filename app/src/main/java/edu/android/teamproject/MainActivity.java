package edu.android.teamproject;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private static final String TAG = "edu.android";
    private static final String[] TAB_TITLES = {"일기쓰기", "일기장", "우리"};


    private SelectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private TextView mainText;

//    private String DW = getString(R.string.DW);
//    private String Diary = getString(R.string.Diary);
//    private String us = getString(R.string.us);

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.topText);

        mSectionsPagerAdapter = new SelectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        //MainActivity TabView Image -> onTabSelected()
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.couple_o));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.write_o));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.letter_x));
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG,""+item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //MainActivity TabView Image
        int tabPosition = tab.getPosition();
        mainText.setText(TAB_TITLES[tabPosition]);

        mViewPager.setCurrentItem(tabPosition);
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}
    @Override
    public void onTabReselected(TabLayout.Tab tab) {}



    public static class SelectionsPagerAdapter extends FragmentPagerAdapter {

        private static final String TAG = "edu.android";

        public SelectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }



        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position == 0) {
                fragment = new DWFragment();
            } else if (position == 1){
                fragment = new DFragment();
            } else if (position == 2){
                fragment = new AnniFragment();
            }
            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "일기쓰기";
                case 1:
                    return "일기장";
                case 2:
                    return "우리";
            }
            return null;
        }
    }
}
