package cn.archko.test.item;

import android.view.View;
import android.widget.TextView;

import cn.archko.adapter.R;
import cn.archko.adapters.BaseViewHolder;

/**
 * @author: archko 2019/5/30 :11:14
 */
public class TestViewHolder extends BaseViewHolder<TestBean> {

    TextView text;

    public TestViewHolder(View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text);
    }

    @Override
    public void onBind(TestBean data, int position) {
        text.setText(data.str);
    }
}
