package com.tildawn.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.tildawn.Model.User;

public class DatabaseManager {
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:user_data.db");
            createTableIfNeeded();
        } catch (SQLException e) {
            System.out.println("kkkkkkkkkkkkkkkkkk");
            e.printStackTrace();
        }
    }

    private void createTableIfNeeded() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username TEXT NOT NULL UNIQUE," +
            "password TEXT NOT NULL," +
            "securityQuestion TEXT," +
            "securityAnswer TEXT," +
            "avatarPath TEXT," +
            "score INTEGER," +
            "totalEliminations INTEGER," +
            "mostTimeAlive REAL" +
            ");";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }

    public void insertUser(User user) {
        try {
            String sql = "INSERT INTO User (username, password, securityQuestion, securityAnswer, avatarPath, score, totalEliminations, mostTimeAlive) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getSecurityQuestion());
            pstmt.setString(4, user.getSecurityAnswer());
            pstmt.setString(5, user.getAvatarPath());
            pstmt.setInt(6, user.getScore());
            pstmt.setInt(7, user.getTotalEliminations());
            pstmt.setFloat(8, user.getMostTimeAlive());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM User";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("securityQuestion"),
                    rs.getString("securityAnswer"),
                    rs.getString("avatarPath")
                );
                user.setScore(rs.getInt("score"));
                user.setTotalEliminations(rs.getInt("totalEliminations"));
                user.setMostTimeAlive(rs.getFloat("mostTimeAlive"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(User user) {
        try {
            String sql = "UPDATE User SET " +
                "password = ?, " +
                "securityQuestion = ?, " +
                "securityAnswer = ?, " +
                "avatarPath = ?, " +
                "score = ?, " +
                "totalEliminations = ?, " +
                "mostTimeAlive = ? " +
                "WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getSecurityQuestion());
            pstmt.setString(3, user.getSecurityAnswer());
            pstmt.setString(4, user.getAvatarPath());
            pstmt.setInt(5, user.getScore());
            pstmt.setInt(6, user.getTotalEliminations());
            pstmt.setFloat(7, user.getMostTimeAlive());
            pstmt.setString(8, user.getUsername());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No user found with username: " + user.getUsername());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username) {
        try {
            String sql = "DELETE FROM User WHERE username = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted == 0) {
                System.out.println("No user found with username: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

