package com.example.prabhmeh_expbook;

/*
Class representing a single Expense Object
 */
public class Expense {
    private String name;
    private String date;
    private float charge;
    private String comment;

    public Expense(String name, String date, float charge) {
        this.name = name;
        this.date = date;
        this.charge = charge;
    }

    public Expense(String name, String date, float charge, String comment) {
        this.name = name;
        this.date = date;
        this.charge = charge;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getCharge() {
        return charge;
    }

    public void setCharge(float charge) {
        this.charge = charge;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
