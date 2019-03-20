package com.example.wongnoubo.imitated_wechat;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.view.View;
import android.view.ViewGroup;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import android.widget.LinearLayout;

import com.example.wongnoubo.imitated_wechat.AddressFragment;
import com.example.wongnoubo.imitated_wechat.SportFragment;
import com.example.wongnoubo.imitated_wechat.MineFragment;
import com.example.wongnoubo.imitated_wechat.HomeFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,AddressFragment.OnFragmentInteractionListener,SportFragment.OnFragmentInteractionListener,MineFragment.OnFragmentInteractionListener,HomeFragment.OnFragmentInteractionListener{
    private static final String TAG="MainActivity";
    private ViewPager viewPager;
    private LinearLayout mTabHomePage;
    private LinearLayout mTabAddress;
    private LinearLayout mTabStepNumber;
    private LinearLayout mTabMine;
    private ImageButton btnHomePage,btnAddress,btnStepNumber,btnMine;
    private PagerAdapter mPagerAdapter;
    private List<View> mViews = new ArrayList<View>();

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, getClass().getSimpleName());
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();//隐藏标题栏
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"切换Fragment");
                btnHomePage.setImageResource(R.drawable.shouye_press);
                replaceAddressFragment(new HomeFragment());
            }
        }).start();
        initViews();
        initEvents();
    }

    /**
     * 滑动点击事件
     */
    private void initEvents() {
        mTabMine.setOnClickListener(this);
        mTabHomePage.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabStepNumber.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            /**
             * 滑动点击事件
             * @param i
             */
            @Override
            public void onPageSelected(int i) {
                int currentItem = viewPager.getCurrentItem();
                resetImg();
                switch(currentItem){
                    case 0:
                        btnHomePage.setImageResource(R.drawable.shouye_press);
                        replaceAddressFragment(new HomeFragment());
                        Log.d(TAG,"滑动主页");
                        break;
                    case 1:
                        btnAddress.setImageResource(R.drawable.faxian_press);
                        replaceAddressFragment(new AddressFragment());
                        Log.d(TAG,"滑动地址");
                        break;
                    case 2:
                        btnStepNumber.setImageResource(R.drawable.yundong_press);
                        replaceAddressFragment(new SportFragment());
                        Log.d(TAG,"滑动步数");
                        break;
                    case 3:
                        btnMine.setImageResource(R.drawable.wo_press);
                        replaceAddressFragment(new MineFragment());
                        Log.d(TAG,"滑动我的");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initViews() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        mTabHomePage = (LinearLayout) findViewById(R.id.tab_homepage);
        mTabAddress = (LinearLayout) findViewById(R.id.tab_address);
        mTabStepNumber = (LinearLayout) findViewById(R.id.tab_step);
        mTabMine = (LinearLayout) findViewById(R.id.tab_mine);
        btnHomePage = (ImageButton) findViewById(R.id.tab_homepage_img);
        btnAddress = (ImageButton) findViewById(R.id.tab_address_img);
        btnStepNumber= (ImageButton) findViewById(R.id.tab_step_img);
        btnMine = (ImageButton) findViewById(R.id.tab_mine_img);
        LayoutInflater inflater = LayoutInflater.from(this);
        View tabHomepage = inflater.inflate(R.layout.fragment_home,null);
        View tabAddress = inflater.inflate(R.layout.fragment_address,null);
        View tabStepnumber = inflater.inflate(R.layout.fragment_sport,null);
        View tabMine = inflater.inflate(R.layout.fragment_mine,null);
        mViews.add(tabHomepage);
        mViews.add(tabAddress);
        mViews.add(tabStepnumber);
        mViews.add(tabMine);
        mPagerAdapter = new PagerAdapter(){

            @Override
            public int getCount() {
                return mViews.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mViews.get(position);
                container.addView(view);
                Log.d(TAG,"view");
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mViews.get(position));
            }
        };
        viewPager.setAdapter(mPagerAdapter);
    }

    /**
     * 点击翻页
     * @param view
     */
    @Override
    public void onClick(View view) {
        resetImg();
        switch(view.getId()){
            case R.id.tab_step:
                viewPager.setCurrentItem(2);
                btnStepNumber.setImageResource(R.drawable.yundong_press);
                replaceAddressFragment(new SportFragment());
                Log.d(TAG,"点击步数");
                break;
            case R.id.tab_address:
                viewPager.setCurrentItem(1);
                btnAddress.setImageResource(R.drawable.faxian_press);
                replaceAddressFragment(new AddressFragment());
                Log.d(TAG,"点击地址");
                break;
            case R.id.tab_mine:
                viewPager.setCurrentItem(3);
                btnMine.setImageResource(R.drawable.wo_press);
                replaceAddressFragment(new MineFragment());
                Log.d(TAG,"点击我的");
                break;
            case R.id.tab_homepage:
                viewPager.setCurrentItem(0);
                btnHomePage.setImageResource(R.drawable.shouye_press);
                replaceAddressFragment(new HomeFragment());
                Log.d(TAG,"点击首页");
                break;
            default:
                break;
        }
    }

    /**
     * 将所有的图片切换为暗色
     */
    private void resetImg() {
        btnHomePage.setImageResource(R.drawable.shouye);
        btnMine.setImageResource(R.drawable.wo);
        btnStepNumber.setImageResource(R.drawable.yundong);
        btnAddress.setImageResource(R.drawable.faxian);
    }

    /**
     * 动态切换Fragment
     */
    private void replaceAddressFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.address_layout,fragment);
        transaction.commit();
    }
}
