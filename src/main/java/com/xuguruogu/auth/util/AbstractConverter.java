package com.xuguruogu.auth.util;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<A, B> implements Converter<A, B> {

    abstract protected B doConvert(A source);

    @Override
    public final B convert(A source) {
        if (null == source) {
            return null;
        }
        try {
            return this.doConvert(source);
        } catch (Exception e) {
            throw new ConvertException("转换器异常", e);
        }
    }

    /**
     * @see Converter#converter(java.util.List)
     */
    @Override
    public List<B> converter(List<A> sourceList) {
        List<B> dis = new ArrayList<B>();
        B tmp = null;
        if (sourceList != null) {
            for (A ddi : sourceList) {
                tmp = this.convert(ddi);
                //不能将null加入集合，否则集合会含null的元素
                if (tmp != null) {
                    dis.add(tmp);
                }
            }
        }
        return dis;
    }

}