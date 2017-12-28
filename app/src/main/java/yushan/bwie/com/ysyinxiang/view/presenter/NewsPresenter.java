package yushan.bwie.com.ysyinxiang.view.presenter;

import java.util.List;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import yushan.bwie.com.ysyinxiang.view.bean.Recommended_list;
import yushan.bwie.com.ysyinxiang.view.model.BlogService;
import yushan.bwie.com.ysyinxiang.view.model.RetrofitUtils;
import yushan.bwie.com.ysyinxiang.view.view.NewsView;

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

public class NewsPresenter {
    private Subscription subscribe;
    private NewsView inv;


    public void attachView(NewsView inv){
        this.inv = inv;
    }



    //getUrl(@Query("user") String user, @Query("flag") int flag, @Query("fromUid") int fromUid, @Query("page") int page);

    public void getNews(String user,String flag,int fromUid,int page,int num) {

        Retrofit retrofit = RetrofitUtils.getInstance().getRetrofit();

        BlogService service = retrofit.create(BlogService.class);

        subscribe = service.getData(user,flag,fromUid,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Recommended_list>() {
                    @Override
                    public void call(Recommended_list recommended_list) {

//                        List<Recommended_list.DataBean.ArticleBean> list = recommended_list.getData().getArticle();
//                        inv.success(list);

                        List<Recommended_list.DataBean.ArticleBean> list = recommended_list.getData().getArticle();

                        inv.success(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        inv.failed(throwable.getMessage());
                    }
                });
    }





    public void detachView(){

        if (subscribe!=null){

            if (subscribe.isUnsubscribed()){

                subscribe.unsubscribe();

            }
        }

        if (inv!=null){

            inv=null;
        }
    }
}


