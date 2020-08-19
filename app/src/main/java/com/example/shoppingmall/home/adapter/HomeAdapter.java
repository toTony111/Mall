package com.example.shoppingmall.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.youth.banner.Banner;

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
/*
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.banner_viewpager, null), mContext, resultBean);
*/
        if(viewType==VEDIO)
            return new VedioViewHolder(mLayoutInflater.inflate(R.layout.vedio_item,null),mContext);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    private class BannerViewHolder extends RecyclerView.ViewHolder {
        public Banner banner;
        public Context mContext;

        public BannerViewHolder(View itemView, Context context) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
            this.mContext = context;
        }

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
