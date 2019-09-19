package com.example.qimokaoshi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qimokaoshi.R;
import com.example.qimokaoshi.bean.ListBean;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
   public List<ListBean.DataBean.DatasBean> datas=new ArrayList<>();
    public void getData(List<ListBean.DataBean.DatasBean> datas){
        if (datas!=null){
            this.datas.clear();
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }
    private Context context;

    public RecyAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        ListBean.DataBean.DatasBean datasBean = datas.get(i);//获取数据
        viewHolder.title.setText(datasBean.getTitle());//给控件数据
        Glide.with(context).load(datasBean.getEnvelopePic()).into(viewHolder.img);//加载图片
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {//点击事件
            @Override
            public void onClick(View v) {
                onClickListener.click(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_img);
            title = itemView.findViewById(R.id.tv_title);
        }
    }
    OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void click(int i);
    }
}
