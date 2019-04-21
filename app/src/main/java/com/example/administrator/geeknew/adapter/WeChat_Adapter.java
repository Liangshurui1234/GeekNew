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
import com.example.administrator.geeknew.Activity.Main2Activity;
import com.example.administrator.geeknew.Activity.ScrollingActivity;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.bean.Specialbean2;
import com.example.administrator.geeknew.bean.WeChatbean;

import java.util.ArrayList;

public class WeChat_Adapter extends RecyclerView.Adapter<WeChat_Adapter.ViewHolder> {
    private ArrayList<WeChatbean.NewslistBean> list;
    private Context context;

    public void setList(ArrayList<WeChatbean.NewslistBean> list) {
        this.list = list;
    }

    public WeChat_Adapter(ArrayList<WeChatbean.NewslistBean> list, Context context) {

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
        final WeChatbean.NewslistBean shipqiiq=list.get(i);
        if(shipqiiq.getPicUrl().length()>0&&shipqiiq.getPicUrl()!=null){
            Glide.with(context).load(list.get(i).getPicUrl()).into(viewHolder.img);
        }
        viewHolder.text_1.setText(list.get(i).getTitle());
        viewHolder.text_2.setText(list.get(i).getDescription());
        viewHolder.text_3.setText(list.get(i).getCtime());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("url",shipqiiq.getUrl());
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
        TextView text_2;
        TextView text_3;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);

            text_1=itemView.findViewById(R.id.text_1);
            text_2=itemView.findViewById(R.id.text_2);
            text_3=itemView.findViewById(R.id.text_3);
        }
    }
}
