package edu.virginia.cs.hw7.GUI;

import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.businessLogicLayer.MainMenu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.collections.*;
import javafx.beans.value.*;
import javafx.stage.Stage;
import org.jboss.jandex.Main;

import javax.swing.event.ChangeListener;
import java.io.IOException;

public class CourseReviewSubmitReviewController {
    @FXML
    public TextArea submissionField;
    @FXML
    public TextField rating;
    @FXML
    public ChoiceBox<Integer> ratingChoice;
    @FXML
    public Button submit;
    @FXML
    public Button exit;
    @FXML
    public Label ratingError;
    @FXML
    public Label reviewError;
    @FXML
    private int ratingNum;

    public void submit() {
        if (submissionField.getText() != null && rating.getText() != null) {
            clearState();

            if(MainMenu.convertValidRating(rating.getText()) == -1 && submissionField.getText().isEmpty()) {
                reviewError.setText("Please enter a non empty message and a rating between 1-5.");
                return;
            }
            if(MainMenu.convertValidRating(rating.getText()) == -1) {
                ratingError.setText("1-5 Please");
                return;
            }
            if(submissionField.getText().isEmpty()) {
                reviewError.setText("Please enter a review");
                return;
            }
            ratingNum = MainMenu.convertValidRating(rating.getText());
            try {
                MainMenu.addReview(submissionField.getText(), ratingNum, CourseReviewMainMenuController.getCurrentUser(), MainMenu.getCourseOfInterest());
            } catch (InternalImplementationException e) {
                throw new RuntimeException(e);
            }
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/CourseReviewMainMenu.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) submit.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void exit() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/CourseReviewMainMenu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) exit.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void clearState(){
        reviewError.setText("");
        ratingError.setText("");
    }


}
