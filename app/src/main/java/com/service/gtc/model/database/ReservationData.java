package com.service.gtc.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "ReservationData")
public class ReservationData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int reservationId;
    private String feel;
    private String model;
    private int milage;
    private int year;
    private String problem;


    public ReservationData(String feel, String model, int milage, int year, String problem) {
        this.feel = feel;
        this.model = model;
        this.milage = milage;
        this.year = year;
        this.problem = problem;
    }

    public ReservationData(int reservationId, String feel, String model, int milage, int year, String problem) {
        this.reservationId = reservationId;
        this.feel = feel;
        this.model = model;
        this.milage = milage;
        this.year = year;
        this.problem = problem;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getFeel() {
        return feel;
    }

    public String getModel() {
        return model;
    }

    public int getMilage() {
        return milage;
    }

    public int getYear() {
        return year;
    }

    public String getProblem() {
        return problem;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    @Override
    public String toString() {
        return "ReservationData{" +
                "reservationId=" + reservationId +
                ", feel='" + feel + '\'' +
                ", model='" + model + '\'' +
                ", milage=" + milage +
                ", year=" + year +
                ", problem='" + problem + '\'' +
                '}';
    }


}
