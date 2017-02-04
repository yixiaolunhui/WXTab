package com.dalong.wxtab.fragment;

import android.os.Bundle;

import com.dalong.wxtab.R;

/**
 * 消息
 */
public class MessageFragment extends LazyFragment {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_message);
        getData();
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
    }

    /**
     * 获取数据
     */
    public void  getData() {

    }
}
