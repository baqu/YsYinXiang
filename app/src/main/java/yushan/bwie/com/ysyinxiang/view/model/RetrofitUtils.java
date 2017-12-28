package yushan.bwie.com.ysyinxiang.view.model;



import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
 */

public class RetrofitUtils {

    private Retrofit retrofit;
    private static volatile RetrofitUtils instance;
    private RetrofitUtils() {


    }

    private RetrofitUtils(String baseUrl) {

        OkHttpClient client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();

    }

    public static RetrofitUtils getInstance(String baseUrl){

        if (instance==null){

            synchronized (RetrofitUtils.class){
                if (null==instance){

                    instance=new RetrofitUtils(baseUrl);

                }
            }
        }

        return instance;
    }

    public static RetrofitUtils getInstance(){
        if (null==instance){

            //http://m2.itmayi.net.cn
            return getInstance("http://m2.itmayi.net.cn/");

        }
        return instance;
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
