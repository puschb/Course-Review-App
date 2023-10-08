package edu.virginia.cs.hw7;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "Reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "ID")
    private int id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Student", referencedColumnName = "ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Student student;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Course", referencedColumnName = "ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Course course;

    @Column(name = "Message", nullable = false)
    private String message;

    @Column(name = "Rating", nullable = false)
    private int rating;

    public Review() {
    }

    public Review(Student student, Course course, String message, int rating) {
        this.student = student;
        this.course = course;
        this.message = message;
        this.rating = rating;
    }

    public Review(String message, int rating) {
        this.message = message;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + getId() +
                ", student=" + getStudent() +
                ", course=" + getCourse() +
                ", message='" + getMessage() + '\'' +
                ", rating=" + getRating() +
                '}';
    }


}
