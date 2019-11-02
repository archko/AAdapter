package cn.archko.test.item;

import cn.archko.adapters.item.IType;

/**
 * @author: archko 2019/5/30 :11:13
 */
public class TestBean implements IType {

    public static final int TYPE = 4;

    public String str;

    public TestBean(String s) {
        str = s;
    }

    @Override
    public int getViewType() {
        return TYPE;
    }
}
