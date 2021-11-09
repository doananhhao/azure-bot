package com.lexisnexis.risk.bot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "kudos")
public class Kudo {

    @Id
    private String id;

    private int day;

    private int month;

    private int year;

    private int maximumPointPerUser;

    public Kudo() {
    }

    public Kudo(int day, int month, int year, int maximumPointPerUser) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.maximumPointPerUser = maximumPointPerUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMaximumPointPerUser() {
        return maximumPointPerUser;
    }

    public void setMaximumPointPerUser(int maximumPointPerUser) {
        this.maximumPointPerUser = maximumPointPerUser;
    }
}
