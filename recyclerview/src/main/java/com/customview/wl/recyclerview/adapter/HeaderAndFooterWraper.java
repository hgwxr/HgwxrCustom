package com.customview.wl.recyclerview.adapter;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author wl
 * @date 2017/3/7
 * @content
 */

public class HeaderAndFooterWraper   extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


     private  SparseArrayCompat<View>  mHeaderViews=new SparseArrayCompat<>();
     private  SparseArrayCompat<View>  mFooterViews=new SparseArrayCompat<>();
     private  RecyclerView.Adapter mInnerAdapter;

    private final int  HEADER_ITEM= 10;
    private final int  FOOTER_ITEM= 20;
    public HeaderAndFooterWraper(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
    }

    public  void  addFooter(View view){
        mFooterViews.put(getFooterCount()+FOOTER_ITEM,view);
    }
    public  void  addHeader(View view){
        mHeaderViews.put(getHeaderCount()+HEADER_ITEM,view);
    }
    private  boolean isHeaderView(int position){
        return   position<getHeaderCount();
    }
    private  boolean isFooterView(int position){
        return   position>=getHeaderCount()+getRealCount();
    }
    private int getRealCount() {
        return  mInnerAdapter.getItemCount();
    }
    public   int  getHeaderCount(){
        return   mHeaderViews.size();
    }
    public  int getFooterCount(){
      return    mFooterViews.size();
    }

    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int itemViewType = getItemViewType(position);
                    if (mHeaderViews.get(itemViewType)!=null||mFooterViews.get(itemViewType)!=null){
                        return gridLayoutManager.getSpanCount();
                    }
                    if (position==9+getHeaderCount()){
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });

            //gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
        if (manager instanceof     StaggeredGridLayoutManager){

            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) manager;
            //staggeredGridLayoutManager.setSpanCount(1);
        }


    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
            if (isStaggeredGridLayout(holder)) {
                handleLayoutIfStaggeredGridLayout(holder, holder.getLayoutPosition());
            }

    }
    private boolean isStaggeredGridLayout(RecyclerView.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            return true;
        }
        return false;
    }

    protected void handleLayoutIfStaggeredGridLayout(RecyclerView.ViewHolder holder, int position) {
        if (isHeaderView(position) || isFooterView(position)) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();

            p.setFullSpan(true);
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position)){
            return mHeaderViews.keyAt(position);
        }else if (isFooterView(position)){
            return mFooterViews.keyAt(position - getHeaderCount() - getRealCount());
        }
        return mInnerAdapter.getItemViewType(position-getHeaderCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View header = mHeaderViews.get(viewType);
        if (header !=null){
            BaseRecyclerAdapter.BaseViewHolder viewHolder = new BaseRecyclerAdapter.BaseViewHolder(header);
            return viewHolder;
        }else {
            View footer = mFooterViews.get(viewType);
            if ( footer !=null){
                BaseRecyclerAdapter.BaseViewHolder viewHolder = new BaseRecyclerAdapter.BaseViewHolder(footer);
                return viewHolder;
            }
        }
        return mInnerAdapter.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (isHeaderView(position)||isFooterView(position)){
            return;
        }
        mInnerAdapter.onBindViewHolder(holder,position-getHeaderCount());
    }

    @Override
    public int getItemCount() {
        return getFooterCount()+getHeaderCount()+getRealCount();
    }
}
