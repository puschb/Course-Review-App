package edu.virginia.cs.hw7.dataLayer;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Statement;

import edu.virginia.cs.hw7.Course;
import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Review;
import edu.virginia.cs.hw7.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.persistence.PersistenceException;
import javax.xml.crypto.Data;
import java.util.*;

public class DatabaseManager {
    private static Connection connection;
    Statement statement;
    boolean notConnected = true;
    private static Session session;

    public static void main(String args[]) {
        //dbm.startDatabase();


        ///Course c = new Course("CS",3140);
        //Student s = new Student("login1","pass2");
        //Review r = new Review("random shit for review",5);
       // r.setCourse(c);
        //r.setStudent(s);
        //s.setID(23);
        DatabaseManager.startDatabase();
        String hql = "Select loginName from Student s where password = :pass";
        Query<String> q = session.createQuery(hql, String.class);
        q.setParameter("pass","apple");
        for(String s: q.getResultList()) {
            System.out.println(s);
        }

        session.getTransaction().commit();
        session.close();
        DatabaseUtil.shutdown();




        //dbm.endDatabaseSession();

    }

    public static void startDatabase(){
        //java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        session = DatabaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
    }

    public static void closeDatabase() {
        session.close();
    }

    //session must be previously closed
    public static void shutdownDatabase() {
        session = DatabaseUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.close();
        DatabaseUtil.shutdown();
    }

    public void clear(){


    }

    /*
    public void deleteTables() {
        try {Objects.requireNonNull(connection, "Unestablished Connection");
        }catch (NullPointerException e){
            throw new IllegalStateException();
        }
        try {
            statement.execute("DROP TABLE IF EXISTS Students;");
            statement.execute("DROP TABLE IF EXISTS Reviews;");\
        } catch (SQLiteException e) {
            throw new IllegalStateException("Error: One or more target tables do not exist");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

     */

    /**
     *
     * @param loginName - strign representing the name used for login (should be unique in the database)
     * @return - returns true if a student with the information stored in the student entity
     * exists in the Students database, false if not
     */
    //checked
    public static boolean doesStudentExist(String loginName) throws InternalImplementationException{
        if(loginName == null)
            throw new InternalImplementationException("LoginName is null");
        String hql = "SELECT s FROM Student s WHERE s.loginName = :loginName";
        startDatabase();
        Query<Student> q = session.createQuery(hql,Student.class);
        q.setParameter("loginName",loginName);
        List<Student> results = q.getResultList();
        closeDatabase();
        return results.size() > 0;
    }

    //checked
    public static boolean doesStudentExist(String loginName, String password) throws InternalImplementationException{
        if(loginName == null || password == null)
            throw new InternalImplementationException("Login name or pass is null");
        String hql = "SELECT s FROM Student s WHERE s.loginName = :loginName and s.password = :password";
        startDatabase();
        Query<Student> q = session.createQuery(hql,Student.class);
        q.setParameter("loginName",loginName);
        q.setParameter("password",password);
        List<Student> results = q.getResultList();
        closeDatabase();
        return results.size() > 0;
    }
    private Student getStudentByID(Student student) {
        return null;
    }

    /**
     *  takes a student, that must have null for the id value, and inserts them into the
     *  database.
     * @param student - this is a student object that doesn't already exist in the database
     * @throws InternalImplementationException - throws if student wit the same info already exists
     */
    //checked
    public static void addStudent(Student student) throws InternalImplementationException {
        if(student == null)
            throw new InternalImplementationException("student object is null");
        if(doesStudentExist(student.getLoginName()))
            throw new InternalImplementationException(String.format("Student %s already exists in database",
                    student.getLoginName()));
        try {
            startDatabase();
            session.persist(student);
            session.getTransaction().commit();
            closeDatabase();
        } catch(PersistenceException e) {
            throw new InternalImplementationException("Error in persisting student");
        }
    }

    /**
     *  take a review object and inserts it into the database
     *  check if review is already in the database
     *  THIS METHOD SHOULD ONLY BE CALLED AFTER IT HAS BEEN CONFIRMED THAT STUDENT AND COURSE ALREADY EXIST IN THE DB
     * @param review - review that is not in the database already
     */
    //checked
    public static void addReview(Review review) throws InternalImplementationException{
        if( review == null)
            throw new InternalImplementationException("review object is null");
        if(doesReviewExist(review))
            throw new InternalImplementationException("Review already exists");
        review.setStudent(addIDtoStudent(review.getStudent()));
        review.setCourse(addIDtoCourse(review.getCourse()));
        try {
            startDatabase();
            session.persist(review);
            session.getTransaction().commit();
            closeDatabase();
        } catch(PersistenceException e) {
            throw new InternalImplementationException("Error in persisting review");
        }

    }
    //should only be called by addReview
    private static Student addIDtoStudent(Student student) {
        String hql = "FROM Student s where s.loginName = :login AND s.password = :pass";
        startDatabase();
        Query<Student> q = session.createQuery(hql,Student.class);
        q.setParameter("login",student.getLoginName());
        q.setParameter("pass", student.getPassword());
        List<Student> result= q.getResultList();
        closeDatabase();
        return result.get(0);
    }

