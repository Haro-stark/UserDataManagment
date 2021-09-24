package com.example.userdata;

import javax.xml.crypto.Data;
import java.awt.print.Printable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void UserDAO() {
    }

    public static final String INSERT_USERS_SQL = "INSERT INTO Users (name, email, country) Values (?,?,?);";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE id= ?;";
    public static final String SELECT_ALL_USERS = "SELECT * FROM Users;";
    public static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE id= ?;";
    public static final String UPDATE_USERS_SQL = "UPDATE USERS SET name=?, email= ?, country=? WHERE id=?;";
    public static final String UPDATE_USERS_BY_EMAIL_SQL = "UPDATE USERS SET name=?, email= ?, country=? WHERE email=?;";
    public static final String SELECT_USER_BY_EMAIL = "SELECT * FROM Users WHERE email= ?;";

    public void insertUser(User user) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
            System.out.println("Inserting user:: " + preparedStatement);

        } catch (SQLException e) {
            System.out.println("SQL EXCEPTION Catch USER DAO");
            printSQLException(e);
        }
    }
    public User selectUser(int id) {
        User user = new User();

        try {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                user.id = rs.getInt("ID");
                user.name = rs.getString("name");
                user.email = rs.getString("email");
                user.country = rs.getString("country");
            }

        } catch (SQLException e) {
            printSQLException(e);
        }

        return user;
    }
    public List<User> selectAllUser() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);){
            System.out.println("Select all user stamtemnt: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("columns Number of id col is: "+rs.findColumn("ID"));
            int counter=1, id;
            while (rs.next()) {

                if(rs.getInt(1)==0) id = counter;
                else id = rs.getInt(rs.findColumn("ID"));

                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                userList.add(new User(id, name, email, country));
                counter++;
            }

            for (User u : userList) {
                System.out.println("id: " + u.id);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return userList;
    }
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setInt(4, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }


//    public User selectUserByEmail(String email)  {
//        User user = new User();
//
//        try {
//            Connection connection = DatabaseConnection.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
//            preparedStatement.setString(1, email);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                user.id = rs.getInt("id");
//                user.name = rs.getString("name");
//                user.email = rs.getString("email");
//                user.country = rs.getString("country");
//            }
//
//        } catch (SQLException e) {
//            printSQLException(e);
//        }
//
//        return user;
//    }




//    public boolean updateUserByEmail(User user) throws SQLException {
//
//        boolean rowUpdated=false;
//        try (Connection connection = DatabaseConnection.getConnection();
//             PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_BY_EMAIL_SQL);) {
//            statement.setString(1, user.getName());
//            statement.setString(2, user.getEmail());
//            statement.setString(3, user.getCountry());
//            statement.setString(4, user.getEmail());
//
//            statement.executeUpdate();
//            System.out.println("statement: "+statement);
////            System.out.println("Has the statement executed? :" + rowUpdated);
//        }
//        return rowUpdated;
//    }



}
