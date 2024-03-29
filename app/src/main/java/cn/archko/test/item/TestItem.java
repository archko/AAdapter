package cn.archko.test.item;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import cn.archko.adapter.R;
import cn.archko.adapters.BaseViewHolder;
import cn.archko.adapters.item.AdapterItem;
import cn.archko.adapters.item.IType;

/**
 * @author: archko 2019/5/30 :13:48
 */
public class TestItem extends AdapterItem<StringBean> {

    public final static int TYPE = 1;

    public TestItem(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder<StringBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.test_viewholder2, parent, false));
    }

    @Override
    public void onBind(BaseViewHolder<StringBean> viewHolder, StringBean tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }

    static class ViewHolder extends BaseViewHolder<StringBean> {

        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }

        public void onBind(StringBean data, int position) {
            text.setText(data.str);
        }
    }
}