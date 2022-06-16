package com.gzeinnumer.b5d8;

public interface BaseCallBackAdapter<T> {
    void onClick(int type, int position, T data);
}