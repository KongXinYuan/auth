package com.xuguruogu.auth.util;

/**
 * 转换器异常
 * 
 * @author benli.lbl
 * @version $Id: ConvertException.java, v 0.1 Aug 10, 2015 11:17:41 PM benli.lbl Exp $
 */
public class ConvertException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = 1049271719305926377L;

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }
}
