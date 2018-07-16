package com.groupone.middle;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Methods {

    public static String[] existingDepts = new String[]{"Chemistry", "Physics", "Math", "Computer Science"};

    /*
     * this method is used for connecting to either the table of students
     * and each student's individual course list table in the database
     */

    public static Connection connectToStudentsTable(String user, String pass) {
        //connection string
        String url = "jdbc:mysql://localhost:3306/students";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return conn;
    }

    /*
     * this method connects to the database department table
     */

    public static Connection connectToDeptTable(String user, String pass) {
        //connection string
        String url = "jdbc:mysql://localhost:3306/students";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<Student> getAllStudentInfo() {
        ArrayList<Student> students = new ArrayList<Student>();
        String sqlCode = "SELECT First_Name, Last_Name FROM `students`.`student_list`";
        try (Connection conn = connectToStudentsTable("root", "password")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCode);

            while (rs.next()) {
                Student stdnt = new Student(rs.getString(1), rs.getString(2));
                students.add(stdnt);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return students;
    }

    /*
     * this method returns an array list of course objects, these course objects
     * hold the coures information of all courses of a specified department
     */
    public static ArrayList<Course> selectAllCoursesFromASpecificDept(String department) {
        ArrayList<Course> allDeptCourses = new ArrayList<Course>();
        if (deptExists(department)) {
            String sqlCode = "SELECT * FROM `all_courses` WHERE `department` = '" + department + "'";
            try (Connection conn = connectToDeptTable("root", "password")) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sqlCode);
                while (rs.next()) {
                    Course course = new Course(rs.getString(2), rs.getString(8), rs.getInt(7), rs.getString(4),
                            rs.getString(3), rs.getInt(1), rs.getInt(5), rs.getString(6));
                    allDeptCourses.add(course);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Department is not listed in Database. Please enter a department that exists.");
            return null;
        }

        return allDeptCourses;


    }

    /*
     * this method verifies that the department passed through exists
     */
    public static boolean deptExists(String department) {
        //checking to see if dept exists
        for (int i = 0; i < existingDepts.length; i++) {
            if (existingDepts[i].equals(department)) {
                return true;
            }
        }
        return false;
    }


}
