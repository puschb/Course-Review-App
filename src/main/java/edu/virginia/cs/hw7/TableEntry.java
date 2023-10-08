package edu.virginia.cs.hw7;

import javafx.beans.property.SimpleStringProperty;

public class TableEntry {
    private String message;
    private int rating;

    public TableEntry(String message, int rating) {
        this.message = message;
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public int getRating() {
        return rating;
    }
}
