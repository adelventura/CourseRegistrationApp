package com.groupone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScheduleView extends JPanel {

    private JLabel titleLabel;
    private JLabel listTitleLabel;
    private JButton addButton;
    private ClassListView classListView;
    private JButton dropButton;

    public ScheduleView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Schedule Page", JLabel.LEFT);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 30));

        listTitleLabel  = new JLabel("Class List", JLabel.LEFT);
        addButton = new JButton("Add");
        dropButton = new JButton("Drop");

        classListView = new ClassListView(getMyCourses());
        JScrollPane scrollPane = new JScrollPane(classListView);

        ButtonListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        dropButton.addActionListener(listener);

        add(titleLabel);
        add(listTitleLabel);
        add(new ClassItemCell("Name", "Department", "Room", "Time", "Day", false));
        add(scrollPane);
        add(addButton);
        add(dropButton);
    }

    private void add() {
        JOptionPane.showMessageDialog(null, "todo: go to the add courses page");
    }

    private void drop() {
        ArrayList<Course> droppedCourses = classListView.selectedCourses();
        dropCourses(droppedCourses);

        classListView.setCourses(getMyCourses());
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

    // use database
    private ArrayList<Course> courses;

    private ArrayList<Course> getMyCourses() {
        // TODO: get from database
        if (courses == null) {
            courses = new ArrayList<>();
            for (int i = 0; i < 10; i += 1) {
                String name = "CS" + Integer.toString((int) (Math.random() * 1000));
                Course course = new Course(name, "Computer Science");
                courses.add(course);
            }
        }

        return courses;
    }

    public void dropCourses(ArrayList<Course> coursesToDrop) {
        // TODO: change database
        this.courses.removeAll(coursesToDrop);
    }

}
