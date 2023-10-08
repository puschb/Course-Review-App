# Course Review GUI



## Description
I built a full stack course review application along with two other students. The stack we used was JavaFX for the frontend, java for the backend, and MySQL for our database. We used hibernate to optimize our SQL queries and make our project run more efficiently. In terms of architecture, we used the model-view-controller paradigm with a data layer for interacting with our database, a business logic layer for executing the logic, and a presentation layer which consisted of both our GUI and our command line interface.

## What it does
This project connects student to a database in order to write course reviews and view ones they, as well as other 
students, have submitted for a specified course. These students are only permitted to submit one review per course and 
these submission must be for courses that either already exist, and therefore are already in the database, or fit the 
format for a course (1-4 upper case letters for the course abbreviation and 1-4 numbers 0-9 for the course number).
Usernames and passwords must also fit a specific format, which includes strings that do not contain null characters.
Moreover, the user must confirm their password by retyping it and if it does not match their original input they will 
be prompted to make a correction. In general, if a user fails to meet any of the specified criteria for a given input 
they will be prompted to submit a new response in place of the one that was invalid.

## Running the code
Clone the repo: https://github.com/uva-cs3140-sp23/hw7-coursereview-ujx4ab-bhx5gh-dcq8fz.git

To run the application in the command line please see "CommandLineCourseReview" under the presentationLayer package
that can be run be executing "main()". There should be no changes needed to the run configuration.

To run the application GUI please see the "CourseReviewApplication" under the GUI package (there is also a command line 
form  that can be run under [CommandLineCourseReview.java]). Users should run the main method and make sure that they 
have the proper run configurations. In order to set these configurations following the instruction below: 

    1. Run the application once and allow it to throw and error (it should as it is not configured properly yet)
    2. Click the "CourseReviewApplication" drop down menu to the left of the green "run" at the top of the screen
    3. Select "Edit Configurations..."
    4. Select your version of java under build and run (may have to use Java 17 for this application)
    5. On the right press the "Modify options" drop down menu and select "add VM options"
    6. In the VM options arguments text box that now appears under build and run type "--module-path" then the 
       path to your local SDK in quotes ("") and then "--add-modules javafx.controls,javafx.fxml". 
       ** Note that you must at "/lib" to the end of the file path that you copy **
            Example: 
    --module-path "/Users/axelgyllenhoff/Desktop/javafx-sdk-17.0.2/lib" --add-modules javafx.controls,javafx.fxml
    7. Apply the changes, press OK, and run the main! We hope you enjoy.

## Screen shots of the Application

Homepage:

Login page:

Seeing Reviews Page:



