package com.groupone.gui;

import com.groupone.middle.Course;
import com.groupone.middle.Methods;
import com.groupone.middle.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchView extends JPanel {

    // ui component variables
    private JLabel titleLabel;
    private JTextField searchField;
    private JLabel searchLabel;
    private JLabel blankLabel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton addButton;
    private JButton returnButton;
    private ClassListView classListView;

    public Student student;

    // SearchView constructor -- set up and lay out UI components
    public SearchView(Student student) {
        this.student = student;

        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(2, 1));
        titleLabel = new JLabel(" Course Lookup", JLabel.LEFT);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel searchControls = new JPanel();

        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        searchLabel = new JLabel("Search by Department:    ", JLabel.RIGHT);
        searchField = new JTextField();
        searchField.setBounds(5, 5, 75, 50);

        searchControls.setLayout(new GridLayout(1, 4));
        searchControls.add(searchLabel);
        searchControls.add(searchField);
        searchControls.add(searchButton);
        searchControls.add(clearButton);

        labelPanel.add(titleLabel);
        labelPanel.add(searchControls);
        add(labelPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 1));

        // add classListView to JScrollPane
        classListView = new ClassListView(new ArrayList<>(), "Add");
        JScrollPane scrollPane = new JScrollPane(classListView);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelControls = new JPanel();
        addButton = new JButton("Add Selection to Schedule");
        blankLabel = new JLabel("     ", JLabel.CENTER);
        returnButton = new JButton("Return to View Schedule");
        panelControls.add(addButton);
        panelControls.add(blankLabel);
        panelControls.add(returnButton);

        add(panelControls, BorderLayout.SOUTH);

        // add button listeners
        ButtonListener listener = new ButtonListener();
        searchButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        returnButton.addActionListener(listener);
        addButton.addActionListener(listener);

    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == searchButton) {
                search();
            } else if (source == clearButton) {
                clear();
            } else if (source == returnButton) {
                exitSearch();
            } else if (source == addButton) {
                add();
            }
        }
    }

    // clear button handler
    // clears text from search field
    private void clear() {
        searchField.setText("");
    }

    // search button handler
    // passes search field to middle layer and updates search results
    private void search() {
            ArrayList<Course> courses = Methods.selectAllCoursesFromASpecificDept(searchField.getText());
            if(courses == null) {
                JOptionPane.showMessageDialog(null, "Please enter a department that exists.");
            }else {
                classListView.setCourses(courses);
            }

        }
    // add button handler
    // grabs selected courses from classListView and gives to middle layer
    private void add() {
        ArrayList<Course> addedCourses = classListView.selectedCourses();
        for (Course course : addedCourses) {
            student.addCourseToExistingCourseList(course.courseNum, course.department);
        }

        // TODO: delete when db methods for adding classes works
        // adding in memory just for demoing. no check for time conflict or existing course
        student.courses.addAll(addedCourses);
    }

    // creates new ScheduleView window and closes SearchView window
    private void exitSearch() {
        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScheduleView panel = new ScheduleView(student);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(900, 500);

        frame.setVisible(true);
        // grab current window (SearchView) and dispose of it
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }
}
