package com.example.administrator.geeknew.Activity;



import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.administrator.geeknew.R;
import com.example.administrator.geeknew.base.BaseActivity;
import com.example.administrator.geeknew.presenter.EmptyP;
import com.example.administrator.geeknew.view.EmptyV;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CalendarActivity extends BaseActivity<EmptyP, EmptyV> implements EmptyV {


    @BindView(R.id.calendarView)
    MaterialCalendarView calendarView;
    @BindView(R.id.tab)
    Toolbar tab;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getActivityId() {
        return R.layout.activity_calendar;
    }

    public CalendarDay mdate;

    @Override
    protected void initData() {
        tab.setTitle("");
        setSupportActionBar(tab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//左侧添加一个默认的返回图标
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {


            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mdate=date;
            }
        });
    }

    //设置监听事件,点击返回按钮则退出当前页面
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }


    @OnClick(R.id.text)
    public void onViewClicked() {
        Date date = mdate.getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy--MM--dd");
        String format1 = format.format(date);
        char[] chars = format1.toCharArray();
        String str = "";
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                str+=chars[i];
            }
        }
        Intent intent = getIntent();
        intent.putExtra("date",str);
        setResult(20,intent);
        finish();
    }
}
