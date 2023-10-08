package edu.virginia.cs.hw7.GUI;

import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Student;
import edu.virginia.cs.hw7.businessLogicLayer.CommandLineUtility;
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

public class CourseReviewNewLoginController {
    public Button login;
    public Button exit;
    public TextField username;
    public PasswordField password;
    public PasswordField confirm;
    public Label wrongLogin;
    public static Student currentUser;
    public static boolean newLogin = false;

    public void userLogIn() {
        checkNewLogin();
    }

    private void checkNewLogin() {
        if(username.getText().isEmpty() || CommandLineUtility.nullCharacterString(username.getText())){
            wrongLogin.setText("Invalid Username (no null charcters/not empty)");
            return;
        }
        if(password.getText().isEmpty() || CommandLineUtility.nullCharacterString(password.getText())){
            wrongLogin.setText("Invalid Password (no null charcters/not empty)");
            return;
        }

        try {
            if(!Login.isLoginAvailable(username.getText())) {
                wrongLogin.setText("Login name is not available.");

            }
            if (Login.signUp(username.getText(), password.getText(), confirm.getText())) {
                currentUser = new Student(username.getText(), password.getText());
                newLogin = true;
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
            else
                wrongLogin.setText("Passwords don't match.");
            } catch (InternalImplementationException e) {
                e.printStackTrace();
                System.out.println("Error in implementation");
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