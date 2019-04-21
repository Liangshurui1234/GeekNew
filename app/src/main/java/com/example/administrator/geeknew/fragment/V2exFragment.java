package com.example.administrator.geeknew.fragment;


import android.util.Log;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.base.BaseFragment;
import com.example.administrator.geeknew.bean.V2exTabsBean;
import com.example.administrator.geeknew.presenter.V2exP;
import com.example.administrator.geeknew.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author xts
 *         Created by asus on 2019/4/3.
 */
//梁书瑞
public class V2exFragment extends BaseFragment<V2exV,V2exP>
    implements V2exV{
    private static final String TAG="V2exFragment";
    private String mUrl="https://www.v2ex.com/";
    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect(mUrl).get();
                    Element select = document.select("div#Tabs").first();
                    Elements select1 = select.select("a[href]");

                    ArrayList<V2exTabsBean> v2exTabsBeans = new ArrayList<>();
                    for(Element element:select1){
                        String href = element.attr("href");
                        String text = element.text();
                        Log.d(TAG, "linkHref: "+href+",tab:"+text);
                        V2exTabsBean v2exTabsBean = new V2exTabsBean(href, text);
                        v2exTabsBeans.add(v2exTabsBean);

                    }
                    Elements items = document.select("div.cell.item");
                    for (Element item :items) {
                        //图片
                        Element image = item.select("table tr td a > img.avatar").first();
                        String src = image.attr("src");
                        //Log.d(TAG, "src: "+src);

                        //评论数量和评论链接地址
                        Element comment = item.select("table tbody tr td a.count_livid").first();
                        if (comment != null){
                            String href = comment.attr("href");
                            String text = comment.text();
                            //Log.d(TAG, "评论: "+",链接:"+href+",数量:"+text);
                        }
                        //标题
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        String text = title.text();
                        Log.d(TAG, "标题: "+text);

                        //topic_info
                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secondaryTab = topic.select("a.node").first();
                        String secTab = secondaryTab.text();
                        Log.d(TAG, "secTab: "+secTab);

                        String topicText = topic.text();
                        Log.d(TAG, "topicText: "+topicText);

                        Elements people = topic.select("strong > a");
                        if (people.size()>0){
                            //作者
                            Element element = people.get(0);
                            Log.d(TAG, "作者: "+element.text());
                        }

                        if (people.size()>1){
                            //最后的评论者
                            Element element = people.get(1);
                            Log.d(TAG, "最后的评论者: "+element.text());
                        }
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
