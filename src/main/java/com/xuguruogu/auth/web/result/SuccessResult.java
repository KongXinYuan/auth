package com.xuguruogu.auth.web.result;

import java.util.HashMap;

public class SuccessResult extends HashMap<String, Object> {

    /**  */
    private static final long serialVersionUID = -4383865714669881932L;

    public SuccessResult() {
        this.put("success", true);
    }
}
