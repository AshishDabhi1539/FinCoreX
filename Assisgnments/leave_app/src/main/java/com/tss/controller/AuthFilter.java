package com.tss.controller;

import com.tss.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override public void init(FilterConfig filterConfig) {}
    @Override public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest r = (HttpServletRequest) req;
        HttpServletResponse s = (HttpServletResponse) res;
        HttpSession session = r.getSession(false);

        String uri = r.getRequestURI();
        User user = (session == null) ? null : (User) session.getAttribute("user");

        if (uri.startsWith(r.getContextPath() + "/employee/")) {
            if (user == null || !"EMPLOYEE".equals(user.getRole())) {
                s.sendRedirect(r.getContextPath() + "/jsp/login.jsp?e=Please+login");
                return;
            }
        }
        if (uri.startsWith(r.getContextPath() + "/admin/")) {
            if (user == null || !"ADMIN".equals(user.getRole())) {
                s.sendRedirect(r.getContextPath() + "/jsp/login.jsp?e=Admin+login+required");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
