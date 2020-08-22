package com.example.shoppingmall.user.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingmall.R;
import com.example.shoppingmall.user.bean.ItemBean;

import java.util.List;

public class ItemBtnAdapter extends RecyclerView.Adapter<ItemBtnAdapter.ItemBtnHolder> {
    private List<ItemBean> list;

    public ItemBtnAdapter(List<ItemBean> list) {
        this.list = list;
    }

    @NonNull
    @Override

    public ItemBtnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_mine_button, null);
        return new ItemBtnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBtnHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImageId());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemBtnHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ItemBtnHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_btn);
            name = itemView.findViewById(R.id.tv_btn);
        }
    }
}
