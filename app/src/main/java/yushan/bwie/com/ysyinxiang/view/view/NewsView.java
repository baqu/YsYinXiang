package yushan.bwie.com.ysyinxiang.view.view;

import java.util.List;

import yushan.bwie.com.ysyinxiang.view.bean.Recommended_list;

/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
 */

public interface NewsView {

    void success(List<Recommended_list.DataBean.ArticleBean> data);


    void failed(String e);

}
