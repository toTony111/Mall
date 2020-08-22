package com.example.shoppingmall.user.fragment;

import android.view.View;

import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.user.adapter.ItemBtnAdapter;
import com.example.shoppingmall.user.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends BaseFragment {
    View fg_mine;

    @Override
    public View initView() {
        fg_mine= View.inflate(mContext, R.layout.fg_mine, null);
        return fg_mine;
    }

    @Override
    public void initData() {
        super.initData();
        /*我的订单初始化*/
        List<ItemBean> orderBtns = new ArrayList<>();
        orderBtns.add(new ItemBean(R.mipmap.ic_mine_unpay, "待付款"));
        orderBtns.add(new ItemBean(R.mipmap.ic_mine_undelivery, "待发货"));
        orderBtns.add(new ItemBean(R.mipmap.ic_mine_unreceive, "待收货"));
        orderBtns.add(new ItemBean(R.mipmap.ic_mine_uncomment, "待评价"));
        orderBtns.add(new ItemBean(R.mipmap.ic_mine_after_sales, "售后"));
        RecyclerView order_state_view = fg_mine.findViewById(R.id.rv_my_order);
        order_state_view.setLayoutManager(new GridLayoutManager(mContext,5));
        order_state_view.setAdapter(new ItemBtnAdapter(orderBtns));

        /*我的服务初始化*/
        List<ItemBean> serviceBtns = new ArrayList<>();
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_activity, "活动管理"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_vedio, "视频管理"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_position, "我的地址"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_collection, "收藏夹"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_preferential, "优惠券"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_friends, "邀请好友"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_wx, "导师微信"));
        serviceBtns.add(new ItemBean(R.mipmap.ic_mine_cumtom_service, "客服中心"));
        RecyclerView my_service_view = fg_mine.findViewById(R.id.rv_my_service);
        my_service_view.setLayoutManager(new GridLayoutManager(mContext, 4));
        my_service_view.setAdapter(new ItemBtnAdapter(serviceBtns));
    }


}
