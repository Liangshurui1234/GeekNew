package com.example.administrator.geeknew.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.geeknew.Activity.ScrollingActivity;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.bean.Hotbean;

import java.util.ArrayList;

public class Hot_Adapter extends RecyclerView.Adapter<Hot_Adapter.ViewHolder> {

    private ArrayList<Hotbean.RecentBean> list;
    private Context context;

    public void setList(ArrayList<Hotbean.RecentBean> list) {
        this.list = list;
    }

    public Hot_Adapter(ArrayList<Hotbean.RecentBean> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_special2, null);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Hotbean.RecentBean hot=list.get(i);

            Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.img);


        viewHolder.text_1.setText(list.get(i).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScrollingActivity.class);
                intent.putExtra("name",hot.getNews_id()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;

        TextView text_1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);

            text_1=itemView.findViewById(R.id.text_1);
        }
    }
    public interface onimgListeren{
        void Listeren(int i,Hotbean.RecentBean storiesBean);
    }
    private onimgListeren onimgListeren;

    public void setOnimgListeren(Hot_Adapter.onimgListeren onimgListeren) {
        this.onimgListeren = onimgListeren;
    }
}
