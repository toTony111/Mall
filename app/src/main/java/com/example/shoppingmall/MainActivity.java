package com.example.shoppingmall;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.FragmentActivity;

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


    @BindView(R.id.fragmentLayout)
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//将Butterknife与当前activity绑定起来
        RadioButton rbHome = rg.findViewById(R.id.rb_home);
        rbHome.setChecked(true);//默认选中home页
        changeImageSize();
        initFragment();
    }

    /**
     * 初始化多个fragment
     */
    private void initFragment() {

    }


    //    改变底部导航栏的图标的大小
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
}
