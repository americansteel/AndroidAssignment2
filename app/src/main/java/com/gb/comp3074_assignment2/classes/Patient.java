package com.gb.comp3074_assignment2.classes;

/**
 * Created by Dylan on 2017-11-30.
 */

public class Patient extends Person {

    private int doctorId;
    private String room;

    public Patient(int id, String firstName, String lastName, String department, int doctorId, String room) {
        super(id, firstName, lastName, department);
        this.doctorId = doctorId;
        this.room = room;

    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
