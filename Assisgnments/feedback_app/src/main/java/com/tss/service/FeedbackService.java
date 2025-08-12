package com.tss.service;

import com.tss.dao.FeedbackDAO;
import com.tss.model.Feedback;

public class FeedbackService {

	private FeedbackDAO dao = new FeedbackDAO();

    public boolean submitFeedback(Feedback fb) {
        // Possible validation logic here
        return dao.saveFeedback(fb);
    }
}
