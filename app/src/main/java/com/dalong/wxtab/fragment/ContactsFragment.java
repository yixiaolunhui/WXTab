package com.dalong.wxtab.fragment;

import android.os.Bundle;

import com.dalong.wxtab.R;

/**
 * 通讯录
 */
public class ContactsFragment extends LazyFragment {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_contacts);
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
