package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.customview.wl.hgwxrcustom.R;

/**
 * @author wl
 * @date 2017/2/24
 * @content
 * 仿微信朋友圈 自定义view布局
 */

public class NinePartView extends View {
    private Paint mPaint;
    private int width;
    private int height;
    private Bitmap mBitmap;
    private BitmapShader bitmapShader;
    private Rect mRect;
    private RectF mRectF;
    private int mScaleWidth;
    private int mScaleHeight;
    private RectF rectF;
    private RectF rectFDown;
    private Paint mPaintBackGround;

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
        mBitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        mPaintBackGround = new Paint();
        mPaintBackGround.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
      /* mRectF = new RectF(width/4*3,height/4*3,width-20,height);
        mScaleWidth =width/4-20/mBitmap.getWidth();
        mScaleHeight =width/4-20/mBitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setTranslate(width/4*3,height/4*3);
        matrix.setScale(mScaleWidth, mScaleHeight);
        bitmapShader.setLocalMatrix(matrix);*/
        Matrix matrix = new Matrix();
        matrix.setTranslate(width/4*3,height/4*3-mBitmap.getHeight()/2);
        bitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(bitmapShader);
        rectF=new RectF(0,0,width,height/4*3+height/4*(2/4));
        rectFDown = new RectF(0,height/4*3,width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mPaintBackGround.setColor(Color.GRAY);
        canvas.drawRect(rectF,mPaintBackGround);
        canvas.restore();
        canvas.save();
        mPaintBackGround.setColor(Color.BLUE);
        canvas.drawRect(rectFDown,mPaintBackGround);
        canvas.restore();
        //ImageView
        canvas.save();

        //canvas.drawBitmap();
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
       // canvas.drawRect(mRectF,mPaint);
        canvas.restore();

    }
}
