package yushan.bwie.com.ysyinxiang.view.model;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import yushan.bwie.com.ysyinxiang.view.bean.Recommended_list;

/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
 */

public interface BlogService {


    //http://api.tianapi.com/nba/?key=APIKEY&num=10
    //http://m2.itmayi.net.cn/api/articles?user=surfer&flag=index2&fromUid=144&page=1
    @GET("api/articles")
    Observable<Recommended_list> getData(@Query("user") String user,@Query("flag") String flag,@Query("fromUid") int fromUid,@Query("page") int page);



    //http://m2.itmayi.net.cn/api/topics?user=surfer&flag=list&fromUid=144&page=1&limit=2
}
