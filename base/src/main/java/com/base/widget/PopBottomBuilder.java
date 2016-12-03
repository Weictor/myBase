package com.base.widget;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

/**
 * Create by 俞智威
 * on 2015-09-09
 * 13:48
 * Procedure Explain:底部弹出
 */
public class PopBottomBuilder {

    public static final String TAG = "PopBottomBuilder";

    private RelativeLayout rootView;
    private RelativeLayout.LayoutParams layoutParams;
    private TranslateAnimation translateAnimation;
    private View.OnClickListener onClickListener;

    //***************互斥*********************
    private int viewId;
    private View view;
    //***************互斥*********************


    public PopBottomBuilder() {
        translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f,
                Animation.RELATIVE_TO_SELF, 0f);
        translateAnimation.setDuration(500);
    }

    /**
     * 提交
     */
    public View commit(Activity activity) {


        if (rootView == null) {

            if (view == null) view = activity.getLayoutInflater().inflate(viewId, null);

            if (layoutParams == null) layoutParams = new RelativeLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            view.setLayoutParams(layoutParams);
            view.setClickable(true);

            rootView = new RelativeLayout(activity.getApplicationContext());
            rootView.setOnClickListener(onClickListener);
            rootView.setClickable(true);
            rootView.setLayoutParams(new ViewGroup.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            rootView.setBackgroundColor(Color.parseColor("#66000000"));
            rootView.addView(view);

        }

        ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        viewGroup.addView(rootView);
        view.startAnimation(translateAnimation);

        return rootView;
    }


    public PopBottomBuilder setLayout(int id) {
        viewId = id;
        view = null;
        return this;
    }


    public PopBottomBuilder setLayoutParams(RelativeLayout.LayoutParams layoutParams) {
        this.layoutParams = layoutParams;
        return this;
    }

    public PopBottomBuilder setLayout(View v) {
        view = v;
        viewId = -1;
        return this;

    }


    public PopBottomBuilder setOnCancel(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }


}
