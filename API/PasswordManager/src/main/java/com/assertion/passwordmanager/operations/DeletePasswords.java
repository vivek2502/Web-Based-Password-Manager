package com.assertion.passwordmanager.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePasswords {
	private final static String url = "jdbc:postgresql://localhost:5432/postgres";
    private final static String user = "postgres";
    private final static String password = "admin";
    
    
    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    public static boolean deletePassword(String[] delete) {
         int length=delete.length;
        String SQL = "DELETE FROM passwords WHERE website = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
        	System.out.println("***********Connecting to Database************");
        	for(int i=0;i<length;i++) {
        	pstmt.setString(1, delete[i].replaceAll("\\s",""));
        	System.out.println("Deleting password for "+delete[i].replaceAll("\\s",""));
        	pstmt.executeUpdate();
        	}
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}
