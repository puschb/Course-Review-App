package edu.virginia.cs.hw7.presentationLayer;


import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Student;
import edu.virginia.cs.hw7.businessLogicLayer.CommandLineUtility;
import edu.virginia.cs.hw7.businessLogicLayer.Login;
import edu.virginia.cs.hw7.businessLogicLayer.MainMenu;

import java.util.logging.Level;

import java.util.Scanner;

import static edu.virginia.cs.hw7.businessLogicLayer.CommandLineUtility.nullCharacterString;

public class CommandLineCourseReview {

    private static Scanner scanner;
    private static boolean exit;
    private static Student currentUser;

    public static void main(String args[]) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        runApplicaiton();
    }

    public static void runApplicaiton() {
        scanner = new Scanner(System.in);
        exit = false;
        while(!exit) {
            login();
            if(exit)
                break;
            mainMenu();
        }
    }

    private static void login() {
        boolean inLogin = true;
        while(inLogin) {
            System.out.println("Would you like to login (1), create a new user (2), or exit (3): ");
            int menuResponse = CommandLineUtility.processCommandLineInput(scanner.nextLine());
            switch (menuResponse) {
                case 1:
                    String userName = null;
                    String password = null;
                    while (userName == null) {
                        System.out.println("Enter Username (no null characters): ");
                        String tempUserName = scanner.nextLine();
                        if(!nullCharacterString(tempUserName)) userName = tempUserName;
                        else System.out.println("Error. Null character in input.");
                    }
                    while (password == null) {
                        System.out.println("Enter Password (no null characters): ");
                        String tempPassword = scanner.nextLine();
                        if(!nullCharacterString(tempPassword)) password = tempPassword;
                        else System.out.println("Error. Null character in input.");
                    }
                    try {
                        if(!Login.login(userName, password))
                            System.out.println("Error. Either username doesn't exist or password doesn't match.");
                        else {
                            currentUser = new Student(userName, password);
                            inLogin = false;
                        }
                    } catch (InternalImplementationException e) {
                        e.printStackTrace();
                        System.out.println("Error in implementation");
                    }
                    break;
                case 2:
                    try {
                        boolean uniqueUserName = false;
                        String newUserName = null;
                        while(!uniqueUserName) {
                            while (newUserName == null) {
                                System.out.println("Enter New Username (no null characters): ");
                                String tempUserName = scanner.nextLine();
                                if(!nullCharacterString(tempUserName)) newUserName = tempUserName;
                                else System.out.println("Error. Null character in input.");
                            }
                            if (Login.isLoginAvailable(newUserName))
                                uniqueUserName = true;
                            else {
                                newUserName=null;
                                System.out.println("That username already exists, try again.");
                            }
                        }
                        String password2 = null;
                        while (password2 == null) {
                            System.out.println("Enter New Password (no null characters): ");
                            String tempPassword1 = scanner.nextLine();
                            if(!nullCharacterString(tempPassword1)) password2 = tempPassword1;
                            else System.out.println("Error. Null character in input.");
                        }
                        String confirmPassword = null;
                        while (confirmPassword == null) {
                            System.out.println("Confirm Password: ");
                            String tempPassword2 = scanner.nextLine();
                            if(!nullCharacterString(tempPassword2)) confirmPassword = tempPassword2;
                            else System.out.println("Error. Null character in input.");
                        }
                        if(Login.signUp(newUserName, password2, confirmPassword)) {
                            currentUser = new Student(newUserName, password2);
                            inLogin = false;
                        } else
                            System.out.println("Passwords don't match.");
                    } catch (InternalImplementationException e) {
                        e.printStackTrace();
                        System.out.println("Error in implementation");
                    }
                    break;
                case 3:
                    inLogin = false;
                    exit();
                    break;
                default:
                    System.out.println("Invlalid menu choice. Type a number between 1 and 3.");
                    break;
            }
        }
    }

    private static void mainMenu() {
        boolean inMainMenu = true;
        while(inMainMenu) {
            System.out.println("Would you like to submit a review for a course (1), see reviews for a course (2) or logout (3): ");
            int menuResponse = CommandLineUtility.processCommandLineInput(scanner.nextLine());
            switch (menuResponse) {
                case 1:
                    try {
                        System.out.println("Enter the course you would like to write a review for: ");
                        String courseInput = scanner.nextLine();
                        if (!MainMenu.isValidCourseInput(courseInput)) {
                            System.out.println("Invalid format for course input.");
                            continue;
                        }
                        if (MainMenu.doesCourseExist(courseInput) &&
                                MainMenu.hasStudentReviewedCourse(courseInput, currentUser)) {
                            System.out.println("Error, you have already reviewed this course.");
                            continue;
                        }
                        System.out.println("What would you like to say in your review: ");
                        String message = scanner.nextLine();
                        while(message == null || message.length() == 0) {
                            System.out.println("Error. Nothing in review Message.");
                            System.out.println("What would you like to say in your review: ");
                            message = scanner.nextLine();
                        }
                        int rating = -1;
                        while(rating == -1) {
                            System.out.println("Rate this course (1-5): ");
                            rating = MainMenu.convertValidRating(scanner.nextLine());
                        }
                        MainMenu.addReview(message,rating,currentUser,courseInput);
                        System.out.println("Review added!");

                    } catch (InternalImplementationException e) {
                        e.printStackTrace();
                        System.out.println("Error in implementation.");
                    }
                    break;
                case 2:
                    System.out.println("Enter the course you would like to see reviews for: ");
                    String courseInput = scanner.nextLine();

                    if (!MainMenu.isValidCourseInput(courseInput)) {
                        System.out.println("Invalid format for course input.");
                        continue;
                    }

                    String reviews = MainMenu.getReviewsForCourse(courseInput);
                    if( reviews == null)
                        System.out.println("There are no reviews for this course");
                    else
                        System.out.println(reviews);

                    break;
                case 3:
                    inMainMenu = false;
                    break;
                default:
                    System.out.println("Invlalid menu choice. Type a number between 1 and 3.");
                    break;
            }
        }
    }

    private static void exit() {
        exit = true;
        System.out.println("Thank you for using our service.");
    }


}


