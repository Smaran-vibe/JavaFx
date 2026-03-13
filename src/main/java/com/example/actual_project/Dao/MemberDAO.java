package com.example.actual_project.Dao;

import com.example.actual_project.models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class MemberDAO {
    public boolean addMember(Member member) {
        String query = "INSERT INTO Member (MemberID, FirstName, LastName, Gender, DateOfBirth, Phone, Email, Address, JoinDate, PlanID) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBconnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, member.getMemberId());
            pstmt.setString(2, member.getFirstName());
            pstmt.setString(3, member.getLastName());
            pstmt.setString(4, member.getGender());
            pstmt.setDate(5, member.getDateOfBirth());
            pstmt.setString(6, member.getPhone());
            pstmt.setString(7, member.getEmail());
            pstmt.setString(8, member.getAddress());
            pstmt.setDate(9, member.getJoinDate());
            pstmt.setInt(10, member.getPlanId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
