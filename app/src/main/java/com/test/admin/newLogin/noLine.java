package com.test.admin.newLogin;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

/**
 * Created by wuzhenge on 2016/12/12.
 */

public class noLine extends UnderlineSpan {

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(ds.linkColor);
        ds.setUnderlineText(false);
    }
}