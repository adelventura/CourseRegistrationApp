package com.groupone;

import com.groupone.gui.LandingPage;
import com.groupone.gui.ScheduleView;
import com.groupone.gui.SignInWindow;
import com.groupone.middle.Student;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
//        JFrame frame = new JFrame("Course Registration");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        LandingPage panel = new LandingPage();
//        frame.getContentPane().add(panel);
//        frame.pack();
//        frame.setSize(200, 120);
//
//        frame.setVisible(true);

        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Student student = new Student("James", "Lee", "jamesl@gmail.com");
        ScheduleView panel = new ScheduleView(student);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 500);

        frame.setVisible(true);
    }

}
