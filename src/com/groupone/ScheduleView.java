package com.groupone;

import com.groupone.middle.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

//I think I replaced all of them, but just so you know:
//anywhere you call the getMyCourses() method should be replaced with:
//student.showStudentsCurrentCourseList()

public class ScheduleView extends JPanel {

    private JLabel titleLabel;
    private JButton addButton;
    private ClassListView classListView;
    private JButton dropButton;
    public Student student;

    public ScheduleView(Student student) {
        this.student = student;

        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        titleLabel = new JLabel("Schedule Page", JLabel.LEFT);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 30));

        labelPanel.add(titleLabel);
        
        //NOTE: the other fields need to be added to the label
        labelPanel.add(new ClassItemCell("Name", "Department", "Room", "Time", "Day", false));
        add(labelPanel, BorderLayout.NORTH);

        addButton = new JButton("Add");
        dropButton = new JButton("Drop");

        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1, 2));
        controls.add(addButton);
        controls.add(dropButton);

        add(controls, BorderLayout.SOUTH);

        classListView = new ClassListView(student.getStudentsCurrentCourseList());
        JScrollPane scrollPane = new JScrollPane(classListView);

        ButtonListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        dropButton.addActionListener(listener);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void add() {
        JOptionPane.showMessageDialog(null, "todo: go to the add courses page");
    }

    private void drop() {
        ArrayList<Course> droppedCourses = classListView.selectedCourses();
        dropCourses(droppedCourses);

        classListView.setCourses(student.getStudentsCurrentCourseList());
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

    //I deleted your getMyCourses() method and instead integrated it with one of my
    //existing methods; now it connects directly to the database

   public void dropCourses(ArrayList<Course> coursesToDrop) {
        // TODO: change database
        student.getStudentsCurrentCourseList().removeAll(coursesToDrop);
    }

}
