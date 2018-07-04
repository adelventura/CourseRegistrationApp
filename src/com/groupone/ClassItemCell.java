package com.groupone;

import javax.swing.*;
import java.awt.*;

public class ClassItemCell extends JPanel {

    private Course course;

    JLabel nameLabel;
    JLabel departmentLabel;
    JLabel roomLabel;
    JLabel timeLabel;
    JLabel dayLabel;
    JCheckBox selectedBox;

    public ClassItemCell(Course course) {
        this(course.name, course.department, Integer.toString(course.room), course.time, course.day, true);
        this.course = course;
    }

    public ClassItemCell(String name, String department, String room, String time, String day, boolean showBox) {
        setLayout(new GridBagLayout());

        nameLabel = new JLabel(name, JLabel.LEFT);
        departmentLabel = new JLabel(department, JLabel.LEFT);
        roomLabel = new JLabel(room, JLabel.LEFT);
        timeLabel = new JLabel(time, JLabel.LEFT);
        dayLabel = new JLabel(day, JLabel.LEFT);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.anchor = GridBagConstraints.SOUTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        add(nameLabel, constraints);
        add(departmentLabel, constraints);
        add(roomLabel, constraints);
        add(timeLabel, constraints);
        add(dayLabel, constraints);

        if (showBox) {
            selectedBox = new JCheckBox();
            add(selectedBox, constraints);
        } else {
            // just put in some space to take up where a checkbox would be but isn't
            add(new JLabel("Drop", JLabel.LEFT), constraints);
        }
    }

    public Course getCourse() {
        return course;
    }

    public boolean isSelected() {
        return selectedBox.isSelected();
    }

}
