package com.mybase;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.base.util.StatusBarUtil;

public class MainActivity extends AppCompatActivity {

//    private RecyclerView recyclerView;
//    PullToRefreshListView pullToRefreshListView;
//    private List<TextBean> list;

    MeiZhuNotification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setColor(this, getResources().getColor(com.bm.base.R.color.actionsheet_blue));

        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);


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
}
