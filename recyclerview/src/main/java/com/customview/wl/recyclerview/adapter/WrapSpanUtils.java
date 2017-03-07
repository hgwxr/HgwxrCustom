package com.customview.wl.recyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author wl
 * @date 2017/3/7
 * @content
 */

public class WrapSpanUtils {

    private static WrapSpanUtils wrapSpanUtils;

    private    WrapSpanUtils(){}
    public static synchronized WrapSpanUtils getInstance(){

        if (wrapSpanUtils==null){
            wrapSpanUtils  = new WrapSpanUtils() ;
        }
        return wrapSpanUtils;
    }
    private void setSpanSize(RecyclerView recyclerView){
      /*  RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (mHeaderViews.get(itemViewType)!=null||mFooterViews.get(itemViewType)!=null){
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
            //gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }*/
    }
    /*public  void hadSetting(){
        if (position==9){
            return gridLayoutManager.getSpanCount();
        }
    }*/
}
