package com.assertion.passwordmanager.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.assertion.passwordmanager.dao.Website;

public class EditPassword {
	private final static String url = "jdbc:postgresql://localhost:5432/postgres";
    private final static String user = "postgres";
    private final static String password = "admin";
    
    
   
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    public static boolean editPassword(Website website) {
        String SQL = "UPDATE passwords "
                + "SET password = ? "
                + "WHERE website = ?";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL)) {
        	pstmt.setString(1, website.getPassword().replaceAll("\\s",""));
            pstmt.setString(2, website.getName().replaceAll("\\s",""));
            System.out.println("***********Connecting to Database************");
            pstmt.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