    //should only be called by addReview
    private static Course addIDtoCourse(Course course) {
        String hql = "FROM Course c where c.catalogNumber = :catNum AND c.department = :department";
        startDatabase();
        Query<Course> q = session.createQuery(hql,Course.class);
        q.setParameter("catNum",course.getCatalogNumber());
        q.setParameter("department", course.getDepartment());
        List<Course> result= q.getResultList();
        closeDatabase();
        return result.get(0);
    }

    /**
     *  takes a course and adds it to a database
     *  checks if the course is already in the database
     * @param course
     */
    //checked
    public static void addCourse(Course course) throws InternalImplementationException{
        if(course == null)
            throw new InternalImplementationException("course is null");
        if(doesCourseExist(course.getDepartment(),course.getCatalogNumber()))
            throw new InternalImplementationException("Course already exists");
        try {
            startDatabase();
            session.persist(course);
            session.getTransaction().commit();
            closeDatabase();
        } catch(PersistenceException e) {
            throw new InternalImplementationException("Error in persisting course");
        }

    }

    /**
     *  checks if a course exists in the database
     *  returns boolean - true if it exists, false if not
     * @param department
     * @param catNum
     */
    //checked
    public static boolean doesCourseExist(String department, int catNum) throws InternalImplementationException{
        if(department == null) {
            throw new InternalImplementationException("department for course is null");
        }
        String hql = "FROM Course c WHERE c.catalogNumber = :catalogNbr and c.department = :department ";
        startDatabase();
        Query<Course> q = session.createQuery(hql,Course.class);
        q.setParameter("catalogNbr", catNum);
        q.setParameter("department",department);
        List<Course> results = q.getResultList();
        closeDatabase();
        return results.size() > 0;
    }

    /**
     *  checks if a review exists in the database
     *  returns boolean
     * @param review
     */
    //literally checks just for duplicates
    public static boolean doesReviewExist(Review review) throws InternalImplementationException{
        if(review == null)
            throw new InternalImplementationException("review is null");
        String hql = "FROM Review r WHERE r.student.password = :sPass and r.student.loginName = :sLogin and " +
                "r.course.department = :cDepartment and r.course.catalogNumber = :cCatNum " +
                "and r.message = :message and r.rating = :rating ";
        startDatabase();
        Query<Review> q = session.createQuery(hql,Review.class);

        try {
            q.setParameter("sPass", review.getStudent().getPassword());
            q.setParameter("sLogin", review.getStudent().getLoginName());

            q.setParameter("cDepartment", review.getCourse().getDepartment());
            q.setParameter("cCatNum", review.getCourse().getCatalogNumber());


            q.setParameter("message", review.getMessage());
            q.setParameter("rating", review.getRating());
        }catch (NullPointerException e) {
            throw new InternalImplementationException("review contains null");
        }
        List<Review> results = q.getResultList();
        closeDatabase();
        return results.size() > 0;
    }




    /**
     *  takes in student and course to check if the student has already reviews the course
     *  returns boolean
     * @param loginName
     * @param course
     */

    //currently not using password to identify student since login name should be unique
    public static boolean hasStudentReviewedCourse(String loginName, Course course) {
        String hql = "FROM Review r WHERE r.student.loginName = :sLogin AND r.course.catalogNumber = :cCatalogNum " +
                "AND r.course.department = :cDepartment";
        startDatabase();
        Query<Review> q = session.createQuery(hql, Review.class);
        q.setParameter("sLogin", loginName);
        q.setParameter("cCatalogNum", course.getCatalogNumber());
        q.setParameter("cDepartment", course.getDepartment());
        List<Review> reviewList = q.getResultList();
        closeDatabase();
        return reviewList.size() > 0;
    }


    /**
     *  gets the all of the reviews that a course has
     *  returns list of reviews
     * @param course
     */
    public static List<Review> getCourseReviews(Course course) {
        String hql = "FROM Review f WHERE f.course.department = :cDepartment AND f.course.catalogNumber = :cCatalogNum";
        startDatabase();
        Query<Review> q = session.createQuery(hql, Review.class);
        q.setParameter("cDepartment", course.getDepartment());
        q.setParameter("cCatalogNum",course.getCatalogNumber());
        List<Review> reviewList = q.getResultList();
        closeDatabase();
        return reviewList;
    }
}
