package com.mybase;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.base.interfaces.ShowData;
import com.base.volley.HttpConnect;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.shizhefei.mvc.MVCHelper;
import com.shizhefei.mvc.MVCSwipeRefreshHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener {

//    private RecyclerView recyclerView;
//    PullToRefreshListView pullToRefreshListView;
//    private List<TextBean> list;

    private MVCHelper<List<TextBean>> mvcHelper;
    private Adapter adapter;
    MeiZhuNotification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mvcHelper = new MVCSwipeRefreshHelper<List<TextBean>>(swipeRefreshLayout);
        // 设置数据源
        mvcHelper.setDataSource(new TextBeansDataSource(this));
        // 设置适配器
        mvcHelper.setAdapter(new Adapter(this));

        // 加载数据
        mvcHelper.refresh();


        notification = new MeiZhuNotification.Builder().setContext(MainActivity.this)
                .setTime(System.currentTimeMillis())
                .setImgRes(R.mipmap.ic_launcher)
                .setTitle("你收到了一条消息")
                .setContent("格局不够，读再多书也只能做一个屌丝").build();

//        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.listview);
//        reSet();
//        pullToRefreshListView.setMode(PullToRefreshListView.Mode.BOTH);
//        pullToRefreshListView.setAdapter(adapter);
//        pullToRefreshListView.setOnRefreshListener(this);
//
//        recyclerView = (RecyclerView) findViewById(R.id.recycle);
//        //设置布局管理器
//        recyclerView.setLayoutManager(layout);
//        //设置adapter
//        recyclerView.setAdapter(adapter);
//        //设置Item增加、移除动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        //添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
    }

    private void reSet() {
//        if(list.size()<15){
//            pullToRefreshListView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
//        }else
//            pullToRefreshListView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
    }


    public void clickme(View view) {
//        HttpService.findTranslationList(new ShowData<FindTranslationListBean>() {
//            @Override
//            public void showData(FindTranslationListBean findTranslationListBean) {
//                if (findTranslationListBean.isSuccess()) {
//                    Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
//                } else
//                    Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
//            }
//        }, new FindTranslationListPostBean("2B06414BC1F54AF4B438DC71DF7CC0A9", "",
//                "", "", "2", "8", ""));

//        HttpService.getCodeForUser(new ShowData<GetCodeForUserBean>() {
//            @Override
//            public void showData(GetCodeForUserBean getCodeForUserBean) {
//                if (getCodeForUserBean.isSuccess()) {
//
//                } else
//                    Toast.makeText(MainActivity.this, getCodeForUserBean.getMsg(), Toast.LENGTH_SHORT).show();
//            }
//        }, new GetCodeForUserPostBean("18640080104", 1));

        notification.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放资源
        mvcHelper.destory();
    }


    @Override
    public void onRefresh(PullToRefreshBase refreshView) {

    }
}
