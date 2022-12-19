/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import systemvariables.SystemVariables;
import model.Movie;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rennersantana
 */
public class DatabaseConnection {
    
   //this method will handle queries that do not return anything for example Delete, Update and Insert
    public void connModify (String query, String message){
        try {

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(SystemVariables.DBCONNECTION.value, SystemVariables.USER.value, SystemVariables.PASSWORD.value);
            // Get a statement from the connection
            Statement stmt = conn.createStatement();

            // Execute the query
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(message);
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");

            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //this method will fetch data from DB when user perform some operations and is used when information is being returned from the DB
    //this method get the users from the database and return it in a list
    public List<User> connRetrieveUser(String query){

        List<User> usersList = new ArrayList<>();
        try {
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(SystemVariables.DBCONNECTION.value, SystemVariables.USER.value, SystemVariables.PASSWORD.value);
            // Get a statement from the connection
            Statement stmt = conn.createStatement();
            // Execute the query getting the users from db
            ResultSet rs = stmt.executeQuery(query);

            // Loop through the result set
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstName = rs.getString("first_name");
                String surname = rs.getString("surname");
                List<Integer> moviesWatched = connRetrieveMoviesWatched(userId);

                User user = new User(userId, email, password, firstName, surname, moviesWatched);
                usersList.add(user);
            }

            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return usersList;
    }

    //this method will fetch data from DB when user perform some operations and is used when information is being returned from the DB
    //this method will fetch the movies when user navigate through the menus will fetch all the movies or only by id
    public List<Movie> connRetrieveMovies (String query){

        List<Movie> moviesList = new ArrayList<>();
        try {
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(SystemVariables.DBCONNECTION.value, SystemVariables.USER.value, SystemVariables.PASSWORD.value);
            // Get a statement from the connection
            Statement stmt = conn.createStatement();
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Loop through the result set
            while (rs.next()) {
                int id = rs.getInt("movie_id");
                String originalTitle = rs.getString("original_title");
                String tagline = rs.getString("tagline");
                int watchedCount = rs.getInt("watched_count");
                double rating = rs.getDouble("rating");
                double price = rs.getDouble("price");
                Movie movie = new Movie(id, originalTitle, tagline, watchedCount, rating, price);
                moviesList.add(movie);
            }
            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return moviesList;
    }

    //this method will fetch data from DB when user log in so we create a object user this method will fetch the movie id that the user already watched
    //and return a Arraylist of integers
    public List<Integer> connRetrieveMoviesWatched (int userId){
        String query = "SELECT * FROM movies_rented WHERE user_id=" + userId;
        List<Integer> moviesList = new ArrayList<>();
        try {
            // Get a connection to the database
            Connection conn = DriverManager.getConnection(SystemVariables.DBCONNECTION.value, SystemVariables.USER.value, SystemVariables.PASSWORD.value);
            // Get a statement from the connection
            Statement stmt = conn.createStatement();
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);
            // Loop through the result set
            while (rs.next()) {
                int movieId = rs.getInt("movie_id");
                moviesList.add(movieId);
            }
            //Close the result set, statement and the connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            System.out.println("SQL Exception:");
            // Loop through the SQL Exceptions
            while (se != null) {
                System.out.println("State  : " + se.getSQLState());
                System.out.println("Message: " + se.getMessage());
                System.out.println("Error  : " + se.getErrorCode());
                se = se.getNextException();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return moviesList;
    }

}
