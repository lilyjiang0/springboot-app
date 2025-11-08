package com.learnspring.cruddemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="review")
public class Review {
    // Define fields.
    // Annotate fields.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    // Define constructors.
    public Review() {
    }

    public Review(String comment) {
        this.comment = comment;
    }

    // Define getter and setters.
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Define toString().
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }
}
