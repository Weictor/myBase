package com.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.base.util.StatusBarUtil;
import com.bm.base.R;

/**
 * Create by 俞智威
 * on 2016-05-31
 * 08:26
 * Procedure Explain:
 */
public abstract class BaseActivity extends Activity {

    public static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(setLayoutResourceID());
        initWidget();
        initData();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.text_color));
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /***
     * 用于在初始化View之前做一些事
     */
    protected void init() {
    }

    /**
     * 初始化控件
     */
    protected void initWidget() {
    }

    protected abstract int setLayoutResourceID();

    /**
     * findViewById的封装
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T getView(int id) {
        return (T) super.findViewById(id);
    }


    protected void gotoActivity(Class<?> clazz) {
        if (clazz != null) {
            startActivity(new Intent(this, clazz));
        }
    }

    protected void gotoActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);

    }
}
