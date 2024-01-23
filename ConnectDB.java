package com.gabriele.com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {

	public static void main(String[] args) {
		
		Connection myconn=null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
			// 1. Get a connection to a database
			myconn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "xxxx");
			if (myconn != null) {
				System.out.println("Connection OK");
			}else {
				System.out.println("Connection Failed");
			}
		
			// 2. Create statement
			myStmt = myconn.createStatement();
			String sql = "SELECT datname FROM pg_database";

						
			// 3. Execute a sql query
			myRs = myStmt.executeQuery(sql);
			System.out.println("List of databases:");
		
						
			// 4. Process the result set
			while (myRs.next()) {
			String databaseName = myRs.getString("datname");
            System.out.println(databaseName);
		}
		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
            // Close resources in the reverse order of their creation
            try {
                if (myRs != null) {
                    myRs.close();
                }
                if (myStmt != null) {
                    myStmt.close();
                }
                if (myconn != null) {
                    myconn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
