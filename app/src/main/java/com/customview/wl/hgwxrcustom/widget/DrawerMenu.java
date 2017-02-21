package com.customview.wl.hgwxrcustom.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wl
 * @date 2017/2/18
 * @content
 */

public class DrawerMenu  extends ViewGroup implements View.OnClickListener {
    private View fChild;
    private int fChildMeasuredWidth;
    private int fChildMeasuredHeight;
    private int fChildX;
    private int fChildY;
    private int childCount;
    private int fChildXL;
    private int fChildYL;
    private int fChildXR;
    private int fChildYR;
    private int measuredHeight;

    public DrawerMenu(Context context) {
        this(context,null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //initData();
    }

   /* private void initData() {

    }*/

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            measureChild(getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     *
     * @param isChange
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean isChange, int l, int t, int r, int b) {
        measuredHeight = getMeasuredHeight();
        if (isChange) {
            layoutBootom();
        }
    }

    private void layoutBootom() {

        for (int i = 0; i <childCount ; i++) {
            fChild = getChildAt(i);
            fChild.setOnClickListener(this);
            fChildMeasuredWidth = fChild.getMeasuredWidth();
            fChildMeasuredHeight = fChild.getMeasuredHeight();
            fChildXL = 0;
            fChildYL = measuredHeight - fChildMeasuredHeight*(i+1);
            fChildXR =fChildMeasuredWidth;
            fChildYR =measuredHeight - fChildMeasuredHeight*i;
            fChild.layout(fChildXL, fChildYL, fChildXR, fChildYR);
            if (i!=0){
                fChild.setVisibility(GONE);
            }
        }
    }

    @Override
    public void onClick(View view) {
        for (int i = 1; i < childCount; i++) {
            fChild = getChildAt(i);
            fChild.setVisibility(VISIBLE);
            ViewCompat.setX(fChild,-fChildMeasuredWidth);
            ViewCompat.animate(getChildAt(i)).translationX(0).setDuration(1000).start();
        }
    }
}
