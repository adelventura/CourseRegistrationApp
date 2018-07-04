package com.groupone;

import javax.swing.*;

public class ClassListView extends JPanel {

    JLabel titleLabel;

    public ClassListView() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleLabel = new JLabel("Class List", JLabel.LEFT);

        add(titleLabel);
        for (int i = 0; i < 10; i += 1) {
            String name = "CS" + Integer.toString((int)(Math.random() * 1000));
            Course course = new Course(name, "Computer Science");
            ClassItemCell cell = new ClassItemCell(course);
            add(cell);
        }
    }

}

