package com.customview.wl.recyclerview.adapter;

/**
 * @author wl
 * @date 2017/3/2
 * @content
 */
        import android.content.Context;
        import android.support.v4.util.Pools;
        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import java.util.List;

public class CommentPagerAdapter<D> extends PagerAdapter {
    private List<D> list;
    private int layoutId;
    private int variableId;
    private Pools.Pool<View> pool = new Pools.SimplePool<>(4);

    public CommentPagerAdapter(List<D> list, int layoutId, int variableId) {
        this.list = list;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = pool.acquire();
        if (view == null) {
            //view = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), layoutId, container, false).getRoot();
        }

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
        pool.release(view);
    }
}

