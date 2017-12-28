package yushan.bwie.com.ysyinxiang.view.personal;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.qclCopy.BlurBehind;
import yushan.bwie.com.ysyinxiang.view.release.Release;

/**
 * 类的用途：我的信息页
 * 作者：张佳乐
 * 时间：2017/12/26AM8:44
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
public class Personal extends Activity {

    private ImageView guanbi;
    private TextView huati;
    private RoundedImageView login;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal);

        //沉浸式状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();


        initview();

        BlurBehind.getInstance()//在你需要添加模糊或者透明的背景中只需要设置这几行简单的代码就可以了

                .withAlpha(30)

                .withFilterColor(Color.parseColor("#ffffff"))

                .setBackground(this);


    }

    private void initview() {

        guanbi = (ImageView) findViewById(R.id.guanbi);
        huati = (TextView) findViewById(R.id.huati);
        login = (RoundedImageView) findViewById(R.id.login);
        name = (TextView) findViewById(R.id.name);

//
//        Intent intent = getIntent();
//
//        String iconurl = intent.getStringExtra("iconurl");
//        String xingming = intent.getStringExtra("name");
//
//        Glide.with(this)
//                .load(iconurl)
//                .transform(new GlideCircleTransform(this))
//                .into(login);
//
//        name.setText(xingming);
//

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Personal.this, Release.class));
            }
        });

        huati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              Intent intent=new Intent(Personal.this,AllTopics.class);
                startActivity(intent);

            }
        });


        guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Personal.this, MainActivity.class));
                finish();
            }
        });
    }
}
