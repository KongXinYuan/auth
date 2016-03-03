package com.xuguruogu.auth.util;

import java.util.List;

/**
 * 
 * @author benli.lbl
 * @version $Id: Converter.java, v 0.1 Aug 10, 2015 11:15:32 PM benli.lbl Exp $
 */
public interface Converter<A, B> {

    B convert(A source);

    List<B> converter(List<A> sourceList);
}
