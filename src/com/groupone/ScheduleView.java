package com.groupone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScheduleView extends JPanel {

    JLabel titleLabel;
    JButton addButton;
    ClassListView classListView;
    JButton dropButton;

    public ScheduleView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Schedule Page", JLabel.LEFT);
        addButton = new JButton("Add");
        dropButton = new JButton("Drop");

        ArrayList<Course> courses = new ArrayList<>();
        for (int i = 0; i < 10; i += 1) {
            String name = "CS" + Integer.toString((int)(Math.random() * 1000));
            Course course = new Course(name, "Computer Science");
            courses.add(course);
        }

        classListView = new ClassListView(courses);

        ButtonListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        dropButton.addActionListener(listener);

        add(titleLabel);
        add(addButton);
        add(classListView);
        add(dropButton);
    }

    private void add() {
        JOptionPane.showMessageDialog(null, "todo: go to the add courses page");
    }

    private void drop() {
        JOptionPane.showMessageDialog(null, "todo: drop classes");
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == addButton) {
                add();
            } else if (source == dropButton) {
                drop();
            }
        }
    }

}
