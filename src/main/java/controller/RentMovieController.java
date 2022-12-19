/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Movie;
import model.Rent;
import model.User;
import services.MovieDao;
import services.UserDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author rennersantana
 */
public class RentMovieController {
    

    //scanner method for taking input from users
    private final Scanner sc = new Scanner(System.in);
    //User DAO is the class responsible for handling database connection
    private final UserDAO userService = new UserDAO();
    private final MovieDao movieServices = new MovieDao();
    //this list holds all the movies that has been rented so we can use it to display the most popular movies to the user
    private  List<Rent> moviesRented = new ArrayList<>();

    //this method is the menu after the user log in
    public void userMenu(User user){
        while(true) {
            try {
                System.out.println("EIRVID");
                displayPopularMovies();
                System.out.println("Please chose one of the options below:");
                System.out.println("1: Search for a movie");
                System.out.println("2: See all movies");
                System.out.println("3: Already know the movie Id");
                System.out.println("0 - Exit");
                System.out.println("Please enter your option: ");
                int input = Integer.parseInt(sc.nextLine());
                switch (input) {
                    case 1:
                        searchMovieMenu();
                        rentMovieMenu(user);
                    case 2:
                        printAllMovies();
                        userMenu(user);
                    case 3:
                        rentMovieMenu(user);
                    case 0:
                        System.out.println("Thank you for using our System.");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Input not valid, please try again.");
            }
        }
    }

    //print all movies on the database in a form of object
    private void printAllMovies(){
        List<Movie> movieList = movieServices.findAll();
        System.out.println("Check out our movies below\n");
        for (Movie movie : movieList){
            System.out.println(movie);
        }
    }

    //get the serch query from the user and pass to the queryMovies method
    private void searchMovieMenu(){
        System.out.println("Please enter the title of the movie bellow:");
        String input = sc.nextLine();
        queryMovies(input);
    }

    //finds movies on database that contains the search keyword inputted by the user
    private void queryMovies(String query){

        List<Movie> movieList = movieServices.findAll();
        System.out.println("Movies found: ");
        for (Movie movie : movieList){
           if(movie.getOriginalTitle().toLowerCase().contains(query.toLowerCase())){
               System.out.println(movie);
           }
        }
    }

    //this method display to the users the oprtion when they are ready to rent a movie
    private void rentMovieMenu(User user) throws InterruptedException {

        System.out.println("Found a Movie? " + user.getFirstName());
        System.out.println("Please enter the Movie Id bellow: ");
        int inputId = Integer.parseInt(sc.nextLine());

        Movie movie = movieServices.findById(inputId);
        System.out.println("You are renting the movie: ");
        System.out.println(movie);

        System.out.println("\nPlease use the options below to confirm or go back to Main menu");
        System.out.println("1: Rent Movie: " + movie.getOriginalTitle());
        System.out.println("2: Go back to Main Menu");
        System.out.println("0 - Exit");
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case 1:
                rentMovie(user, movie);
            case 2:
                userMenu(user);
            case 0:
                System.out.println("Thank you for using our System.");
                System.exit(0);
        }
    }

    //this method manger the rent of a movie
    private void rentMovie(User user, Movie movie) throws InterruptedException {
        long period = 1; //this is the period the movie will be rented
        int watched = 1 ; //this values represents hoe many times a movies is being watched so everytime a user rent
                           //this watched add 1 to the watchedCount in the database

        //creating an instance of the Rent model and printing the transaction information to the console
        Rent rent = new Rent(user, movie, period);
        moviesRented.add(rent);
        System.out.println("Movie Rent Transaction");
        System.out.println("User: " + rent.getUser().getFirstName());
        System.out.println("Email: " + rent.getUser().getEmail());
        System.out.print("Movie: " + rent.getMovie());

//        keeps track of 1 minute
//        TimeUnit.MINUTES.sleep(rent.getPeriod());
        //this line is for test - purposes to reduce the time spent waiting the rental period expires setting it to 10 seconds
        TimeUnit.SECONDS.sleep(10);
        System.out.println("Movie Returned, Thank you.");
        //here we update the list of movies that user has watched and the watched count of the movie
        int movieId = rent.getMovie().getMovieId();
        int userId = rent.getUser().getUserId();
        List<Integer> moviesWatched = rent.getUser().getMoviesWatchedId();
        //adds the movie to the Rent list
        moviesWatched.add(movieId);
        rent.getMovie().setWatchedCount(rent.getMovie().getWatchedCount() + watched);
        rent.getUser().setMoviesWatchedId(moviesWatched);
        //update database
        movieServices.updateWatchedCount(rent.getMovie());
        userService.saveToWatchedList(movieId, userId);

    }
    //this method will display the most popular movies that has been rented in a period of five minutes
    private void displayPopularMovies(){
        LocalDateTime fiveMinutes = LocalDateTime.now().minusMinutes(5);
        List<Movie> popularMovies = new ArrayList<>();
        for (Rent rent : moviesRented){
            if(rent.getDateOfRent().isAfter(fiveMinutes)){
                popularMovies.add(rent.getMovie());
            }
        }
        if(popularMovies.size() > 0) {
            System.out.println("List of most popular movies on EIRVID");
            popularMovies.forEach(x -> {
                System.out.println(x);
            });
        }

    }
}
