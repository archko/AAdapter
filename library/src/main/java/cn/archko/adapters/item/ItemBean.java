package cn.archko.adapters.item;

/**
 * @author: archko 2019/5/29 :17:18
 */
public class ItemBean<T extends IType> {

    T data;

    public ItemBean(T data) {
        this.data = data;
    }

    public int getViewType() {
        return data.getViewType();
    }
}
