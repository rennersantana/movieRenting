/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.movierenting;

import controller.LoginController;
import database.SaveMovieToDatabase;

/**
 *
 * @author rennersantana
 */
public class MovieRenting {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
  //    These lines are only to be uncommented in case there is a need to save the movies to the database.
//        SaveMovieToDatabase save = new SaveMovieToDatabase();
//        save.writeMovieToDatabase();

        //This controller start the login in menu
        LoginController lc = new LoginController();
        lc.initialMenu();
    }

}

