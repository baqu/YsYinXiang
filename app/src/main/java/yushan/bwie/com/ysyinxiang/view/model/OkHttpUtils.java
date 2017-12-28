package yushan.bwie.com.ysyinxiang.view.model;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
 */

public class OkHttpUtils {

    private NetCallBack callBack;
    private static final int OK = 0;
    private Handler mHandler = new Handler();

    //get请求
    public <T> void getLoadData(String url, final NetCallBack callBack, final Class<T> tClass) {
        this.callBack = callBack;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.errorNet(e.getMessage(), 498);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                T t = new Gson().fromJson(response.body().string(), tClass);
                final Message msg = Message.obtain();
                msg.what = OK;
                msg.obj = t;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.successNet(msg.obj);
                    }
                });
            }
        });
    }

    //post请求
    public <T> void getLoadDataPost(String url, final NetCallBack callBack, final Class<T> tClass, RequestBody body) {
        this.callBack = callBack;
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
        final Request request = new Request.Builder().url(url).post(body).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.errorNet(e.getMessage(), 499);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                T t = new Gson().fromJson(response.body().string(), tClass);
                final Message msg = Message.obtain();
                msg.what = OK;
                msg.obj = t;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.successNet(msg.obj);
                    }
                });
            }
        });
    }
}
