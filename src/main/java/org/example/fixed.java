package org.example;

import java.sql.*;
import java.util.Scanner;
public class fixed {
    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javafxdb",
                "root",
                "Trafalgar law@123");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");

        ps.setString(1, username);
        ps.setString(2, password);

        ResultSet rs = ps.executeQuery();

        if(rs.next())
            System.out.println("Login Successful");
        else
            System.out.println("Login Failed");

        conn.close();
    }
}
