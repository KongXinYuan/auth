package com.xuguruogu.auth.interceptor;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xuguruogu.auth.web.result.ErrorResult;

@ControllerAdvice
public class KssExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(KssExceptionHandler.class);

	@Autowired
	ObjectMapper objectMapper;

	private Pattern allowedUrls = Pattern.compile("(.*\\.json$|^/api/.*)");

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(Throwable.class)
	public ModelAndView handleAnyException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws IOException {

		logger.warn("err", ex);

		if (allowedUrls.matcher(request.getServletPath()).matches()) {

			JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(response.getOutputStream(),
					JsonEncoding.UTF8);

			jsonGenerator.writeObject(new ErrorResult(ex));
			// return new ModelAndView(new MappingJackson2JsonView()).;
			return null;

		} else {
			return new ModelAndView("error/500");
		}
	}
}
