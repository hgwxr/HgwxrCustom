package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wl
 * @date 2017/2/23
 * @content
 */

public class LoadiingRectView extends View {
    private Paint mPaint;
    private int DEFAULTSIZE;
    private int width;
    private int height;
    private Rect rect;
    private int one=2;
    private int two=4;
    private int three=4;
    private int four=2;
    public LoadiingRectView(Context context) {
        this(context,null);
    }

    public LoadiingRectView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadiingRectView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = commMeasure(widthMeasureSpec);
        height = commMeasure(heightMeasureSpec);
        rect = new Rect();


    }

    private int commMeasure(int measureSpec) {
         int result;
        int mode = MeasureSpec.getMode(measureSpec);
        int widthMeasure = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                result=widthMeasure;
                break;
            default:
                result= DEFAULTSIZE;
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //width/2,height/2,width/4,height


        canvas.save();
        mPaint.setColor(Color.GREEN);
        rect.set(width/4* 0,height/4*one,width/4*1,height);
        canvas.drawRect(rect,mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.RED);
        rect.set(width/4* 1,height/4*two,width/4*2,height);
        canvas.drawRect(rect,mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.YELLOW);
        rect.set(width/4* 2,height/4*three,width/4*3,height);
        canvas.drawRect(rect,mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setColor(Color.GRAY);
        rect.set(width/4* 3,height/4*four,width/4*4,height);
        canvas.drawRect(rect,mPaint);
        canvas.restore();
        mHandler.sendEmptyMessageDelayed(0,200);
    }
    private Handler mHandler=new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    invalidate();
                    if (one==4){
                        one=0;
                    }else{
                        one+=1;
                    }
                    if (two==4){
                        two=0;
                    }else{
                        two+=1;
                    }
                    if (three==4){
                        three=0;
                    }else{
                        three+=1;
                    }
                    if (four==4){
                        four=0;
                    }else{
                        four+=1;
                    }
                    break;
            }
            return false;
        }
    });
}
