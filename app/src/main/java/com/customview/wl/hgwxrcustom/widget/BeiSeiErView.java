package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author wl
 * @date 2017/2/18
 * @content
 */

public class BeiSeiErView extends View {
    private Paint mPaint;
    private Point mPoint1;//控制点
    private Point mPoint2;
    private Point mPoint3;
    private Path mPath;

    public BeiSeiErView(Context context) {
        this(context,null);
    }

    public BeiSeiErView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BeiSeiErView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        mPoint1 = new Point(400,300);
        mPoint2 = new Point(100,700);
        mPoint3 = new Point(700,1000);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLUE);
        canvas.save();
        mPaint.setStrokeWidth(5);
        canvas.drawLine(0,0,getWidth(),getHeight(),mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setStrokeWidth(10);
        canvas.translate(100,100);
        canvas.drawLine(mPoint1.x,mPoint1.y,mPoint2.x,mPoint2.y,mPaint);
        canvas.drawLine(mPoint1.x,mPoint1.y,mPoint3.x,mPoint3.y,mPaint);
        //canvas.drawLine(mPoint3.x,mPoint3.y,mPoint2.x,mPoint2.y,mPaint);
        mPath.reset();
        mPath.moveTo(mPoint2.x,mPoint2.y);
        mPath.quadTo(mPoint1.x,mPoint1.y,mPoint3.x,mPoint3.y);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(mPath,mPaint);//画路劲
        //画控制点
        mPaint.setStrokeWidth(30);
        canvas.drawPoint(mPoint1.x,mPoint1.y,mPaint);
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                mPoint1.x= (int) event.getX();
                mPoint1.y= (int) event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
