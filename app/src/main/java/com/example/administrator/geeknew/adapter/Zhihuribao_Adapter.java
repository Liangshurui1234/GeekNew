package com.example.administrator.geeknew.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.bean.Bannerbean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Zhihuribao_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
     private ArrayList<Bannerbean.TopStoriesBean>list;
         private ArrayList<Bannerbean.StoriesBean> dataBeans;
         private Context context;
         private String mDate="热门精选";

         public void setList(ArrayList<Bannerbean.TopStoriesBean> list) {
             this.list = list;
         }

         public void setDataBeans(ArrayList<Bannerbean.StoriesBean> dataBeans) {
             this.dataBeans = dataBeans;
         }

         public Zhihuribao_Adapter(ArrayList<Bannerbean.TopStoriesBean> list, ArrayList<Bannerbean.StoriesBean> dataBeans, Context context) {

             this.list = list;
             this.dataBeans = dataBeans;
             this.context = context;
         }
//梁书瑞
         @NonNull
         @Override
         public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
             LayoutInflater inflater = LayoutInflater.from(context);
             if (i == 0){
                 View inflate = inflater.inflate(R.layout.layout_banner, null);
                 return new AViewHolder(inflate);
             }else if (i == 1){
                 View inflate = inflater.inflate(R.layout.layout_text, null);
                 return new BViewHolder(inflate);
             }else {
                 View inflate = inflater.inflate(R.layout.layout_zhihu_leibiao, null);
                 return new CViewHolder(inflate);
             }


         }

         @Override
         public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
             int viewType = getItemViewType(i);
             if (viewType == 0){
                 AViewHolder bannerVH = (AViewHolder) viewHolder;
                 bannerVH.banner.setImages(list)
                         .setImageLoader(new ImageLoader() {
                             @Override
                             public void displayImage(Context context, Object path, ImageView imageView) {
                                 Bannerbean.TopStoriesBean bean = (Bannerbean.TopStoriesBean) path;
                                 RequestOptions options = new RequestOptions()
                                         .placeholder(R.drawable.ic_launcher_background);
                                 Glide.with(context).load(bean.getImage()).apply(options).into(imageView);
                             }
                         }).start();
             }else if (viewType == 1){
                 BViewHolder timeVH = (BViewHolder) viewHolder;
                 timeVH.text_1.setText(mDate);
             }else {
                 CViewHolder newsVH = (CViewHolder) viewHolder;
                 int newPosition = i-1;
                 if (list.size()>0){
                     newPosition-=1;
                 }
                 Bannerbean.StoriesBean storiesBean = dataBeans.get(newPosition);
                 newsVH.text.setText(storiesBean.getTitle());

                 List<String> images = storiesBean.getImages();
                 if (images != null && images.size()>0){
                     RequestOptions options = new RequestOptions()
                             .placeholder(R.mipmap.ic_launcher);
                     Glide.with(context).load(images.get(0)).apply(options).into(newsVH.img);
                 }
             }
         }

         @Override
         public int getItemViewType(int position) {
             if (list.size()>0){
                 if (position == 0){
                     return 0;
                 }else if (position == 1){
                     return 1;
                 }else {
                     return 2;
                 }
             }else {
                 if (position == 0){
                     return 1;
                 }else {
                     return 2;
                 }
             }
         }

         @Override
         public int getItemCount() {
            if(list.size()>0){
             return dataBeans.size()+2;
             }else{
                return dataBeans.size()+1;
            }
         }
         public void setData(Bannerbean bean) {
             mDate = bean.getDate();
             List<Bannerbean.StoriesBean> stories = bean.getStories();
             if (stories != null && stories.size()>0){
                 dataBeans.clear();
                 dataBeans.addAll(stories);
             }

             List<Bannerbean.TopStoriesBean> top_stories = bean.getTop_stories();
             if (top_stories != null && top_stories.size()>0){
                 list.clear();
                 list.addAll(top_stories);
             }

             notifyDataSetChanged();
         }
         public class AViewHolder extends RecyclerView.ViewHolder {
             Banner banner;
             public AViewHolder(@NonNull View itemView) {
                 super(itemView);
                 banner=itemView.findViewById(R.id.ban);
             }
         }
         public class BViewHolder extends RecyclerView.ViewHolder {
             TextView text_1;
             public BViewHolder(@NonNull View itemView) {
                 super(itemView);
                 text_1=itemView.findViewById(R.id.text_1);
             }
         }
         public class CViewHolder extends RecyclerView.ViewHolder {
             TextView text;
             ImageView img;
             public CViewHolder(@NonNull View itemView) {
                 super(itemView);
                 img=itemView.findViewById(R.id.img);
                 text=itemView.findViewById(R.id.tv_title);
             }
         }
}
