package com.sunrise.dental.model;

public class Treatment {

    private int treatmentId;

    private int patientId;

    private String treatmentName;

    private String description;

    private String treatmentDate;

    private double cost;


    public Treatment() {

    }


    public int getTreatmentId() {

        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {

        this.treatmentId = treatmentId;
    }


    public int getPatientId() {

        return patientId;
    }

    public void setPatientId(int patientId) {

        this.patientId = patientId;
    }


    public String getTreatmentName() {

        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {

        this.treatmentName = treatmentName;
    }


    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }


    public String getTreatmentDate() {

        return treatmentDate;
    }

    public void setTreatmentDate(String treatmentDate) {

        this.treatmentDate = treatmentDate;
    }


    public double getCost() {

        return cost;
    }

    public void setCost(double cost) {

        this.cost = cost;
    }

}