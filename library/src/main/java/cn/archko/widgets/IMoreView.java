package cn.archko.widgets;

import android.view.ViewGroup;

/**
 * @author: archko 2018/12/21 :17:28
 */
public interface IMoreView {
    /**
     * 初始状态，例如对应"点击加载更多"
     */
    int STATE_NORMAL = 1;
    /**
     * 正在上拉状态，例如对应"松开加载更多"
     */
    int STATE_PULLING = 2;
    /**
     * 正在加载状态，例如对应"正在加载..."
     */
    int STATE_LOADING = 3;
    /**
     * 加载失败状态，例如对应"加载失败，点击重试"
     */
    int STATE_LOAD_FAIL = 4;
    /**
     * 没有更多.
     */
    int STATE_NO_MORE = 5;

    /**
     * 实现该方法以返回一个 view，该 view 会加入到 ListView 的 footer 中作为"加载更多"的控件和用户进行交互。
     */
    ViewGroup getLoadMoreView();

    /**
     * 实现该方法以加载数据，当用户点击"加载更多"时会回调该方法。
     */
    void onLoadMore();

    /**
     * 实现该方法，监听不同的加载状态然后刷新你的 UI。
     */
    void onLoadingStateChanged(int state);
}
