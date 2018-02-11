package com.yandaoqiu.iwork.base.adapter.base;


/**
 *
 * @param <T>
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}
