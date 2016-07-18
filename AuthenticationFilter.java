package com.mybiletix.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mybiletix.Constants;
import com.mybiletix.UserData;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("*.xhtml")
public class AuthenticationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthenticationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String currentUrl = httpRequest.getRequestURL().toString();
		
		if (currentUrl.contains(Constants.MAIN_SCREEN)) {// devam et login sayfasýna gecme
			// ignore resources
			chain.doFilter(request, response);
			return;
		}
		
		if (currentUrl.contains(Constants.REGISTER_SCREEN)) {
			// ignore resources
			chain.doFilter(request, response);
			return;
		}
		
		if (currentUrl.contains(Constants.RESOURCE_PATH)) {
			// ignore resources
			chain.doFilter(request, response);
			return;
		}

		UserData userData = (UserData) session.getAttribute(Constants.USER_DATA);

		if (currentUrl.contains(Constants.LOGIN_PAGE)) {
			if (userData == null) {
				// continue to login
				chain.doFilter(request, response);
			} else {
				// redirect to default page because of already logged in
				if (userData.isAdmin()) {
					httpResponse.sendRedirect(Constants.ADMIN_HOME_URL);
				} else if (userData.isCustomer()) {
					httpResponse.sendRedirect(Constants.CUSTOMER_HOME_URL);
				} else {
					httpResponse.sendRedirect(Constants.ORGINISER_HOME_URL);
				}
			}
		} else {
			if (userData == null) {
				// redirect to login page because of not logged in
				httpResponse.sendRedirect(Constants.LOGIN_PAGE);
			} else {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
