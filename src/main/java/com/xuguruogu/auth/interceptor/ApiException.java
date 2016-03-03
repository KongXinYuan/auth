package com.xuguruogu.auth.interceptor;

public class ApiException extends RuntimeException {

    /**  */
    private static final long serialVersionUID = 3456375511900395518L;

    public ApiException(String message) {
        super(message);
    }
}
