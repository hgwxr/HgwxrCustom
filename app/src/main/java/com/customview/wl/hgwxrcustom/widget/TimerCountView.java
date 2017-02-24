package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wl
 * @date 2017/2/24
 * @content
 */

public class TimerCountView  extends View {
    private Paint mPaint;
    private Rect mRect;
    private int DEFAULTSIZE;
    private int width;
    private int height;
    private long timeLong;
    private SimpleDateFormat simpleDateFormat;
    private String format="";

    public TimerCountView(Context context) {
        this(context,null);
    }

    public TimerCountView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimerCountView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mRect = new Rect();
        timeLong = System.currentTimeMillis();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        format=simpleDateFormat.format(new Date());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width =commMeasure(widthMeasureSpec);
        height =commMeasure(heightMeasureSpec);

    }
    private int commMeasure(int measureSpec) {
        int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int measure = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result=measure;
                break;
            default:
                result= DEFAULTSIZE;
                break;
        }
        return result;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRect.set(10, 10, width-10, height-10);
        mPaint.setStrokeWidth(width/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.drawColor(Color.GREEN);
        canvas.drawText(format,10,width/3,mPaint);
        canvas.restore();
        mHandler.sendEmptyMessage(0);

    }
    private Handler  mHandler=new Handler(new Handler.Callback() {


        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    timeLong-=1000;
                    format = simpleDateFormat.format(timeLong);
                    invalidate();
                    break;
            }
            return false;
        }
    });
}
