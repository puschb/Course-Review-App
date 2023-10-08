package edu.virginia.cs.hw7.GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseReviewStartController {
    public Button LoginButtonStart;
    public Button NewLoginButtonStart;
    public Button exit;

    public void switchToLogin() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/CourseReviewLogin.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) LoginButtonStart.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToNewLogin() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/CourseReviewNewLogin.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) NewLoginButtonStart.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

}