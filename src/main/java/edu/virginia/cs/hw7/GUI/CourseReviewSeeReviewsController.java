package edu.virginia.cs.hw7.GUI;

import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Review;
import edu.virginia.cs.hw7.Student;
import edu.virginia.cs.hw7.TableEntry;
import edu.virginia.cs.hw7.businessLogicLayer.Login;
import edu.virginia.cs.hw7.businessLogicLayer.MainMenu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jboss.jandex.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;


public class CourseReviewSeeReviewsController implements Initializable{
    @FXML
    private TableView<Review> reviewTable;
    @FXML
    private TableColumn<Review, String> commentColumn;
    @FXML
    private TableColumn<Review, Integer> ratingColumn;
    @FXML
    private Label averageRatingLabel;
    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        commentColumn.setCellFactory(tc -> {
            TableCell<Review, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(commentColumn.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        commentColumn.setCellValueFactory(new PropertyValueFactory<Review, String>("message"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<Review, Integer>("rating"));

        ObservableList<Review> test = getReviews();
        double averageRating = 0;
        for(Review r: test) {
            averageRating += r.getRating();
        }
        averageRating /= test.size();
        averageRatingLabel.setText(String.format("Average rating: %.2f",averageRating));
        reviewTable.setItems(test);
    }

    public ObservableList<Review> getReviews() {
        ObservableList<Review> reviews = FXCollections.observableArrayList();
        String courseOfInterst = MainMenu.getCourseOfInterest();
        reviews.addAll(MainMenu.getReviewsForCourseList(courseOfInterst));
        return reviews;
    }

    public void exit() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/CourseReviewMainMenu.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) exitButton.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
