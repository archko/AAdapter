package cn.archko.test;

import android.view.View;

public interface OnItemClickListener<T> {

    void onItemClick(View view, T data, int position);

    void onItemClick2(View view, T data, int position);
}
