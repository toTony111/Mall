package com.example.shoppingmall.user.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.shoppingmall.base.BaseFragment;

public class UserFragment extends BaseFragment {
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
        textView.setText("个人中心初始化完成");
        textView.setGravity(View.TEXT_ALIGNMENT_CENTER);

    }
}
