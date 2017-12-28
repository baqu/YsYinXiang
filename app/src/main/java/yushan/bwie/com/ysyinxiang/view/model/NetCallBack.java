package yushan.bwie.com.ysyinxiang.view.model;

/**
 * 类的用途 ：
 * zhangjiale
 * {DATE}
 */

public interface NetCallBack <T>{
    void successNet(T t);
    void errorNet(String errorMsg, int errorCode);
}
