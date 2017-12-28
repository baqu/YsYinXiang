package yushan.bwie.com.ysyinxiang.view.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.adapter.RecomAdapter;
import yushan.bwie.com.ysyinxiang.view.bean.Recommended_list;
import yushan.bwie.com.ysyinxiang.view.presenter.NewsPresenter;

/**
 * 类的用途：推荐首页
 * 作者：张佳乐
 * 时间：2017/12/26AM8:43
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

public class Recommend extends Fragment{

    private XRecyclerView recyviwe;
    private int num=10;
    private NewsPresenter presenter;
    private List<Recommended_list.DataBean.ArticleBean> list;
    private RecomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=View.inflate(getActivity(), R.layout.recommend,null);




        list = new ArrayList<>();


        recyviwe = (XRecyclerView) view.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyviwe.setLayoutManager(linearLayoutManager);

        Fresco.initialize(getActivity());
        
        getData(num);


        recyviwe.setPullRefreshEnabled(true);
        recyviwe.setLoadingMoreEnabled(true);
        recyviwe.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyviwe.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        recyviwe.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        num=10;
                        getData(num);
                        recyviwe.refreshComplete();
                    }

                }, 2000);

            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        num+=10;
                        getData(num);
                        recyviwe.refreshComplete();
                    }
                }, 2000);

            }
        });


        return view;
    }



    //http://m2.itmayi.net.cn/api/articles?user=surfer&flag=index2&fromUid=144&page=1
    private void getData(int count) {

        presenter = new NewsPresenter();
        presenter.getNews("surfer","index2",144,1,count);
        presenter.attachView(new NewsView() {
            @Override
            public void success(List<Recommended_list.DataBean.ArticleBean> data) {

                list.clear();
                list.addAll(data);


                if (adapter==null){
                    adapter = new RecomAdapter(list,getActivity());
                    recyviwe.setAdapter(adapter);

                }else {
                    adapter.notifyDataSetChanged();
                }




            }



            @Override
            public void failed(String e) {

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (presenter!=null){

            presenter.detachView();
        }
    }
}
