package com.groupone;

import com.groupone.middle.Student;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: get from login or something
        Student student = new Student("Ashley", "ashleyisthebest1", "Ashley", "DelVentura", "do.not.reply@idk.com");
        ScheduleView panel = new ScheduleView(student);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 500);

        frame.setVisible(true);
    }

}
