package com.example.shoppingmall.home.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;

public class HomeFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        View home = View.inflate(mContext, R.layout.fg_home, null);
        initData();
        return home;
    }

    @Override
    public void initData() {
    }
}
