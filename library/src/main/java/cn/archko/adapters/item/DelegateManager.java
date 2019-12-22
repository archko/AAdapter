package cn.archko.adapters.item;

import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.adapters.BaseViewHolder;

/**
 * @author: archko 2018/12/17 :15:44
 */
public class DelegateManager<T extends IType> {

    private Map<Integer, AdapterItem<T>> viewTypeMap = new HashMap<>();

    public DelegateManager() {
    }

    public void addAdapterItem(int viewType, AdapterItem<T> adapterItem) {
        AdapterItem<T> old = viewTypeMap.get(viewType);
        if (null != old) {
            System.err.println("alread has the same type.");
        }
        viewTypeMap.put(viewType, adapterItem);
    }

    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterItem<T> adapterItem = viewTypeMap.get(viewType);
        return adapterItem.onCreateViewHolder(parent, viewType);
    }

    public int getItemViewType(List<T> data, int position) {
        IType itemBean = data.get(position);
        int vt = itemBean.getViewType();
        AdapterItem<T> adapterItem = viewTypeMap.get(vt);
        if (null != adapterItem) {
            return vt;
        }
        return 0;
    }

    public void onBindViewHolder(List<T> data, BaseViewHolder<T> viewHolder, int position) {
        AdapterItem<T> adapterItem = viewTypeMap.get(viewHolder.getItemViewType());
        adapterItem.onBind(viewHolder, data.get(position), position);
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
