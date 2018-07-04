package com.groupone;

import javax.swing.*;
import java.util.ArrayList;

public class ClassListView extends JPanel {

    private JLabel titleLabel;
    private ArrayList<ClassItemCell> cells;

    public ClassListView(ArrayList<Course> courses) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        titleLabel = new JLabel("Class List", JLabel.LEFT);

        setCourses(courses);
    }

    public void setCourses(ArrayList<Course> courses) {
        removeAll();

        add(titleLabel);

        cells = new ArrayList<>();
        for (int i = 0; i < courses.size(); i += 1) {
            Course course = courses.get(i);
            ClassItemCell cell = new ClassItemCell(course);
            cells.add(cell);
            add(cell);
        }

        updateUI();
    }

    public ArrayList<Course> selectedCourses() {
        ArrayList<Course> selected = new ArrayList<>();
        for (int i = 0; i < cells.size(); i += 1) {
            ClassItemCell cell = cells.get(i);

            if (cell.isSelected()) {
                selected.add(cell.getCourse());
            }
        }

        return selected;
    }

}

