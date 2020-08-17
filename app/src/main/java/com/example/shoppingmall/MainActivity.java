package com.example.shoppingmall;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.home.fragment.HomeFragment;
import com.example.shoppingmall.shop.fragment.ShopFragment;
import com.example.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.rg)
    RadioGroup rg;

    @BindView(R.id.rb_home)
    RadioButton rbHome;

    @BindView(R.id.rb_shop)
    RadioButton rbShop;

    @BindView(R.id.rb_user)
    RadioButton rbUser;


    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

//    设置选中的radiobutton的id
    private int position;

//    存放各个选项卡的fragment
    private List<BaseFragment> fragments;

    private BaseFragment mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//将Butterknife与当前activity绑定起来
        RadioButton rbHome = rg.findViewById(R.id.rb_home);
        initFragment();
        initListener();
        changeImageSize();
    }

    /**
     * 初始化多个fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShopFragment());
        fragments.add(new UserFragment());
    }

    /**
     * 改变底部导航栏的图标的大小
     */
    public void changeImageSize() {
        List<RadioButton> radioButtons = Arrays.asList(rbHome, rbShop, rbUser);
        for (int i = 0; i <radioButtons.size() ; i++) {
            //定义底部标签图片大小和位置
            Drawable drawable_news = getResources().getDrawable(R.drawable.selector_home);
            //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
            drawable_news.setBounds(0, 0, 70, 70);
            //设置图片在文字的哪个方向
            radioButtons.get(i).setCompoundDrawables(null, drawable_news, null, null);    }
    }

    /**
     * 初始化RadioGroup的监听器
     */
    private void initListener() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        position=0;
                        break;
                    case R.id.rb_shop:
                        position=1;
                        break;

                    case R.id.rb_user:
                        position=2;
                        break;

                    default:
                        position=0;
                        break;
                }
                BaseFragment toFragment = getFragment(position);
                switchFragment(mContext, toFragment);
            }
        });
        rg.check(R.id.rb_home);//默认选中home页
    }

    /**
     * 切换Fragment
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(BaseFragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    /**
     * 根据下标返回fragment
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (fragments.size()> 0 && fragments != null) {
            return fragments.get(position);
        }
        return null;
    }

}
