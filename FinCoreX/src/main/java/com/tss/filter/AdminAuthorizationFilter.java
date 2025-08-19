package com.tss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.model.User;

@WebFilter({ "/loan_approval", "/manage_customers", "/reports", "/manage_accounts", "/admin/*" })
public class AdminAuthorizationFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		Object userObj = request.getSession(false) != null ? request.getSession(false).getAttribute("user") : null;
		if (userObj == null || !(userObj instanceof User) || !"Admin".equals(((User) userObj).getRole())) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		chain.doFilter(req, res);
	}
}

