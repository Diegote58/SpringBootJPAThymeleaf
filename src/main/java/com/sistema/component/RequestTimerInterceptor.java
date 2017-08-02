package com.sistema.component;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sistema.repository.LogRepository;

@Component("requestTimerInterceptor")
public class RequestTimerInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	@Qualifier("logRepository")
	private LogRepository logRepository;
	
	private static final Log LOG = LogFactory.getLog(RequestTimerInterceptor.class);

	// Ejecuta antes de entrar en el metodo del controlador
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 */
	// Ejecuta antes de renderizar la vista.
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		long startTime = (long) request.getAttribute("startTime");
		String url = request.getRequestURL().toString();

		try {

			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username = "";
			if (auth != null && auth.isAuthenticated()) {
				username = auth.getName();
			}
			logRepository.save(new com.sistema.entity.Log(new Date(), auth.getDetails().toString(), username, url));
			
	
		} catch (Exception e) {
			LOG.debug("DEBUG: " + e.getMessage());
		}finally {
			LOG.info("Url to: '" + url + "' in: '" + (System.currentTimeMillis() - startTime) + " ms'");
			
		}	
	}

}
