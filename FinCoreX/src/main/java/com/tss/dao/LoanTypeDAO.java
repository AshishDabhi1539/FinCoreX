package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.LoanType;

public class LoanTypeDAO {
	public List<LoanType> getActiveLoanTypes() {
		String sql = "SELECT loan_type_id, loan_type_name, default_interest_rate, is_active FROM loan_types WHERE is_active = TRUE ORDER BY loan_type_name";
		List<LoanType> list = new ArrayList<>();
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				LoanType lt = new LoanType();
				lt.setLoanTypeId(rs.getInt("loan_type_id"));
				lt.setLoanTypeName(rs.getString("loan_type_name"));
				lt.setDefaultInterestRate((Double) rs.getObject("default_interest_rate"));
				lt.setIsActive(rs.getBoolean("is_active"));
				list.add(lt);
			}
		} catch (Exception e) {
			// swallow; optional
		}
		return list;
	}
}

