package com.groupone.middle;

import java.sql.*;

public class Course {
    public String name;
    public String department;
    public int room;
    public String time;
    public String day;
    public int courseNum;
    public int credits;
    public String instructor;

    public Course(String name, String department, int room, String time,
                  String day, int courseNum, int credits, String instructor) {
        this.name = name;
        this.department = department;
        this.room = room;
        this.time = time;
        this.courseNum = courseNum;
        this.day = day;
        this.credits = credits;
        this.instructor = instructor;

    }


}
