package com.groupone;

public class Course {

    public String name;
    public String department;
    public int room;
    public String time;
    public String day;

    public Course(String name, String department) {
        this.name = name;
        this.department = department;

        // get all this from the database eventually
        this.room = (int)(Math.random() * 1000);
        this.time = "1:00 pm - 2:00 pm";
        this.day = "MWF";
    }

}
