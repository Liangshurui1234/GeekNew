package com.example.administrator.geeknew.view;


import com.example.administrator.geeknew.base.BaseView;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface LoginView extends BaseView {
    void setData(String data);

    String getUserName();
    String getPsd();

    void showToast(String msg);
}
