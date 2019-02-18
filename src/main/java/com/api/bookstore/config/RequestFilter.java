package com.api.bookstore.config;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.api.bookstore.controllers.UserResource;
import com.api.bookstore.services.JwtManager;

//@Component
@Order(1)
public class RequestFilter implements Filter {

	static final Logger LOG = Logger.getLogger(UserResource.class.getName());

	private final String LOGIN_URI = "/user/login";
	private final String AUTH_HEADER = "Authorization";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;


		if (req.getRequestURI().contains("h2") 
				| req.getRequestURI().contains("swagger")
				| req.getRequestURI().contains("api-docs"))
			chain.doFilter(request, response);
		else 
			if (req.getRequestURI().equals(LOGIN_URI))
			chain.doFilter(request, response);
		else {
			String token = req.getHeader(AUTH_HEADER);
			if (token != null) {
				if (!JwtManager.verifyJWT(token))
					((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
							"The token is not valid.");
				else
					chain.doFilter(request, response);
			} else
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, "The token not found.");
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
