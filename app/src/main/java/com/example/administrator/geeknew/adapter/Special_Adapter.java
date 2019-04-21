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
import com.example.administrator.geeknew.Activity.SpecialActivity;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.bean.Specialbean;

import java.util.ArrayList;

public class Special_Adapter extends RecyclerView.Adapter<Special_Adapter.ViewHolder>{

    private ArrayList<Specialbean.DataBean>list;
    private Context context;

    public void setList(ArrayList<Specialbean.DataBean> list) {
        this.list = list;
    }

    public Special_Adapter(ArrayList<Specialbean.DataBean> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_special, null);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Specialbean.DataBean shipqiiq=list.get(i);
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHolder.img);
        viewHolder.text.setText(list.get(i).getDescription());
        viewHolder.text_1.setText(list.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SpecialActivity.class);
                intent.putExtra("id",shipqiiq.getId()+"");
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
        TextView text;
        TextView text_1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
            text_1=itemView.findViewById(R.id.text_1);
        }
    }
    public interface onimgListeren{
        void Listeren(int i,Specialbean.DataBean spqi);
    }
    private onimgListeren onimgListeren;

    public void setOnimgListeren(Special_Adapter.onimgListeren onimgListeren) {
        this.onimgListeren = onimgListeren;
    }
}
