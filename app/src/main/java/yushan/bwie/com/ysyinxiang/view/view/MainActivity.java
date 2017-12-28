package yushan.bwie.com.ysyinxiang.view.view;


import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.personal.Personal;
import yushan.bwie.com.ysyinxiang.view.qclCopy.BlurBehind;
import yushan.bwie.com.ysyinxiang.view.qclCopy.OnBlurCompleteListener;
import yushan.bwie.com.ysyinxiang.view.release.ImageText;
import yushan.bwie.com.ysyinxiang.view.release.SelectPicPopupWindow;

/**
 * 类的用途：项目首页Tablelayout
 * 作者：张佳乐
 * 时间：2017/12/26AM8:47
 */


/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager vp;

    String[] array = {"推荐", "关注"};
    private List<Fragment> flist;
    private ImageView geren;
    private ImageView fabu;
    SelectPicPopupWindow menuWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        //沉浸式
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();





        //初始化控件
        initview();

        initData();

        //getLocaldeviceId(this);



        tabLayout.setupWithViewPager(vp);
        MyViewPager adapter = new MyViewPager(getSupportFragmentManager());
        vp.setAdapter(adapter);


        //调节下划线长度参数越大长度越短
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(tabLayout, 70, 70);
            }
        });

    }



    //初始化控件
    private void initview() {
        tabLayout = (TabLayout) findViewById(R.id.tablyout);
        vp = (ViewPager) findViewById(R.id.pager);
        geren = (ImageView) findViewById(R.id.geren);
        fabu = (ImageView) findViewById(R.id.fabu);


        //个人界面点击事件
        geren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BlurBehind.getInstance().execute(MainActivity.this, new OnBlurCompleteListener() {

                    @Override

                    public void onBlurComplete() {

                        Intent intent = new Intent(MainActivity.this, Personal.class);

                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);



                        startActivity(intent);

                    }

                });

            }
        });

        //发布界面点击事件
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                BlurBehind.getInstance().execute(MainActivity.this, new OnBlurCompleteListener() {
//
//                    @Override
//
//                    public void onBlurComplete() {
//
//                        Intent intent = new Intent(MainActivity.this, ReleaseRe.class);
//
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//
//
//
//                        startActivity(intent);
//
//                    }
//
//                });



                //实例化SelectPicPopupWindow
                menuWindow = new SelectPicPopupWindow(MainActivity.this, itemsOnClick);
                //显示窗口
                menuWindow.showAtLocation(MainActivity.this.findViewById(R.id.layout_main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置


            }
        });

    }

    private void initData() {
        flist = new ArrayList<>();
        flist.add(new Recommend());
        flist.add(new Follow());

    }







    //viewpager适配器
    public class MyViewPager extends FragmentPagerAdapter {


        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return flist.get(position);
        }

        @Override
        public int getCount() {
            return flist.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return array[position];
        }
    }


    //Tablayout下划线长度更改
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }




    //为弹出窗口实现监听类
    private View.OnClickListener itemsOnClick = new View.OnClickListener(){

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.fengmian:
                    Toast.makeText(MainActivity.this,"封面",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,CoverGraphic.class));
                    break;
                case R.id.tuwen:
                    Toast.makeText(MainActivity.this,"图文",Toast.LENGTH_SHORT).show();


                    startActivity(new Intent(MainActivity.this, ImageText.class));
                    break;
                default:
                    break;
            }


        }

    };
}