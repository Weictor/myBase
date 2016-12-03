package com.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by 俞智威
 * on 2016-02-01.
 * 11:29
 * Procedure Explain:
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = "BaseFragment";

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            createView(rootView);
        }

        initData();
        initWidget();

        refreshData(rootView);

        return rootView;
    }

    /**
     * 初始化数据
     */
    public void initData() {

    }

    /**
     * 初始化控件
     */
    public void initWidget() {

    }

    public View getRootView() {
        return rootView;
    }

    public <T extends View> T getView(int id) {
        return (T) rootView.findViewById(id);
    }

    /**
     * 资源id
     */
    public abstract int getLayoutId();

    /**
     * 创建view
     */
    public abstract void createView(View rootView);

    /**
     * 刷新数据
     */
    public abstract void refreshData(View rootView);

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (rootView != null) {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            viewGroup.removeAllViews();
        }


    }
}
