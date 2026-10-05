package com.webapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    
    
    public static final String INSERT_SQL = "INSERT INTO users (name, phone, email, password_hash) VALUES (?, ?, ?, ?)";
    public void saveUser(Connection conn, String name, String phone, String email, String passwordHash) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, email);
            stmt.setString(4, passwordHash);
            stmt.executeUpdate();
        }
    }
}