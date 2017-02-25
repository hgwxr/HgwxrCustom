package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @author wl
 * @date 2017/2/24
 * @content
 */

public class NinePartView extends View {
    private Paint mPaint;
    private int width;
    private int height;

    public NinePartView(Context context) {
        this(context,null);
    }

    public NinePartView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NinePartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //ImageView
        canvas.save();

        //canvas.drawBitmap();

        canvas.restore();
    }
}
