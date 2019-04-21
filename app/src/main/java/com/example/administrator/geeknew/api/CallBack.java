package com.example.administrator.geeknew.api;

public interface CallBack<T> {
    void onSusser(T bean);
    void onError(String error);
}
