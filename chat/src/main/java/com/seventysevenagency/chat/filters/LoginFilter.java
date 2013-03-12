package com.seventysevenagency.chat.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String url = req.getRequestURI().replace("/chat", "");
		if (!url.matches(".*(css|jpg|png|gif|js)")) {
			Integer userid = (Integer) req.getSession().getAttribute("userid");
			if (!url.matches("/login") && !url.matches("/register")) {
				if (userid == null) {
					// optionally, you may like to check if that attribute has a
					// valid userId as well
					resp.sendRedirect("login");
					return;
				}
			} else {
				System.out.println(userid + " in login filter");
				if (userid == null) {
					chain.doFilter(request, response);
					return;
				} else {
					resp.sendRedirect("chatroom");
					return;
				}
			}
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
