package com.customview.wl.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.widget.TextView;

import com.customview.wl.recyclerview.R;

import java.util.HashMap;

/**
 * @author wl
 * @date 2017/3/7
 * @content
 */

public class DataAdapter extends  BaseRecyclerAdapter<HashMap<String,String>> {

    private int DEFAULT_SPAN_SIZE=1;

    public DataAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected void bindDataToView(BaseViewHolder holder, HashMap<String, String> map, int position) {

        if (position==9){

        }else {
            ((TextView) holder.getView(R.id.tx)).setText(map.get("data"));
        }
    }

    @Override
    protected int getItemType(int position) {
        if (position==9){
            return R.layout.layout_header;
        }
        return R.layout.layout_item;
    }

    @Override
    protected int getItemSpanSize(int position, GridLayoutManager gridLayoutManager) {
        if (position==9){
            return gridLayoutManager.getSpanCount();
        }

        return DEFAULT_SPAN_SIZE;
    }


}
