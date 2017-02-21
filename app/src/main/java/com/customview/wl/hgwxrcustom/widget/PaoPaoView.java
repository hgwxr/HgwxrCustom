package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.customview.wl.hgwxrcustom.R;

/**
 * @author wl
 * @date 2017/2/21
 * @content
 */

public class PaoPaoView extends View {
    private Paint mPaint;
    private int width;
    private int height;
    private Point point1;
    private Point point2;
    private Point point3;

    private String textData;
    private int defaultHeight = 15;

    private int angleAlign = 0;

    private int textColor;
    private int textSize;
    private int bgColor;
    private final int LEFT = 0;
    private final int TOP = 1;
    private final int RIGHT = 2;
    private final int DOWN = 3;
    private RectF rectF;
    private Rect bounds;

    public PaoPaoView(Context context) {
        this(context, null);
    }

    public PaoPaoView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaoPaoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        /**
         * 获得我们所定义的自定义样式属性
         */
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PaoPaoView, defStyleAttr, 0);

        textColor = a.getColor(R.styleable.PaoPaoView_textColor, Color.BLACK);
        angleAlign = a.getInt(R.styleable.PaoPaoView_align, LEFT);
        bgColor = a.getColor(R.styleable.PaoPaoView_backgroudColor, Color.WHITE);
        textData = a.getString(R.styleable.PaoPaoView_text);
        // 默认设置为16sp，TypeValue也可以把sp转化为px
        textSize = a.getDimensionPixelSize(R.styleable.PaoPaoView_textSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
        a.recycle();

    }

    private void init() {
        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 防抖动
        mPaint.setDither(true);
        point1 = new Point();
        point2 = new Point();
        point3 = new Point();
    }

    private int connMeasure(int measureSpec, int defaultSize) {
        /**
         UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
         EXACTLY	当前的尺寸就是当前View应该取的尺寸  machparent
         AT_MOST	当前尺寸是当前View能取的最大尺寸    wrapcontent
         */

        int result = 0;
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
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //p3(width/2,height+defaultHeight)  p2(width-(defaultHeight/Math.sqrt(3)),height) p1(width-(defaultHeight/Math.sqrt(3)),height)
        switch (angleAlign) {
            case LEFT:
                width = connMeasure(widthMeasureSpec, 200) - defaultHeight;
                height = connMeasure(heightMeasureSpec, 200);
                point1.x = defaultHeight;
                point1.y = (int) (height / 2 - (defaultHeight / Math.sqrt(3)));
                point2.x = defaultHeight;
                point2.y = (int) (height / 2 + (defaultHeight / Math.sqrt(3)));
                point3.x = 0;
                point3.y = height / 2;

                break;
            case TOP:
                width = connMeasure(widthMeasureSpec, 200);
                height = connMeasure(heightMeasureSpec, 200) - defaultHeight;

                point1.x = (int) (width / 2 - (defaultHeight / Math.sqrt(3)));
                point1.y = defaultHeight;
                point2.x = (int) (width / 2 + (defaultHeight / Math.sqrt(3)));
                point2.y = defaultHeight;
                point3.x = width / 2;
                point3.y = 0;
                break;
            case RIGHT:
                width = connMeasure(widthMeasureSpec, 200) - defaultHeight;
                height = connMeasure(heightMeasureSpec, 200);
                point1.x = width;
                point1.y = (int) (height / 2 - (defaultHeight / Math.sqrt(3)));
                point2.x = width;
                point2.y = (int) (height / 2 + (defaultHeight / Math.sqrt(3)));
                point3.x = width + defaultHeight;
                point3.y = height / 2;
                break;
            case DOWN:
                width = connMeasure(widthMeasureSpec, 200);
                height = connMeasure(heightMeasureSpec, 200) - defaultHeight;
                int x1 = (int) (width / 2 - (defaultHeight / Math.sqrt(3)));
                int x2 = (int) (width / 2 + (defaultHeight / Math.sqrt(3)));
                point1.x = x1;
                point1.y = height;
                point2.x = x2;
                point2.y = height;
                point3.x = width / 2;
                point3.y = height + defaultHeight;
                break;
        }



    }

    /**
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        canvas.save();

        /**
         【功能说明】该方法用于在画布上绘制圆角矩形，通过指定RectF对象以及圆角半径来实现。该方法是绘制圆角矩形的主要方法，同时也可以通过设置画笔的空心效果来绘制空心的圆角矩形。
         【基本语法】public void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
         参数说明
         rect：RectF对象。
         rx：x方向上的圆角半径。
         ry：y方向上的圆角半径。
         paint：绘制时所使用的画笔。
         */
        mPaint.setColor(bgColor);


        if (rectF == null) {
            if (angleAlign == LEFT)
                rectF = new RectF(defaultHeight, 0, width, height);
            else if (angleAlign == TOP)
                rectF = new RectF(0, defaultHeight, width, height);
            else rectF = new RectF(0, 0, width, height);
        }
        canvas.drawRoundRect(rectF, 10, 10, mPaint);
        canvas.restore();
        canvas.save();
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(point3.x, point3.y);
        path.lineTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        canvas.drawPath(path, mPaint);
        canvas.restore();
        canvas.save();

        int baseLineX = 0;
        if (angleAlign == LEFT)
            baseLineX = defaultHeight * 2;
        int baseLineY = Math.min(width / 2, height / 2);
        mPaint.setColor(textColor == 0 ? Color.BLACK : textColor);
//        mPaint.getTextBounds(textData,0,textData.length(), bounds);
        mPaint.setTextSize(Math.min(width / textData.length(), height / textData.length()));
        canvas.drawText(textData, baseLineX, baseLineY, mPaint);
        canvas.restore();

    }
}
