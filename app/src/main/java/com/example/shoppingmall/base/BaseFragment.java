package com.example.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * BaseFragment子类：
 * 首页：UserFragment
 * 商城：ShopFragment
 * 我的：UserFragment
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;

    /**
     * 之前重写的方法写错了，不应该重写onViewCreated
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 用子类来重写这个方法，来决定返回什么view
     * @return
     */
    public abstract View initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 完成数据的初始化，可能有数据，也可能没有数据
     */
    public void initData(){

    };
}
