package edu.virginia.cs.hw7;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int courseID;

    @Column(name = "Department", nullable = false)
    private String department;

    @Column(name = "CatalogNumber", nullable = false)
    private int catalogNumber;

    @OneToMany(mappedBy = "course")
    private List<Review> reviews;


    public Course() {

    }

    public Course( String department, int catalogNumber) {
        this.department = department;
        this.catalogNumber = catalogNumber;
    }

    public void setCourseID(int id) {
        this.courseID = id;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(int catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + getCourseID() +
                ", department='" + getDepartment() + '\'' +
                ", catalogNumber=" + getCatalogNumber() +
                '}';
    }


}
