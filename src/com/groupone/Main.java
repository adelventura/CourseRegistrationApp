package com.groupone;

import com.groupone.middle.Student;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO: get from login or something
        ScheduleView panel = new ScheduleView();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 500);

        frame.setVisible(true);
    }

}
