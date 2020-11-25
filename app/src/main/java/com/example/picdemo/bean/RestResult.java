package com.example.picdemo.bean;


public class RestResult<T> {

    private T list;


    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
