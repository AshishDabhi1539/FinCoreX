package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.db.DBConnection;
import com.tss.model.Employee;

public class EmployeeDao {

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO FROM emp";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getInt("EMPNO"),
                        rs.getString("ENAME"),
                        rs.getString("JOB"),
                        rs.getObject("MGR") != null ? rs.getInt("MGR") : null,
                        rs.getDate("HIREDATE"),
                        rs.getDouble("SAL"),
                        rs.getObject("COMM") != null ? rs.getDouble("COMM") : null,
                        rs.getInt("DEPTNO")
                );
                employees.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
