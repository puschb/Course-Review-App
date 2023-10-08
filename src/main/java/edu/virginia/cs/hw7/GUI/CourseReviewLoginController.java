package edu.virginia.cs.hw7.GUI;

import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Student;
import edu.virginia.cs.hw7.businessLogicLayer.Login;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseReviewLoginController {
    public Button login;
    public Button exit;
    public TextField username;
    public PasswordField password;
    public Label wrongLogin;
    public static Student currentUser;

    public void userLogIn() {
        checkLogin();
    }

    private void checkLogin() {
        if(!(username.getText().isEmpty() && password.getText().isEmpty())) {
            try {
                if (!Login.login(username.getText(), password.getText()))
                    wrongLogin.setText("Wrong username or password!");
                else {
                    currentUser = new Student(username.getText(), password.getText());
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/CourseReviewMainMenu.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Stage stage = (Stage) login.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (InternalImplementationException e) {
                e.printStackTrace();
                System.out.println("Error in implementation");
            }
        }
    }

    public void exit() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/CourseReviewStart.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) exit.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}