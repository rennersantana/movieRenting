/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rennersantana
 */
public class Movie {
    
 

    private int movieId;
    private String originalTitle;
    private String tagline;
    private int watchedCount;
    private double rating;
    private double price;

    //this constructor is used when we are fetching an already created movie
    public Movie(int movieId, String originalTitle, String tagline, int watchedCount, double rating, double price) {
        this.movieId = movieId;
        this.originalTitle = originalTitle;
        this.tagline = tagline;
        this.watchedCount = watchedCount;
        this.rating = rating;
        this.price = price;
    }

    //This construct is called when we create the movie just before we save it to the database, at this point we do not have an id so we use overload principle
    public Movie(String originalTitle, String tagline, int watchedCount, double rating, double price) {
        this.originalTitle = originalTitle;
        this.tagline = tagline;
        this.watchedCount = watchedCount;
        this.rating = rating;
        this.price = price;
    }

    //standard getters and setters and toString method
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public int getWatchedCount() {
        return watchedCount;
    }

    public void setWatchedCount(int watchedCount) {
        this.watchedCount = watchedCount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Movie Id: "+ movieId + "\nOriginal Title: " + originalTitle + "\nTagline: " + tagline + "\nRating: " + rating + "\nPrice: " + price + "\n";
    }
}
