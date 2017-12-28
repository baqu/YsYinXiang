package yushan.bwie.com.ysyinxiang.view.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.custom.GlideCircleTransform;

/**
 * 类的用途：详情页
 * 作者：张佳乐
 * 时间：2017/12/26AM8:41
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

public class Details_page extends AppCompatActivity{

    private ImageView imagevie;
    private TextView titlename;
    private TextView wenzhang;
    private String title;
    private String neirong;
    private String image;
    private ImageView photo;
    private ImageView photo1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiangqingye);

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();





        imagevie = (ImageView) findViewById(R.id.imageview);
        titlename = (TextView) findViewById(R.id.title);
        wenzhang = (TextView) findViewById(R.id.wenzhang);
        photo = (ImageView) findViewById(R.id.photo);
        photo1 = (ImageView) findViewById(R.id.photo1);

        Intent intent = getIntent();

        image = intent.getStringExtra("image");
        title = intent.getStringExtra("title");
        neirong = intent.getStringExtra("neirong");

        titlename.setText(title);
        wenzhang.setText(neirong);
        Glide.with(this).load(image).into(imagevie);


        Glide.with(this)
                .load(image)
                .transform(new GlideCircleTransform(this))
                .into(photo);


        Glide.with(this)
                .load(image)
                .transform(new GlideCircleTransform(this))
                .into(photo1);






    }
}
