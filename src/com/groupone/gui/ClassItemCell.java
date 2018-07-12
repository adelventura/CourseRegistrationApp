package com.groupone.gui;

import com.groupone.middle.Course;

import javax.swing.*;
import java.awt.*;

public class ClassItemCell extends JPanel {

    private Course course;

    JLabel nameLabel;
    JLabel departmentLabel;
    JLabel roomLabel;
    JLabel timeLabel;
    JLabel dayLabel;
    JLabel courseNumLabel;
    JLabel instructorLabel;
    JLabel creditLabel;
    JCheckBox selectedBox;

    public ClassItemCell(Course course) {
        this(course.name, course.department, Integer.toString(course.room), course.time, course.day, Integer.toString(course.courseNum), course.instructor, Integer.toString(course.credits), true);
        this.course = course;
    }

    public ClassItemCell(String name, String department, String room, String time, String day, String courseNum, String instructor, String credits, boolean showBox) {
        setLayout(new GridLayout(1, 6));

        nameLabel = new JLabel(name, JLabel.CENTER);
        departmentLabel = new JLabel(department, JLabel.LEFT);
        roomLabel = new JLabel(room, JLabel.CENTER);
        timeLabel = new JLabel(time, JLabel.LEFT);
        dayLabel = new JLabel(day, JLabel.CENTER);
        courseNumLabel = new JLabel(courseNum, JLabel.CENTER);
        instructorLabel = new JLabel(instructor, JLabel.CENTER);
        creditLabel = new JLabel(credits, JLabel.CENTER);

        add(nameLabel);
        add(departmentLabel);
        add(roomLabel);
        add(timeLabel);
        add(dayLabel);
        add(courseNumLabel);
        add(instructorLabel);
        add(creditLabel);

        if (showBox) {
            selectedBox = new JCheckBox();
            add(selectedBox);
        } else {
            // just put in some space to take up where a checkbox would be but isn't
            add(new JLabel("Drop", JLabel.LEFT));
        }
    }

    public Course getCourse() {
        return course;
    }

    public boolean isSelected() {
        return selectedBox.isSelected();
    }

}
