package com.groupone.gui;

import com.groupone.middle.Course;
import com.groupone.middle.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ScheduleView extends JPanel {
    // ui component variables
    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel blankLabel;
    private JButton addButton;
    private JButton dropButton;
    private ClassListView classListView;

    public Student student;

    // ScheduleView constructor -- set up and lay out UI components
    public ScheduleView(Student student) {
        this.student = student;

        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        titleLabel = new JLabel(" Schedule Page", JLabel.LEFT);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 30));
        nameLabel = new JLabel("   Hi, " + student.firstName + " " + student.lastName, JLabel.LEFT);

        labelPanel.add(titleLabel);
        labelPanel.add(nameLabel);

        add(labelPanel, BorderLayout.NORTH);

        dropButton = new JButton("Drop Selection from Schedule");
        blankLabel = new JLabel("     ", JLabel.CENTER);
        addButton = new JButton("Go to Course Lookup");

        JPanel controls = new JPanel();
        controls.add(dropButton);
        controls.add(blankLabel);
        controls.add(addButton);

        add(controls, BorderLayout.SOUTH);

        // add classListView to JScrollPane
        classListView = new ClassListView(student.getStudentsCurrentCourseList(), "Drop");
        JScrollPane scrollPane = new JScrollPane(classListView);

        // add button listeners
        ButtonListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        dropButton.addActionListener(listener);

        add(scrollPane, BorderLayout.CENTER);
    }
    // add button handler
    // takes user to SearchView to add courses
    private void add() {
        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SearchView panel = new SearchView(student);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(900, 500);

        // grab current window (ScheduleView) and dispose of it
        frame.setVisible(true);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }

    // drop button handler
    private void drop() {
        // get courses to drop from classListView
        ArrayList<Course> droppedCourses = classListView.selectedCourses();
        // drop courses
        dropCourses(droppedCourses);

        // update course list with remaining classes
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

   // drop selected courses with middle layer
   public void dropCourses(ArrayList<Course> coursesToDrop) {
        student.getStudentsCurrentCourseList().removeAll(coursesToDrop);
        //deletes courses from student course list in database
        Student.deleteCourses(coursesToDrop);
    }


}
