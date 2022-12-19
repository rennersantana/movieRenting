/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author rennersantana
 */
public class Rent {
    
 private User user;
    private Movie movie;
    private LocalDateTime dateOfRent;
    private long period;

    //this class is used when the system create a Rent operation saving it to this object
    //this property dataOfRent is responsible for logging the time when a movie is rented as we use this property to display the most popular movies
    //the period property can be changed for a perhaps longer duration  or the Period java class
    public Rent(User user, Movie movie, long period) {
        this.user = user;
        this.movie = movie;
        this.period = period;
        this.dateOfRent = LocalDateTime.now();
    }

    //standard getters and setters and toString method
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDateOfRent() {
        return dateOfRent;
    }

    public void setDateOfRent(LocalDateTime dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "user=" + user +
                ", movie=" + movie +
                ", dateOfRent=" + dateOfRent +
                ", period=" + period +
                '}';
    }
}
