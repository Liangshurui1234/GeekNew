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
import com.example.administrator.geeknew.bean.Specialbean2;

import java.util.ArrayList;

public class Special_Adapter2 extends RecyclerView.Adapter<Special_Adapter2.ViewHolder> {

    private ArrayList<Specialbean2.StoriesBean> list;
    private Context context;

    public void setList(ArrayList<Specialbean2.StoriesBean> list) {
        this.list = list;
    }

    public Special_Adapter2(ArrayList<Specialbean2.StoriesBean> list, Context context) {

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
        final Specialbean2.StoriesBean shipqiiq=list.get(i);
        if(shipqiiq.getImages().size()>0&&shipqiiq.getImages()!=null){
            Glide.with(context).load(list.get(i).getImages().get(0)).into(viewHolder.img);
        }

        viewHolder.text_1.setText(list.get(i).getTitle());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScrollingActivity.class);
                intent.putExtra("name",shipqiiq.getId()+"");
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
        void Listeren(int i,Specialbean2.StoriesBean storiesBean);
    }
    private onimgListeren onimgListeren;

    public void setOnimgListeren(Special_Adapter2.onimgListeren onimgListeren) {
        this.onimgListeren = onimgListeren;
    }
}
