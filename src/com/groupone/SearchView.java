package com.groupone;

import com.groupone.middle.Methods;
import com.groupone.middle.Student;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchView extends JPanel {
    private JLabel titleLabel;
    private JTextField searchField;
    private JLabel searchLabel;
    private JButton searchButton;
    private JButton clearButton;
    private JButton returnButton;
    private ClassListView classListView;
    public Student student;

    public SearchView(Student student) {
        this.student = student;

        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3, 1));
        titleLabel = new JLabel("Course Lookup", JLabel.LEFT);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 30));

        JPanel searchControls = new JPanel();

        searchButton = new JButton("Search");
        clearButton = new JButton("Clear");
        searchLabel = new JLabel("Search:    ", JLabel.CENTER);
        searchField = new JTextField();
        searchField.setBounds(5, 5, 75, 50);

        searchControls.setLayout(new GridLayout(1, 4));
        searchControls.add(searchLabel);
        searchControls.add(searchField);
        searchControls.add(searchButton);
        searchControls.add(clearButton);

        labelPanel.add(titleLabel);
        labelPanel.add(searchControls);
        // TODO: update column headers for additional fields
        labelPanel.add(new ClassItemCell("Name", "Department", "Room", "Time", "Day", false));
        add(labelPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(6, 1));

        classListView = new ClassListView(new ArrayList<>());
        JScrollPane scrollPane = new JScrollPane(classListView);

        add(scrollPane, BorderLayout.CENTER);

        JPanel panelControls = new JPanel();
        returnButton = new JButton("Return to View Schedule");
        panelControls.add(returnButton);

        add(panelControls, BorderLayout.SOUTH);

        ButtonListener listener = new ButtonListener();
        searchButton.addActionListener(listener);
        clearButton.addActionListener(listener);
        returnButton.addActionListener(listener);

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
            }
        }
    }

    private void clear() {
        searchField.setText("");
    }

    private void search() {
        ArrayList<Course> courses = Methods.selectAllCoursesFromASpecificDept(searchField.getText());
        classListView.setCourses(courses);
    }

    private void exitSearch() {
        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ScheduleView panel = new ScheduleView(student);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 500);

        frame.setVisible(true);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
    }

}
