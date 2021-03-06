package com.assertion.passwordmanager.operations;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.assertion.passwordmanager.dao.Website;

public class SavePassword {
	private final static String url = "jdbc:postgresql://localhost:5432/postgres";
    private final static String user = "postgres";
    private final static String password = "admin";
    
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    
    public static boolean insertPassword(Website website) {
    	System.out.println("***********Connecting to Database************");
        String SQL = "INSERT INTO passwords(website,password) "
                + "VALUES(?,?)";
 
        try (Connection conn = connect();
                PreparedStatement pstmt = conn.prepareStatement(SQL,Statement.RETURN_GENERATED_KEYS)) {
 
            pstmt.setString(1, website.getName().replaceAll("\\s",""));
            pstmt.setString(2, website.getPassword().replaceAll("\\s",""));
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows 
            if (affectedRows > 0) {
                // get ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
