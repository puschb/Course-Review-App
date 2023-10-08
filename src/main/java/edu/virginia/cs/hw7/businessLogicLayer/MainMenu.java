package edu.virginia.cs.hw7.businessLogicLayer;

import edu.virginia.cs.hw7.*;
import edu.virginia.cs.hw7.dataLayer.DatabaseManager;

import java.util.List;
import java.util.regex.Pattern;

public class MainMenu {
    private static String REGEX_VALID_COURSE_INPUT = "^[A-Z]{1,4} [0-9]{4}$";
    private static String REGEX_VALID_RATING_INPUT = "^[1-5]$";
    private static String courseOfInterest;

    public static boolean isValidCourseInput(String input) {
        return Pattern.matches(REGEX_VALID_COURSE_INPUT,input);
    }

    //must be called after is ValidCoursreInput has been called
    public static boolean doesCourseExist(String input) throws InternalImplementationException {
        String department = input.split(" ")[0];
        int catNum = Integer.parseInt(input.split(" ")[1]);
        if (!DatabaseManager.doesCourseExist(department,catNum)) {
            DatabaseManager.addCourse(new Course(department,catNum));
            return false;
        }
        return true;
    }

    public static boolean hasStudentReviewedCourse(String input, Student student) {
        String department = input.split(" ")[0];
        int catNum = Integer.parseInt(input.split(" ")[1]);
        Course course = new Course(department,catNum);
        String loginName = student.getLoginName();
        return DatabaseManager.hasStudentReviewedCourse(loginName,course);
    }

    public static int convertValidRating(String ratingInput){
        if( Pattern.matches(REGEX_VALID_RATING_INPUT,ratingInput))
            return Integer.parseInt(ratingInput);
        return -1;
    }

    public static void addReview(String message, int rating, Student student, String courseInput)
            throws InternalImplementationException {
        Review review = new Review(message,rating);
        String department = courseInput.split(" ")[0];
        int catNum = Integer.parseInt(courseInput.split(" ")[1]);
        Course course = new Course(department,catNum);
        review.setCourse(course);
        review.setStudent(student);
        DatabaseManager.addReview(review);

    }


    public static String getReviewsForCourse(String courseInput) {
        String department = courseInput.split(" ")[0];
        int catNum = Integer.parseInt(courseInput.split(" ")[1]);
        Course course = new Course(department,catNum);

        List<Review> reviews = DatabaseManager.getCourseReviews(course);
        if(reviews.size() ==0)
            return null;
        StringBuilder formattedReviews = new StringBuilder(String.format("Reviews for course %s \n", courseInput));
        double average = 0;
        for(Review r : reviews) {
            formattedReviews.append(r.getMessage()).append("\n");
            average += r.getRating();
        }
        average /= reviews.size();
        formattedReviews.append("Course average: %.2f");
        String toReturn = String.format(formattedReviews.toString(),average);

        return toReturn;
    }

    public static List<Review> getReviewsForCourseList(String courseInput) {
        String department = courseInput.split(" ")[0];
        int catNum = Integer.parseInt(courseInput.split(" ")[1]);
        Course course = new Course(department,catNum);
        List<Review> reviews = DatabaseManager.getCourseReviews(course);
        return reviews;
    }

    public static void setCourseOfInterest(String course){
        courseOfInterest = course;
    }

    public static String getCourseOfInterest(){
        return courseOfInterest;
    }
}
