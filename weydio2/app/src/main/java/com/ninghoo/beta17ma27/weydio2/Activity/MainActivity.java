package com.ninghoo.beta17ma27.weydio2.Activity;

import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.ninghoo.beta17ma27.weydio2.Fragment.RecyclerListFragment;
import com.ninghoo.beta17ma27.weydio2.Fragment.WebFragment;
import com.ninghoo.beta17ma27.weydio2.Fragment.WeiboHomeFragment;
import com.ninghoo.beta17ma27.weydio2.R;
import com.ninghoo.beta17ma27.weydio2.View.ViewPagerIndicator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private List<Fragment> mTabContents = new ArrayList<Fragment>();
    private FragmentPagerAdapter mAdapter;
    private ViewPager mViewPager;

    private List<String> mDatas = Arrays.asList("weibo", "music");

    private ViewPagerIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideActionBar();

        initState();

        initView();
        initDatas();
        //设置Tab上的标题
        mIndicator.setTabItemTitles(mDatas);
        mViewPager.setAdapter(mAdapter);
        //设置关联的ViewPager
        mIndicator.setViewPager(mViewPager,0);

    }

    private void initDatas()
    {
        // 这里加入Fragment的顺序，决定了ViewPager显示的顺序。
//        WebFragment fragment1 = WebFragment.newInstance("web");
//        mTabContents.add(fragment1);
        WeiboHomeFragment fragment1 = WeiboHomeFragment.newInstance("weibo");
        mTabContents.add(fragment1);

        RecyclerListFragment fragment = RecyclerListFragment.newInstance("music");
        mTabContents.add(fragment);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mTabContents.size();
            }

            @Override
            public Fragment getItem(int position)
            {
                return mTabContents.get(position);
            }
        };
    }

    private void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.vp_pager);
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicator);
    }

    private void hideActionBar()
    {
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null)
        {
            actionBar.hide();
        }
    }

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            // 状态栏变成白色。
            // 动态设置状态栏。
            LinearLayout linear_bar = (LinearLayout) findViewById(R.id.visable_bar);
            linear_bar.setVisibility(View.VISIBLE);
            //获取到状态栏的高度
            int statusHeight = getStatusBarHeight();
            int statusWidth = getStatusBarWidth();
            //动态的设置隐藏布局的高度
            // 这里的设置和模拟状态栏所在的根布局有关。
//            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) linear_bar.getLayoutParams();
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linear_bar.getLayoutParams();
            params.height = statusHeight;
            params.width = statusWidth;
            linear_bar.setLayoutParams(params);
        }
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 通过反射的方式获取状态栏宽度
     *
     * @return
     */
    public int getStatusBarWidth()
    {
        Resources resources = this.getResources();

        DisplayMetrics dm = resources.getDisplayMetrics();

        float density1 = dm.density;

        // 第2个参数控制宽度的回收。
        int mWidth = dm.widthPixels - dip2px(density1, 0);

        return mWidth;
    }

    // dp转换成px；
    public static int dip2px(float scale, int dpValue)
    {
        return (int) (dpValue * scale + 0.5f);
    }
}
