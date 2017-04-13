package com.base.widget;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Create by 俞智威
 * on 2016-11-25
 * 16:23
 * Procedure Explain: 长按view
 */

public class LongTouchView extends ImageView {
    public static final String TAG = "RepeatButton";
    private long mStart;//长按开始时间
    private int mRepeatCount;//长按重复次数
    private LongListener mListener;//
    private long mInterval = 500;//长按一次持续时间

    public LongTouchView(Context context) {
        super(context);
//        Log.d(TAG, "RepeatButton1");
    }

    public LongTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        Log.d(TAG, "RepeatButton");
        setFocusable(true); //设置焦点
        setLongClickable(true); //启用长按事件，长按后会执行performLongClick()
    }

    public LongTouchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
//        Log.d(TAG, "RepeatButton3");
    }

    private Runnable mThread = new Runnable() {
        public void run() {
//            Log.d(TAG, "mRepeaterThread run()");
            doRepeat(false);
            if (isPressed()) {
//                Log.d(TAG, "mRepeaterThread run() press");
                postDelayed(this, mInterval);//延迟mInterval后执行当前线程
            }
        }
    };

    /**
     * @param end 表示最后一次长按，即结束长按事件
     */
    private void doRepeat(boolean end) {
//        Log.d(TAG, "mRepeaterThread run() end=" + end);
        long now = SystemClock.elapsedRealtime();//获取当前时间
        if (mListener != null) {
            mListener.onLongTouch(this, now - mStart, end ? -1 : mRepeatCount++);
        }
    }

    /**
     * 长按结束
     */
    private void endRepeat() {
        doRepeat(true);
        mStart = 0;
    }

    /**
     * 设置长按监听事件，初始化mInterval
     */
    public void setLongListener(LongListener listener, long interval) {
//        Log.d(TAG, "setRepeatListener interval=" + interval);
        mListener = listener;
        mInterval = interval;
    }

    @Override
    public boolean performLongClick() {
//        Log.d(TAG, "performLongClick");
        mStart = SystemClock.elapsedRealtime();//获取系统当前时间
        mRepeatCount = 0;
        post(mThread);//调用post()方法，执行mThread
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
//            Log.d(TAG, "onTouchEvent UP");
            removeCallbacks(mThread);//删除队列当中未执行的线程对象
            if (mStart != 0) {
                endRepeat();
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //Log.d(TAG, "onKeyDown keyCode=" + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                super.onKeyDown(keyCode, event);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        Log.d(TAG, "onKeyUp keyCode=" + keyCode);
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_CENTER:
            case KeyEvent.KEYCODE_ENTER:
                removeCallbacks(mThread);
                if (mStart != 0) {
//                    Log.d(TAG, "onKeyUp mStart=" + mStart);
                    endRepeat();
                }
                super.onKeyUp(keyCode, event);
                return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    public interface LongListener {
        /**
         * manpeng
         *
         * @param v           用户传入的Button对象
         * @param duration    延迟的毫秒数
         * @param repeatcount 重复次数回调
         */
        void onLongTouch(View v, long duration, int repeatcount);
    }

}
