package yushan.bwie.com.ysyinxiang.view.personal;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yushan.bwie.com.ysyinxiang.R;
import yushan.bwie.com.ysyinxiang.view.adapter.AllTopicsAdapter;
import yushan.bwie.com.ysyinxiang.view.bean.AllTopics_Bean;
import yushan.bwie.com.ysyinxiang.view.model.NetCallBack;
import yushan.bwie.com.ysyinxiang.view.model.OkHttpUtils;

/**
 * 类的用途：全部话题页
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
public class AllTopics extends AppCompatActivity implements NetCallBack<AllTopics_Bean>{

    private RecyclerView recyview;
    private List<AllTopics_Bean.DataBean.TopicBean> list = new ArrayList<>();
    private AllTopicsAdapter adapter;
    private TextView fanhui;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alltopics);

        //沉浸式标题栏
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


        initview();


    }

    private void initview() {

        recyview = (RecyclerView) findViewById(R.id.recyview);

        fanhui = (TextView) findViewById(R.id.fanhui);

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        recyview.setLayoutManager(linearLayoutManager);


        adapter = new AllTopicsAdapter(list,this);


        recyview.setAdapter(adapter);


        OkHttpUtils ok=new OkHttpUtils();
        ok.getLoadData("http://m2.itmayi.net.cn/api/topics?user=surfer&flag=list&fromUid=144&page=1&limit=9",this,AllTopics_Bean.class);





    }

    @Override
    public void successNet(AllTopics_Bean allTopics_bean) {

        list.addAll(allTopics_bean.getData().getTopic());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void errorNet(String errorMsg, int errorCode) {

    }
}
