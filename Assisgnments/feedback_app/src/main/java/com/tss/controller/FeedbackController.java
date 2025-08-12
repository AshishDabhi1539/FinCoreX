package com.tss.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tss.model.Feedback;
import com.tss.service.FeedbackService;

@WebServlet("/submitFeedback")
public class FeedbackController extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FeedbackService service = new FeedbackService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Feedback fb = new Feedback();
        fb.setName(request.getParameter("name"));
        fb.setSessionDate(request.getParameter("sessionDate"));
        fb.setSessionContent(Integer.parseInt(request.getParameter("sessionContent")));
        fb.setQueryResolution(Integer.parseInt(request.getParameter("queryResolution")));
        fb.setInteractivity(Integer.parseInt(request.getParameter("interactivity")));
        fb.setImpactfulLearning(Integer.parseInt(request.getParameter("impactfulLearning")));
        fb.setDeliverySkills(Integer.parseInt(request.getParameter("deliverySkills")));

        boolean result = service.submitFeedback(fb);

        HttpSession session = request.getSession();
        session.setAttribute("feedback", fb);

        if (result) {
            session.setAttribute("msg", "Your Feedback is submitted successfully on " + new java.util.Date());
        } else {
            session.setAttribute("msg", "Error: Feedback Not Submitted");
        }

        response.sendRedirect("result.jsp"); 
    }
}
