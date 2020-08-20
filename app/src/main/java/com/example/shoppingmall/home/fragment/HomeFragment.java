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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.shoppingmall.R;
import com.example.shoppingmall.base.BaseFragment;
import com.example.shoppingmall.home.bean.BannerBean;
import com.example.shoppingmall.home.bean.VideoBean;
import com.example.shoppingmall.home.bean.VideoTypeBean;
import com.example.shoppingmall.utils.Constants;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Arrays;
import java.util.List;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {
    private IndicatorViewPager indicatorViewPager;
    private View home;
    private Banner bannerView;
    private List<BannerBean> banners;
    private List<VideoTypeBean> videoType;
    private List<VideoBean> videoList;
    /*滚动条和视频列表*/
    private ScrollIndicatorView scrollIndicatorView;
    private ViewPager viewPager;

    @Override
    public View initView() {
        /*动态加载片段*/
        home = View.inflate(mContext, R.layout.fg_home, null);
        viewPager = home.findViewById(R.id.moretab_viewPager);
        scrollIndicatorView = home.findViewById(R.id.siv_video_type);
        return home;
    }


    @Override
    public void initData() {

        //请求网络
        getDataFromNet();

        /*绑定tab与viewpager*/
        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xffed5757, Color.GRAY).setSize(selectSize, unSelectSize));
        scrollIndicatorView.setScrollBar(new ColorBar(mContext, 0xffed5757, 4));
        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
/*        myAdapter.setVideoType(videoType);
        myAdapter.setVideoList(videoList);*/
        indicatorViewPager.setAdapter(new MyAdapter());


    }

    private void getDataFromNet() {
        GetBuilder getBuilder = OkHttpUtils.get();
        /*banner请求*/
            getBuilder
                .url(Constants.BASE_BANNER)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "网络请求失败" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        switch (id) {
                            /*请求成功*/
                            case 0:
                                if (response != null) {
                                    processData(response, R.id.banner);
                                    bannerView = home.findViewById(R.id.banner);
                                    bannerView.setAdapter(new BannerImageAdapter<BannerBean>(banners) {
                                        @Override
                                        public void onBindView(BannerImageHolder holder, BannerBean data, int position, int size) {
                                            //图片加载自己实现
                                            Glide.with(holder.itemView)
                                                    .load(data.getImg_url())
                                                    .thumbnail(Glide.with(holder.itemView).load(R.drawable.loading))
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                                                    .into(holder.imageView);
                                        }
                                    });
                                    bannerView.setIndicator(new RoundLinesIndicator(mContext));
                                    bannerView.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));
                                    break;
                                }
                            case 200:
                                break;
                        }
                    }
                });
    }


    private void processData(String response, int i) {
        JSONObject jsonObject = JSONObject.parseObject(response);
        String data = jsonObject.getString("data");
        if (!TextUtils.isEmpty(response)) {
            switch (i){
                case R.id.banner:
                    banners = JSON.parseArray(data,BannerBean.class);
                    break;
                case R.id.siv_video_type:
                    videoType = JSON.parseArray(data,VideoTypeBean.class);
                    break;
                case R.id.moretab_viewPager:
                    videoList = JSON.parseArray(data,VideoBean.class);
                    break;
            }
        }
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"推荐","精华","推荐","精华", "Honeycomb", "Ice Cream Sandwich", "Jelly Bean", "KitKat", "Lolipop", "Marshmallow"};
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
            int padding = DisplayUtil.dipToPix(getActivity(), 8);
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