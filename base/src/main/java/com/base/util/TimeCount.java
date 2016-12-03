package com.base.util;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by 俞智威
 * on 2015-12-04.
 * 12:35
 * Procedure Explain:时间计时器
 */

public class TimeCount extends CountDownTimer {

    private Button button;

    public TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onFinish() {
        button.setText("重新获取");
        button.setClickable(true);

    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);
        button.setText("剩余" + millisUntilFinished / 1000 + "秒");
    }
}