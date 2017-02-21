package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wl
 * @date 2017/2/18
 * @content
 */

public class LoadingView extends View {
    private Paint mPaint;
    private int widthSize;
    private int heightSize;
    public  int sweepAngle;

    public LoadingView(Context context) {
        this(context,null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = connMeasure(widthMeasureSpec, 200);
        heightSize = connMeasure(widthMeasureSpec, 200);
        setMeasuredDimension(widthSize, heightSize);
    }

    private int connMeasure(int measureSpec,int defaultSize) {
        /**
         UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
         EXACTLY	当前的尺寸就是当前View应该取的尺寸  machparent
         AT_MOST	当前尺寸是当前View能取的最大尺寸    wrapcontent
         */

        int result=0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                result = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                result = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                result = size;
                break;
            }
        }
        return   result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        Rect rect = new Rect(0,0,widthSize,heightSize);
        canvas.drawRect(rect,mPaint);
        canvas.drawCircle(widthSize/2,heightSize/2,widthSize/2-10,mPaint);
        canvas.drawPoint(widthSize/2,heightSize/2,mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        RectF rectF = new RectF(getLeft()-20,getTop()-20,getRight()-20,getBottom()-20);
        canvas.drawArc(rectF,0.0f,sweepAngle=60,false,mPaint);//
        /**
         startAngle: 圆弧起始角度，单位为度。
         sweepAngle: 圆弧扫过的角度，顺时针方向，单位为度。
         useCenter: 如果为True时，在绘制圆弧时将圆心包括在内，通常用来绘制扇形。
         */
        canvas.restore();
    }
    public void  startAnim(int count){
        if (count!=-1){



        }
    }
}
