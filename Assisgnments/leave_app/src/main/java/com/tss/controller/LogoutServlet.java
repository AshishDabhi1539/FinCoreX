package com.tss.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        
        try {
        if (s != null) s.invalidate();
        resp.sendRedirect(req.getContextPath()+"/login.jsp?m=Logged+out");
        }
        catch (Exception e) {
            e.printStackTrace();
            req.getRequestDispatcher("/error.jsp")
                   .forward(req, resp);
        }

    }
}
