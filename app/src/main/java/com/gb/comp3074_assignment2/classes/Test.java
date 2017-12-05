package com.gb.comp3074_assignment2.classes;

/**
 * Created by Dylan on 2017-12-04.
 */

public class Test {

    private int id;
    private int patientId;
    private int bpl;
    private int bph;
    private int temperature;

    public Test(int id, int patientId, int bpl, int bph, int temperature) {

        this.id = id;
        this.patientId = patientId;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getBpl() {
        return bpl;
    }

    public void setBpl(int bpl) {
        this.bpl = bpl;
    }

    public int getBph() {
        return bph;
    }

    public void setBph(int bph) {
        this.bph = bph;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
