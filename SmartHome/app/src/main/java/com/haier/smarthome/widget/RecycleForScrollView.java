package com.haier.smarthome.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by lp. 2018/8/24
 * Update:
 */

public class RecycleForScrollView extends RecyclerView {
    public RecycleForScrollView(Context context) {
        super(context);
    }

    public RecycleForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecycleForScrollView(Context context, AttributeSet attrs,
                                int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

