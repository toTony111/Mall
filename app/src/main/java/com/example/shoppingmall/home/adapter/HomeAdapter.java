package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoppingmall.R;
import com.example.shoppingmall.home.bean.BannerBean;
import com.example.shoppingmall.utils.Constants;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    public final LayoutInflater mLayoutInflater;

//    广告类型
    public final int BANNER=0;

//    视频类型
    public final int VEDIO=1;

//    当前类型
    public int currentType=BANNER;

    public HomeAdapter(Context context) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    /**
     * 根据位置得到当前的类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType=BANNER;
            case VEDIO:
                currentType=VEDIO;
//            默认为广告位置
            default:
                currentType=BANNER;
        }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    /**
     * 什么时候需要加上context
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==BANNER)
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.fg_home, null), mContext);
        if(viewType==VEDIO)
            return new VedioViewHolder(mLayoutInflater.inflate(R.layout.vedio_item,null),mContext);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;
        public Context mContext;

        public BannerViewHolder(View itemView, Context context) {
            super(itemView);
            banner = (Banner) itemView;
            this.mContext = context;
        }

/*
        public void setData(List<BannerBean> banners) {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //如果你想用自己项目的图片加载,那么----->自定义图片加载框架
            List<String> imageUris = new ArrayList<>();
            for (int i = 0; i < banners.size(); i++) {
                imageUris.add(banners.get(i).getImg_url());
            }
            banner.setImages(imageUris, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    */
/**
                     * 这里你可以根据框架灵活设置
                     *//*

                    Glide.with(mContext)
                            .load(Constants.BASE_BANNER + url)
                            .into(view);
                }
            });
        }
*/
    }

    private class VedioViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        private Context mContext;

        public VedioViewHolder(View itemView, Context mContext) {
            super(itemView);
//            tv_more_recommend = (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend = (GridView) itemView.findViewById(R.id.gv_vedio);
            this.mContext = mContext;
        }
    }
}
