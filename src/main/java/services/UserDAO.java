/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import database.DatabaseConnection;
import model.User;

import java.util.List;


/**
 *
 * @author rennersantana
 */
public class UserDAO implements IDAO<User>{
  //create a instance of the Database Connection class
    private final DatabaseConnection dbConn = new DatabaseConnection();

    //fetch users by email and password used mostly on the log in controller
    public User findByEmailPassword(String email, String password) {
        String query = "SELECT * FROM users WHERE email='" + email + "' AND password='" + password + "'";
        return  dbConn.connRetrieveUser(query).get(0);
    }

    //save the movie that the user is renting to the database so it is possible to keep track of the movies that a user has rented.
    public void saveToWatchedList(int movieId, int userId) {
        String query = "INSERT INTO movies_rented(movie_id, user_id) VALUES ('" + movieId + "','" + userId + "')";
        dbConn.connModify(query, "Watched List Updated");
    }

    //save user to database
    @Override
    public void save(User user) {
        String query = "INSERT INTO users(email, password, first_name, surname)"
                + "VALUES('" + user.getEmail() + "','" + user.getPassword() + "','" + user.getFirstName() +"','" + user.getSurname() + "')";
        dbConn.connModify(query, "User Created");
    }
    //ipdate user in the database
    @Override
    public void update(User user) {
        String query = "UPDATE users SET email = '" + user.getEmail()  +"', password ='" + user.getPassword() + "', first_name ='" + user.getFirstName() +
                "', surname ='" + user.getSurname() + "' WHERE user_id=" + user.getUserId();
        dbConn.connModify(query, "User Updated");
    }
    //delete user from the database
    @Override
    public void delete(int id) {
        String query = "DELETE FROM users WHERE user_id =" + id;
        dbConn.connModify(query, "User Deleted");

    }
    //find user by id
    @Override
    public User findById(int id) {
        String query = "SELECT * FROM users WHERE user_id=" +id;
        return  dbConn.connRetrieveUser(query).get(0);
    }
    //find all users
    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        return dbConn.connRetrieveUser(query);

    }
}
