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
public class TestItem2 extends AdapterItem<StringBean2> {

    public final static int TYPE = 2;

    public TestItem2(Context context) {
        super(context);
    }

    public BaseViewHolder<StringBean2> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder2(inflater.inflate(R.layout.test_viewholder2, parent, false));
    }

    public void onBind(BaseViewHolder<StringBean2> viewHolder, StringBean2 tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }

    static class ViewHolder2 extends BaseViewHolder<StringBean2> {

        TextView text;

        public ViewHolder2(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            text.setTextColor(text.getContext().getResources().getColor(R.color.colorAccent));
        }

        public void onBind(StringBean2 data, int position) {
            text.setText(data.str);
        }
    }
}
