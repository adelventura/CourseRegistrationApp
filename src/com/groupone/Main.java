package com.groupone;

import com.groupone.gui.LandingPage;
import com.groupone.gui.ScheduleView;
import com.groupone.gui.SignInWindow;
import com.groupone.middle.Methods;
import com.groupone.middle.Student;
import java.sql.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Hard coded the login for testing
        // Username: James, Password: password
    

        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LandingPage panel = new LandingPage();
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(200, 140);

        frame.setVisible(true);
    }

}
