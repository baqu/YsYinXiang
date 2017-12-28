package yushan.bwie.com.ysyinxiang.view.view;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
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
public class MyApplication extends Application {


    {   //配置QQappkey
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    private UMShareAPI umShareAPI;

    private static MyApplication app;

    public static MyApplication getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        app = this;
//        x.Ext.init(this);
//        x.Ext.setDebug(BuildConfig.DEBUG);
        //初始化sdk
        umShareAPI = UMShareAPI.get(this);






        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//				.showImageOnFail(R.drawable.test)
//				.showImageOnFail(R.drawable.test)
                .cacheInMemory(true).cacheOnDisc(true).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();

        ImageLoader.getInstance().init(config);


    }


    public UMShareAPI getUmShareAPI() {
        return umShareAPI;
    }
}
