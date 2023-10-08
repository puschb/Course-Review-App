package edu.virginia.cs.hw7;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "LoginName", nullable = false)
    private String loginName;

    @Column(name = "Password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "student")
    private List<Review> reviews;

    public Student() {
    }

    public Student( String loginName, String password) {
        this.loginName = loginName;
        this.password = password;
    }

    public void setID(int id) {
        this.id = id;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", loginName='" + getLoginName() + '\'' +
                ", password='" + getPassword() + '\''  +
                '}';
    }
}
