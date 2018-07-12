package com.groupone.gui;

import com.groupone.middle.Course;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ClassListView extends JPanel {

    private JTable table;

    private ArrayList<Course> courses;
    private Boolean[] selectedCell;

    public ClassListView(ArrayList<Course> courses) {
        setLayout(new BorderLayout());

        table = new JTable();
        add(table.getTableHeader(), BorderLayout.PAGE_START);
        add(table, BorderLayout.CENTER);

        setCourses(courses);
    }

    public void setCourses(ArrayList<Course> courses) {
        String[] columns = {
                "Name",
                "Department",
                "Room",
                "Time",
                "Day",
                "Course Number",
                "Instructor",
                "Credits",
                "Drop"
        };

        this.courses = courses;
        selectedCell = new Boolean[courses.size()];
        for (int i = 0; i < courses.size(); i += 1) {
            selectedCell[i] = false;
        }

        AbstractTableModel model = new AbstractTableModel() {
            public int getColumnCount() { return columns.length; }
            public int getRowCount() { return courses.size(); }

            public String getColumnName(int col) {
                return columns[col];
            }

            public Object getValueAt(int row, int col) {
                Course course = courses.get(row);
                switch (col) {
                    case 0:
                        return course.name;
                    case 1:
                        return course.department;
                    case 2:
                        return course.room;
                    case 3:
                        return course.time;
                    case 4:
                        return course.day;
                    case 5:
                        return course.courseNum;
                    case 6:
                        return course.instructor;
                    case 7:
                        return course.credits;
                    case 8:
                        return selectedCell[row];
                    default:
                        throw new RuntimeException();
                }
            }

            public boolean isCellEditable(int row, int col) {
                return (col == 8);
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public void setValueAt(Object value, int row, int col) {
                if (col == 8) {
                    selectedCell[row] = (Boolean)value;
                }
            }
        };

        table.setModel(model);
    }

    public ArrayList<Course> selectedCourses() {
        ArrayList<Course> selectedCourses = new ArrayList<>();
        for (int i = 0; i < courses.size(); i += 1) {
            if (selectedCell[i]) {
                selectedCourses.add(courses.get(i));
            }
        }

        return selectedCourses;
    }

}

