package com.groupone;

import javax.swing.*;

public class ClassItemCell extends JPanel {

    private Course course;

    JLabel nameLabel;
    JLabel departmentLabel;
    JLabel roomLabel;
    JLabel timeLabel;
    JLabel dayLabel;
    JCheckBox selectedBox;

    public ClassItemCell(Course course) {
        this.course = course;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        nameLabel = new JLabel(course.name, JLabel.LEFT);
        departmentLabel = new JLabel(course.department, JLabel.LEFT);
        roomLabel = new JLabel(Integer.toString(course.room), JLabel.LEFT);
        timeLabel = new JLabel(course.time, JLabel.LEFT);
        dayLabel = new JLabel(course.day, JLabel.LEFT);
        selectedBox = new JCheckBox();

        add(nameLabel);
        add(departmentLabel);
        add(roomLabel);
        add(timeLabel);
        add(dayLabel);
        add(selectedBox);
    }

    public Course getCourse() {
        return course;
    }

    public boolean isSelected() {
        return selectedBox.isSelected();
    }

}
