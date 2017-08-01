package com.sistema.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component("requestTimerInterceptor")
public class RequestTimerInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(RequestTimerInterceptor.class);
	
	
	//Ejecuta antes de entrar en el metodo del controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}
	
	//Ejecuta antes de renderizar la vista.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		long startTime = (long) request.getAttribute("startTime");
		LOG.info("REQUEST URL: '" + request.getRequestURL().toString() + "' -- TOTAL TIME '"+ (System.currentTimeMillis() - startTime) +"' ms" );
	}

	

}
