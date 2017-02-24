package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wl
 * @date 2017/2/22
 * @content
 * 转圈等待
 * 加载完成 显示打勾的动画
 */

public class LoadingAnimView  extends View {
    private Paint mPaint;
    private int sweepAngle;
    private int startAngle;
    private boolean isLoading;

    public LoadingAnimView(Context context) {
        this(context,null);
    }

    public LoadingAnimView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isLoading) {

            canvas.save();
            mPaint.setColor(Color.BLUE);

            int width = getWidth() / 2;
            int height = getHeight() / 2;
            // canvas.drawCircle(width,height,width-5,mPaint);
            canvas.restore();
            //
            canvas.save();
            //画圆弧
            mPaint.setColor(Color.RED);
            RectF rectF = new RectF();
            rectF.set(5, 5, width * 2 - 5, height * 2 - 5);
            canvas.drawArc(rectF, startAngle, sweepAngle, false, mPaint);
            canvas.restore();
            mHandler.sendEmptyMessageDelayed(0, 200);
        }else{
            
        }

    }

    private Handler mHandler=new Handler(new Handler.Callback() {
        private boolean aBoolean;

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    invalidate();
                   sweepAngle+=10;
                      if (sweepAngle>120) {
                          aBoolean = true;
                      }
                    if (aBoolean){
                        startAngle += 10;

                    }
                    if (sweepAngle>360){
                        sweepAngle=0;
                    }
                    break;
            }
            return false;
        }
    });
}
