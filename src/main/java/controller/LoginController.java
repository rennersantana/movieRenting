/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;
import services.UserDAO;

import java.util.Scanner;

/**
 *
 * @author rennersantana
 */
public class LoginController {
  //scanner method for taking input from users
    private final Scanner sc = new Scanner(System.in);
    //User DAO is the class responsible for handling database connection
    private final UserDAO userService = new UserDAO();

    //tjhis method will call the login so the user can either login and register is the only method called by the main method
    public void initialMenu() {
        System.out.println("Welcome to the EIRVID");
        System.out.println("Please log in to use our services.");
        System.out.println("If you are not registered please create an account.");
        logInMenu();
    }

    //log in and register menu
    private void logInMenu() {
        while (true) {
            try {
                System.out.println("1 - Log In.");
                System.out.println("2 - Register");
                System.out.println("0 - Exit");
                System.out.println("Please enter your option: ");
                int input = Integer.parseInt(sc.nextLine());
                switch (input) {
                    case 1:
                        menuOneLogIn();
                    case 2:
                        menuTwoRegister();
                        menuOneLogIn();
                    case 0:
                        System.out.println("Thank you for using our System.");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Input not valid, please try again.");
            }
        }

    }

    //menu responsible for getting the email and password from the user
    private void menuOneLogIn() {
        System.out.println("Please fill your credentials bellow to Log in:");
        System.out.print("email: ");
        String email = sc.nextLine();
        System.out.print("password: ");
        String password = sc.nextLine();
        checkCredentials(email, password);
    }

    //method responsible for register the user in the database
    private void menuTwoRegister() {
        System.out.println("Please fill the form bellow:");
        System.out.print("First Name: ");
        String firstName = sc.nextLine();
        System.out.print("Surname: ");
        String surname = sc.nextLine();
        System.out.print("email: ");
        String registerEmail = sc.nextLine();
        System.out.print("password: ");
        String registerPassword = sc.nextLine();
        User user = new User(registerEmail, registerPassword, firstName, surname);
        register(user);
    }

    //method responsible for chechking the credential of the users and if the user exist in the database
    private void checkCredentials(String email, String password) {
        User user = userService.findByEmailPassword(email, password);
        RentMovieController rc = new RentMovieController();
        System.out.println(user);
        rc.userMenu(user);
    }
    //method save the user in the database
    private void register(User user) {
        userService.save(user);
    }





}
