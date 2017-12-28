package yushan.bwie.com.ysyinxiang.view.release;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.qclCopy.BlurBehind;
import yushan.bwie.com.ysyinxiang.view.view.MyApplication;

/**
 * 类的用途： QQ登录
 * 作者：张佳乐
 * 时间：2017/12/22PM6:57
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

public class Release extends Activity implements View.OnClickListener{

    private ImageView guanbi;
    private Bitmap bitmap;
    private ImageView login;
    private String imageurl;
    private String nameString;
    private String genderString;
    private String cityString;
    private String provinceString;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setEnterTransition(new Slide().setDuration(500));
        getWindow().setExitTransition(new Slide().setDuration(2000));
        setContentView(R.layout.release);
        login = (ImageView) findViewById(R.id.login);

        login.setOnClickListener(this);


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

                .withAlpha(80)

                .withFilterColor(Color.parseColor("#ffffff"))

                .setBackground(this);

    }

    private void initview() {


                guanbi = (ImageView) findViewById(R.id.guanbi);
                guanbi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {




                    }
                });
            }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {


        MyApplication.getApp().getUmShareAPI().doOauthVerify(this,
                SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Toast.makeText(Release.this,"onStart",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        if(i==0){//判断i = 0,只是登录成功，但是获取不到数据


                            Toast.makeText(Release.this,"登录成功",Toast.LENGTH_SHORT).show();
                            finish();

//
//                            Intent intent=new Intent(Release.this, Personal.class);
//
//                            intent.putExtra("iconurl",imageurl);
//                            intent.putExtra("name",nameString);
//
//                            startActivity(intent);

                            //获取信息
                            MyApplication.getApp().getUmShareAPI().getPlatformInfo(Release.this,
                                    SHARE_MEDIA.QQ,
                                    this);
                        }
                        if(i==2){//当i=2/时，获取信息
                           // login.setVisibility(View.GONE);
                            //头像
                            imageurl = map.get("iconurl");
                            //昵称
                            nameString = map.get("name");
                            //男女
                            genderString = map.get("gender");
                            //城市
                            cityString = map.get("city");
                            //省份
                            provinceString = map.get("province");
//                            x.image().bind(image,imageurl);
//                            name.setText(nameString);
//                            gender.setText(genderString);
//                            city.setText(cityString);
//                            province.setText(provinceString);



                        }


                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                        Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {
                        Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();



                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MyApplication.getApp().getUmShareAPI().onActivityResult(requestCode, resultCode, data);
    }  }