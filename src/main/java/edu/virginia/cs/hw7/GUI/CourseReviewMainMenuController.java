package edu.virginia.cs.hw7.GUI;

import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Review;
import edu.virginia.cs.hw7.Student;
import edu.virginia.cs.hw7.businessLogicLayer.MainMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseReviewMainMenuController {
    public Button submitReview;
    public Button seeReviews;
    public Button logOut;
    public TextField courseInput;
    public Label wrongInput;
    public TableView reviewsTable;
    public TableColumn ratingColumn;
    public TableColumn reviewColumn;


    ObservableList<Review> list = FXCollections.observableArrayList();

    public static Student getCurrentUser() {
        if (CourseReviewNewLoginController.newLogin)
            return CourseReviewNewLoginController.currentUser;
        else
            return CourseReviewLoginController.currentUser;
    }

    public void submitReview() {
        try {
            if (!MainMenu.isValidCourseInput(courseInput.getText())) {
                wrongInput.setText("Invalid format for course input.");
                return;
            }
            if (MainMenu.doesCourseExist(courseInput.getText()) &&
                    MainMenu.hasStudentReviewedCourse(courseInput.getText(), getCurrentUser())) {
                wrongInput.setText("You've already reviewed this course.");
                return;
            }
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("/CourseReviewSubmitReview.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            MainMenu.setCourseOfInterest(courseInput.getText());
            clearState();
            Stage stage = (Stage) submitReview.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();




        } catch (InternalImplementationException e) {
            e.printStackTrace();
            System.out.println("Error in implementation.");
        }
    }

    public void seeReviews() {
        String reviews = null;
        if (!MainMenu.isValidCourseInput(courseInput.getText())) {
            wrongInput.setText("Invalid format for course input.");
        }
        else {
            reviews = MainMenu.getReviewsForCourse(courseInput.getText());
            if (reviews == null)
                wrongInput.setText("There are no reviews for this course");
            else {
                Parent root;
                try {
                    MainMenu.setCourseOfInterest(courseInput.getText());
                    root = FXMLLoader.load(getClass().getResource("/CourseReviewSeeReviews.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                clearState();
                Stage stage = (Stage) seeReviews.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    public void logOut() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/CourseReviewStart.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) logOut.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    /*
    private void initializeList() {
        list.addAll(MainMenu.getReviewsForCourseList(courseInput.getText()));
        if(list == null)
            System.out.println("Null");
    }

    public void initializeTable() {
        ratingColumn.setCellFactory(new PropertyValueFactory("rating"));
        reviewColumn.setCellFactory(new PropertyValueFactory("message"));
        reviewsTable.setItems(list);
        initializeList();

    }
     */

    public void clearState(){
        wrongInput.setText("");
        courseInput.setText("");
    }

}
