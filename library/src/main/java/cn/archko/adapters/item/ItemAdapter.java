package cn.archko.adapters.item;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.adapters.BaseViewHolder;
import cn.archko.adapters.HeaderAndFooterRecyclerAdapter;

/**
 * @author: archko 2018/12/17 :15:44
 */
public class ItemAdapter<T extends IType> extends HeaderAndFooterRecyclerAdapter<T> {

    private DelegateManager<T> delegateManager;

    public ItemAdapter(Context context) {
        this(context, null);
    }

    public ItemAdapter(Context context, List<T> data) {
        super(context, data);
        delegateManager = new DelegateManager<T>();
    }

    public void addAdapterItem(int viewType, AdapterItem clazz) {
        delegateManager.addAdapterItem(viewType, clazz);
    }

    @Override
    public int doGetItemViewType(int position) {
        return delegateManager.getItemViewType(getData(), position);
    }

    @Override
    public BaseViewHolder<T> doCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void onBindNormalViewHolder(BaseViewHolder holder, int position, int realPosition) {
        delegateManager.onBindViewHolder(getData(), holder, realPosition);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        delegateManager.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        delegateManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        delegateManager.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        delegateManager.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        delegateManager.onDetachedFromRecyclerView(recyclerView);
    }
}
