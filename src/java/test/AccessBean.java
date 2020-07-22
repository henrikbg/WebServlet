package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henrik
 */
public class AccessBean {

private String connectionString = "jdbc:mysql://localhost:3306/GolfClubManager";
private String username;
private String password;
private boolean valid;
private Connection conn;

    public AccessBean() {
        try {
            this.conn = DriverManager.getConnection(connectionString, "root", "mysql1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        
        try {
            PreparedStatement ps;
            ResultSet rs;
            String sql = "SELECT * FROM Credentials WHERE Username = ? AND Password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            valid = rs.next();
            return valid;
        } catch (SQLException e) {
            valid = false;
            return valid;
        }
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}
