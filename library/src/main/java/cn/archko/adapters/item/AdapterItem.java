package cn.archko.adapters.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.adapters.BaseViewHolder;

/**
 * @author: archko 2019/5/30 :9:58
 */
public abstract class AdapterItem<T> {

    protected LayoutInflater inflater;

    public AdapterItem(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public abstract BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public void onBind(BaseViewHolder<T> viewHolder, T tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }

    public void onViewRecycled(@NonNull BaseViewHolder<T> holder) {

    }

    public void onViewAttachedToWindow(@NonNull BaseViewHolder<T> holder) {

    }

    public void onViewDetachedFromWindow(@NonNull BaseViewHolder<T> holder) {

    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

    }

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
    }
}
