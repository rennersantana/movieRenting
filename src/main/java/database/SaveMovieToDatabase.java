    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import model.Movie;
import services.MovieDao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author rennersantana
 */
public class SaveMovieToDatabase {
    
 // method to be called only once to load movies in the database
    public void writeMovieToDatabase() {
        MovieDao movieDao = new MovieDao();
        String fileName = "Movie_Metadata_Edited_2.csv";
        String splitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        String line = "";
        int numberOfMovies = 0;
        int movieLimit = 200;

        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String firstLine = br.readLine();

            while ((line = br.readLine()) != null && numberOfMovies < movieLimit ) { // returns a Boolean value

                String[] data = line.split(splitBy); // use comma as separator
                String originalTitle = data[1];
                String tagline = data[6];
                int watchedCount = 0;
                double rating = Double.parseDouble(data[8]);
                double price =Double.parseDouble(data[10]);
                Movie movie = new Movie(originalTitle, tagline, watchedCount, rating, price);
                movieDao.save(movie);
                numberOfMovies++;

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
