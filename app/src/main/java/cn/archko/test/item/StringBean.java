package cn.archko.test.item;

import cn.archko.adapters.item.IType;

/**
 * @author: archko 2019/5/30 :13:54
 */
public class StringBean implements IType {

    public String str;

    public StringBean(String str) {
        this.str = str;
    }

    @Override
    public int getViewType() {
        return TestItem.TYPE;
    }
}