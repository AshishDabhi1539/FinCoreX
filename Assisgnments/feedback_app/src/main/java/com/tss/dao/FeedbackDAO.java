package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tss.db.DBConnection;
import com.tss.model.Feedback;

public class FeedbackDAO {

	public boolean saveFeedback(Feedback fb) {
        String sql = "INSERT INTO feedback(name, session_date, session_content, query_resolution, interactivity, impactful_learning, delivery_skills) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fb.getName());
            ps.setString(2, fb.getSessionDate());
            ps.setInt(3, fb.getSessionContent());
            ps.setInt(4, fb.getQueryResolution());
            ps.setInt(5, fb.getInteractivity());
            ps.setInt(6, fb.getImpactfulLearning());
            ps.setInt(7, fb.getDeliverySkills());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
