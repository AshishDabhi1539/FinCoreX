package com.banking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banking.db.DBConnection;
import com.banking.model.Beneficiary;

public class BeneficiaryDao {

    public boolean add(Beneficiary b) throws SQLException {
        String sql = "INSERT INTO beneficiaries (user_id, beneficiary_name, account_number, ifsc, bank_name, nickname, created_at) VALUES (?,?,?,?,?,?, NOW())";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, b.getUserId());
            ps.setString(2, b.getBeneficiaryName());
            ps.setString(3, b.getAccountNumber());
            ps.setString(4, b.getIfsc());
            ps.setString(5, b.getBankName());
            ps.setString(6, b.getNickname());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Beneficiary> listByUser(long userId) throws SQLException {
        String sql = "SELECT * FROM beneficiaries WHERE user_id=? ORDER BY created_at DESC";
        List<Beneficiary> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Beneficiary b = new Beneficiary();
                    b.setBeneficiaryId(rs.getLong("beneficiary_id"));
                    b.setUserId(rs.getLong("user_id"));
                    b.setBeneficiaryName(rs.getString("beneficiary_name"));
                    b.setAccountNumber(rs.getString("account_number"));
                    b.setIfsc(rs.getString("ifsc"));
                    b.setBankName(rs.getString("bank_name"));
                    b.setNickname(rs.getString("nickname"));
                    list.add(b);
                }
            }
        }
        return list;
    }

    public boolean delete(long beneficiaryId, long userId) throws SQLException {
        String sql = "DELETE FROM beneficiaries WHERE beneficiary_id=? AND user_id=?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, beneficiaryId);
            ps.setLong(2, userId);
            return ps.executeUpdate() > 0;
        }
    }
}
