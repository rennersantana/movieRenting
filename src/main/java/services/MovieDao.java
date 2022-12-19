/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import database.DatabaseConnection;
import model.Movie;

import java.util.List;

/**
 *
 * @author rennersantana
 */
public class MovieDao implements IDAO<Movie>{
    
   //create a instance of the Database Connection class
    private final DatabaseConnection dbConn = new DatabaseConnection();

    //every movie when created has a watched count property, so we can see how many times each movie has been watched
    public void updateWatchedCount(Movie movie) {
        String query = "UPDATE movies SET watched_count ='" + movie.getWatchedCount() + "' WHERE movie_id=" + movie.getMovieId();
        dbConn.connModify(query, "Movie Updated");
    }
    //save movie to database
    @Override
    public void save(Movie movie) {
        String query = "INSERT INTO movies(original_title, tagline, watched_count, rating, price)"
                + "VALUES('" + movie.getOriginalTitle() + "','" + movie.getTagline() + "','" + movie.getWatchedCount() +"','" + movie.getRating() +"','" + movie.getPrice() + "')";
        dbConn.connModify(query, "Movie Saved");

    }
    //update movie on the database
    @Override
    public void update(Movie movie) {
        String query = "UPDATE movies SET original_title = '" + movie.getOriginalTitle()  +"', tagline ='" + movie.getTagline() + "', watched_count ='" + movie.getWatchedCount() +
                "', rating ='" + movie.getRating() + "', price='" + movie.getPrice() + "' WHERE movie_id=" + movie.getMovieId();
        dbConn.connModify(query, "Movie Updated");
    }
    //delete movie from the database
    @Override
    public void delete(int id) {
        String query = "DELETE FROM movies WHERE movie_id =" + id;
        dbConn.connModify(query, "Movie Deleted");
    }
    //find a movie by id
    @Override
    public Movie findById(int id) {
        String query = "SELECT * FROM movies WHERE movie_id=" +id;
        return  dbConn.connRetrieveMovies(query).get(0);
    }
    //find all movies in the database
    @Override
    public List<Movie> findAll() {
        String query = "SELECT * FROM movies";
        return  dbConn.connRetrieveMovies(query);
    }
}
