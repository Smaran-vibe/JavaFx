package org.example;
import java.sql.*;
import java.util.Scanner;

public class Hello_data1 {
    public static void main(String[] args) throws Exception {

        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/javafxdb",
                "root",
                "");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine().trim();

        System.out.print("Enter password: ");
        String password = sc.nextLine().trim();

        String sql = "SELECT * FROM users WHERE username='"
                + username + "' AND password='" + password + "'";

        System.out.println("Executing Query: " + sql);

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next())
            System.out.println("Login Successful");
        else
            System.out.println("Login Failed");

        conn.close();
    }
}

