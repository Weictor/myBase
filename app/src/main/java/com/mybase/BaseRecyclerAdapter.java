package com.mybase;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * Create by 俞智威
 * on 2016-06-07
 * 09:28
 * Procedure Explain:recycleView 的万能适配器
 */
public abstract class BaseRecyclerAdapter<T> extends
        RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas = new LinkedList<T>();
    protected int layoutId;
    public OnItemClickListener<T> mOnItemClickListener;

    public BaseRecyclerAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        if (datas != null) {
            mDatas = datas;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mDatas.size() > 0) {
            count = mDatas.size();
        }
        return count;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        showViewHolder(holder, position);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return getViewHolder(parent, viewType);
    }

    public void addItemLast(List<T> datas) {
        mDatas.addAll(datas);
    }

    public void addItemTop(List<T> datas) {
        mDatas = datas;
    }

    public void remove(int position) {
        mDatas.remove(position);
    }

    public void removeAll() {
        mDatas.clear();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<T> getDatas() {
        return mDatas;
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }


    protected abstract void showViewHolder(BaseRecyclerViewHolder holder, int position);

    /***
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract BaseRecyclerViewHolder getViewHolder(ViewGroup parent,
                                                            int viewType);

    // 点击事件接口
    public interface OnItemClickListener<T> {

        void onItemClick(View view, int position, T model);

        void onItemLongClick(View view, int position, T model);
    }

}
