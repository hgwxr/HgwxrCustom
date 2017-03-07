package com.customview.wl.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wl on 2016/12/26.
 */

public abstract class BaseRecyclerAdapter<T extends Object> extends RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder> {
    protected List<T> mTList;
    protected   Context mContext;

    protected  BaseRecyclerAdapter.OnItemClickListener<T> itemClickListener;

    public void setItemClickListener(OnItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BaseRecyclerAdapter(Context mContext) {
        this.mTList = new ArrayList<>();
        this.mContext = mContext;

    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(viewType, null, false);

        return new BaseViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(final BaseViewHolder holder, int position) {
        final T data = mTList.get(position);
        bindDataToView(holder, data,position);
        holder.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(v,holder,data);
            }
        });
        holder.getItemView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClickListener.onLongClick(v,holder,data);
                return false;
            }
        });
    }

    protected abstract void bindDataToView(BaseViewHolder holder, T data, int position) ;

    public void add(T t,int index){
        mTList.add(index,t);
        notifyItemInserted(index);
    }
    public  void addAll(List<T>  tList){
         int start=mTList.size()-1;
        mTList.addAll(tList);
        notifyItemRangeInserted(start,mTList.size()-1);
    }
    @Override
    public int getItemCount() {
        return mTList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int TYPE = 0;

        TYPE=getItemType(position);
        return TYPE;
    }


    protected abstract @LayoutRes int getItemType(int position);

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class BaseViewHolder extends  RecyclerView.ViewHolder{


        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public View getItemView(){
            return itemView;
        }
        public  <T extends View> T getView( int id) {
            SparseArray<View> viewHolder = (SparseArray<View>) itemView.getTag();
            if (viewHolder == null) {
                viewHolder = new SparseArray<View>();
                itemView.setTag(viewHolder);
            }
            View childView = viewHolder.get(id);
            if (childView == null) {
                childView = itemView.findViewById(id);
                viewHolder.put(id, childView);
            }
            return (T) childView;
        }

    }
  public  interface OnItemClickListener<T>{
        void onClick(View view, BaseViewHolder holder, T data);
        void onLongClick(View view, BaseViewHolder holder, T data);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemSpanSize(position,gridLayoutManager);
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    protected abstract int getItemSpanSize(int position, GridLayoutManager gridLayoutManager);
}
