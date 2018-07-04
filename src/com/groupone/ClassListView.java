package com.groupone;

import javax.swing.*;
import java.util.ArrayList;

public class ClassListView extends JPanel {

    JLabel titleLabel;

    public ClassListView(ArrayList<Course> courses) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleLabel = new JLabel("Class List", JLabel.LEFT);

        add(titleLabel);
        for (int i = 0; i < courses.size(); i += 1) {
            Course course = courses.get(i);
            ClassItemCell cell = new ClassItemCell(course);
            add(cell);
        }
    }

}

