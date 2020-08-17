package com.example.shoppingmall.shop.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.base.BaseFragment;

public class ShopFragment extends BaseFragment {
    private TextView textView;

    @Override
    public View initView() {
        textView = new TextView(mContext);
        initData();
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("商城初始化完成");
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
    }
}
