package com.example.shoppingmall.home.fragment;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.home.bean.VideoBean;
import com.example.shoppingmall.utils.Constants;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.LinkedList;
import java.util.List;

public class HomeFragment extends BaseFragment {
    private IndicatorViewPager indicatorViewPager;
    private View home;

    @Override
    public View initView() {
        home = View.inflate(mContext, R.layout.fg_home, null);

        ViewPager viewPager = home.findViewById(R.id.moretab_viewPager);
        ScrollIndicatorView scrollIndicatorView = home.findViewById(R.id.siv_video_type);
        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xffed5757, Color.GRAY).setSize(selectSize, unSelectSize));
        scrollIndicatorView.setScrollBar(new ColorBar(mContext, 0xffed5757, 4));
        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter());

        /*完成数据初始化*/
        initData();
        return home;
    }


    @Override
    public void initData() {

        //请求网络
//        getDataFromNet();

        /*初始化视频列表*/
        List<VideoBean> list = new LinkedList<>();

    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(Constants.BASE_INDEX_BANNER)
                .build()
                .execute(new MyStringCallback());
    }

    private class MyStringCallback extends StringCallback {
        @Override
        public void onBefore(okhttp3.Request request, int id) {
            super.onBefore(request, id);
        }

        @Override
        public void onAfter(int id) {
            super.onAfter(id);
        }

        @Override
        public void inProgress(float progress, long total, int id) {
            super.inProgress(progress, total, id);
        }

        @Override
        public boolean validateReponse(okhttp3.Response response, int id) {
            return super.validateReponse(response, id);
        }

        @Override
        public void onError(okhttp3.Call call, Exception e, int id) {
            Log.e("TAG", "网络请求失败" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            processData(response);
            switch (id) {
//                请求成功
                case 100:
                    Log.i("http get method", "网络请求成功");
                    break;
                case 200:
                    break;
            }
        }
    }

    private void processData(String json) {
        if (!TextUtils.isEmpty(json)) {
            JSONObject jsonObject = JSON.parseObject(json);
            String code = jsonObject.getString("code");
            String msg = jsonObject.getString("msg");
            String data = jsonObject.getString("data");
            Log.i("tag", msg);
        }
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"推荐视频" ,"品牌热卖", "护肤洗面", "个人洗护", "面膜精华", "Jelly Bean", "KitKat", "Lolipop", "Marshmallow"};
        private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶", "姜饼", "蜂巢", "冰激凌三明治", "果冻豆", "奇巧巧克力棒", "棒棒糖", "棉花糖"};

        @Override
        public int getCount() {
            return versions.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(versions[position]);

            int witdh = getTextWidth(textView);
            int padding = DisplayUtil.dipToPix(mContext, 8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.3f) + padding);
            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new TextView(container.getContext());
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.GRAY);
            return convertView;
        }


        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }

    }


}