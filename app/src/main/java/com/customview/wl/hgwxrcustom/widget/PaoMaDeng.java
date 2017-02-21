package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wl
 * @date 2017/2/21
 * @content
 */

public class PaoMaDeng extends TextView {
    private Paint mPaint;

    private List<String>  mapList;
    private String data;
    private  int currentIndex;
    private int widthSize;
    private int heightSize;
    private int zero;

    public PaoMaDeng(Context context) {
        this(context,null);
    }

    public PaoMaDeng(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PaoMaDeng(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        mPaint.setTextSize(50);
        mapList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mapList.add("测试数据+  "+i);
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentIndex++;
                setText(mapList.get(currentIndex == 10 ? getzero() : currentIndex));
                invalidate();
                break;
        }
        return true;
    }

    public int getzero() {
        currentIndex=0;
        return zero;
    }
}
