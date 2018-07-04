package com.groupone;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Course Registration Application 2000 ME XP Vista X 12 SE Deluxe Student Edition");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScheduleView panel = new ScheduleView();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(500, 500);

        frame.setVisible(true);
    }

}
