package com.xuguruogu.auth.web.result;

import java.util.HashMap;

public class ErrorResult extends HashMap<String, Object> {

	/**  */
	private static final long serialVersionUID = -4383865714669881932L;

	public ErrorResult(Exception ex) {
		this.put("hasError", true);
		this.put("error", ex.getMessage());
	}
}
