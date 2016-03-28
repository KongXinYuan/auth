package com.xuguruogu.auth.security;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 *
 * @author benli.lbl
 * @version $Id: CsrfSecurityRequestMatcher.java, v 0.1 Aug 30, 2015 12:47:17 AM
 *          benli.lbl Exp $
 */
@Component("apiCsrfSecurityRequestMatcher")
public class ApiCsrfSecurityRequestMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");

	private Pattern allowedUrls = Pattern.compile(".*\\.json|.*\\.form$");

	@Override
	public boolean matches(HttpServletRequest request) {
		return !(allowedMethods.matcher(request.getMethod()).matches()
				|| allowedUrls.matcher(request.getRequestURI()).matches());
	}

}
