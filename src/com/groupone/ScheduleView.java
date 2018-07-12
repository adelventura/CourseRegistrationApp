package com.groupone;

import com.groupone.middle.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScheduleView extends JPanel {

    private JLabel titleLabel;
    private JLabel nameLabel;
    private JLabel blankLabel;
    private JButton addButton;
    private ClassListView classListView;
    private JButton dropButton;
    public Student student;

    public ScheduleView(Student student) {
        this.student = student;

        setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3, 1));
        titleLabel = new JLabel(" Schedule Page", JLabel.LEFT);
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.PLAIN, 30));
        nameLabel = new JLabel("   Hi, " + student.firstName + " " + student.lastName, JLabel.LEFT);

        labelPanel.add(titleLabel);
        labelPanel.add(nameLabel);

        labelPanel.add(new ClassItemCell("Name               ", "Department", "Room", "Time", "Day", "Course Number", "Instructor", "Credits", false));
        add(labelPanel, BorderLayout.NORTH);

        dropButton = new JButton("Drop Selection from Schedule");
        blankLabel = new JLabel("     ", JLabel.CENTER);
        addButton = new JButton("Go to Course Lookup");

        JPanel controls = new JPanel();
        controls.add(dropButton);
        controls.add(blankLabel);
        controls.add(addButton);

        add(controls, BorderLayout.SOUTH);

        classListView = new ClassListView(student.getStudentsCurrentCourseList());
        JScrollPane scrollPane = new JScrollPane(classListView);

        ButtonListener listener = new ButtonListener();
        addButton.addActionListener(listener);
        dropButton.addActionListener(listener);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void add() {
        JFrame frame = new JFrame("Course Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SearchView panel = new SearchView(student);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setSize(800, 500);

        frame.setVisible(true);
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        topFrame.dispose();
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

   public void dropCourses(ArrayList<Course> coursesToDrop) {
        // TODO: change database
        student.getStudentsCurrentCourseList().removeAll(coursesToDrop);
    }

}
