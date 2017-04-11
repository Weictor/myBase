package com.mybase;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.base.interfaces.GetData;
import com.base.util.StatusBarUtil;
import com.base.volley.HttpConnect;
import com.base.widget.MeiZhuNotification;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GetData<BaseBean> {

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
        HttpConnect.init(getApplicationContext());

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
        Map<String, String> map = new HashMap<>();
        map.put("token", "2B06414BC1F54AF4B438DC71DF7CC0A9");
        map.put("level", "2");
        map.put("languageid", "8");
        HttpService.findTranslationList(1, this,
                new FindTranslationListPostBean("2B06414BC1F54AF4B438DC71DF7CC0A9", "",
                        "", "", "2", "8", ""), map);

//        notification.show();

    }

    public void clickme1(View view) {

        HttpService.getCodeForUser(2, this, new GetCodeForUserPostBean("18640080104", 1));

    }

    @Override
    public void getData(BaseBean baseBean, int tag) {
        if (tag == 1) {
            FindTranslationListBean findTranslationListBean = (FindTranslationListBean) baseBean;
            if (findTranslationListBean.isSuccess()) {
                Toast.makeText(MainActivity.this, "成功", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
        } else if (tag == 2) {
            GetCodeForUserBean getCodeForUserBean = (GetCodeForUserBean) baseBean;
            if (getCodeForUserBean.isSuccess()) {
                Toast.makeText(MainActivity.this, getCodeForUserBean.getData() + "", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(MainActivity.this, getCodeForUserBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
//        if (baseBean instanceof FindTranslationListBean) {
//
//        } else if (baseBean instanceof GetCodeForUserBean) {
//
//        }

    }
}
