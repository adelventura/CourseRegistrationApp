package com.groupone.middle;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;


public class Student {

	public String username;
	public String password;
    public String firstName;
    public String lastName;
    public String email;
    public static String studentCourseTbl;
    public ArrayList<Course> courses;

    public Student(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        studentCourseTbl = lastName.toLowerCase() + "_table";
        //when a new student is created, he/she is automatically added to student database
        addStudentInfoToStudentsTable();
    }

    public Student(String fN, String lN) {
        this.firstName = fN;
        this.lastName = lN;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }


    /*
     * this method takes the information passed into the class constructor
     * and adds it to the student_list table in the database
     */
    public void addStudentInfoToStudentsTable() {

        if (!studentExists()) {
        	
            String sql = "INSERT INTO `students`.`student_list`(`First_Name`,`Last_Name`, `email`, `username`, `password`)"
                    + " VALUES(?,?,?,?,?)";
            try (Connection conn = Methods.connectToStudentsTable("root", "password");
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, this.firstName);
                pstmt.setString(2, this.lastName);
                pstmt.setString(3, this.email);
                pstmt.setString(4, this.username);
                pstmt.setString(5, this.password);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            //when new student is added to student database
            //automatically creates course list for newly created student
            //course list is initially empty
            createCourseListForStudent();
        } else {
            System.out.println("Student already exists in database.");
        }

    }


    /*
     * method creates course list in database for student with specified fields
     */
    public void createCourseListForStudent() {
        String sqlCode = "CREATE TABLE " + studentCourseTbl + "("
                + "Course_Num int NOT NULL, "
                + "Course_Name varchar(45), "
                + "Days varchar(45), "
                + "Time varchar(45), "
                + "Credits int NOT NULL, "
                + "Instructor varchar(45), "
                + "Room_Numberber int NOT NULL, "
                + "PRIMARY KEY(Course_Name))";

        try (Connection conn = Methods.connectToStudentsTable("root", "password");
             PreparedStatement pstmt = conn.prepareStatement(sqlCode)) {

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	/*
	 * This method receives a course number and a department as arguments and deletes
	 * the specified lines from the database
	 */
	
	public static void deleteCourseFromCourseList(int courseNum, String department) {
		String sqlCode = "DELETE FROM `students`.`"+studentCourseTbl+"` "
				+ "WHERE `Course_Num` = '"+courseNum+"' AND `department` ="
						+ "'"+department+"'";
		try(Connection conn = Methods.connectToStudentsTable("root", "password")){
			PreparedStatement pstmt = conn.prepareStatement(sqlCode);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * This method receives an array list of courses as an argument from a method that
	 * connects to the GUI
	 * The method then calls the deleteCourseFromCourseList for each element in the array list
	 * deleting the courses fromt the student's course list in the database
	 */
	public static void deleteCourses(ArrayList<Course> CoursesToDrop) {
		for(Course course : CoursesToDrop) {
			deleteCourseFromCourseList(course.courseNum, course.department);
		}
	}
	

    /*
     * method adds a selected course to the student's course list
     * @param takes course number and course department
     * it first checks to see that there is no time conflict
     * if there isn't, it adds the course to the array list of the student's courses
     * and also adds the course to the student's course list in the database
     */
    	public void addCourseToExistingCourseList(int courseNum, String department) {
			boolean timeConflict = false;
			boolean preexisting = false;
			//Adds the new course to the existing array list of student's courses
			
			//retrieving all information about specific course
			String sql = "SELECT * FROM `department_tables`.`all_courses` WHERE `Course_Num` = '"+courseNum+"'"
					+ " AND `department` = '"+department+"'";
			String sqlCode = "INSERT INTO `students`.`"+studentCourseTbl+"`("
					+ "Course_Num, "
					+ "Course_Name, "
					+ "Days, "
					+ "Time, "
					+ "Credits,"
					+ "Instructor, "
					+ "Room_Number,"
					+ "department) "
					+ "SELECT Course_Num, Course_Name, Days, Time, Credits, Instructor, Room_Number, department "
					+ "FROM `department_tables`.`all_courses` WHERE `Course_Num` = '"+courseNum+"'"
							+ " AND `department` = '"+department+"'";
			
			try(Connection conn = Methods.connectToStudentsTable("root", "password");
					PreparedStatement pstmt = conn.prepareStatement(sqlCode)) {
					//adding course to courses array list for student
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					//if there isn't a time conflict between class trying to be added and 
					//existing courses, course is added to course list
					rs.next();
					if(!preexistingCourse(rs.getString(2))) {
						if(!timeConflictExists(rs.getString(3), rs.getString(4))) {
							pstmt.executeUpdate();
							rs.previous();
							while(rs.next()) {
								Course course = new Course(
										rs.getString(2),
										rs.getString(8),
										rs.getInt(7),
										rs.getString(4),
										rs.getString(3),
										rs.getInt(1),
										rs.getInt(5),
										rs.getString(6)
								);
								courses.add(course);
								
							}
							JOptionPane.showMessageDialog(null, "Class added successfully.");
						}else {
							timeConflict = true;
						}
					}else {
						preexisting = true;
					}
			} catch(SQLException e) {
				System.out.println("add course to existing: "+e.getMessage());
				if(e.getSQLState().equals("23000") && e.getErrorCode() == 1062) {
					JOptionPane.showMessageDialog(null, "Course already exists.");
				}
			}
			if(preexisting) {
				JOptionPane.showMessageDialog(null, "Course already exists.");
			}
			
			if(timeConflict) {
				JOptionPane.showMessageDialog(null, "Time conflict.");
			}
		
	}
	
	public boolean preexistingCourse(String name) {
		ArrayList<Course> existingCourses = getStudentsCurrentCourseList();
		for(Course course : existingCourses) {
			if(name.equals(course.name)) {
				return true;
			}
		}
		return false;
	}

	

	/*
	 * method checks to see if there is a time conflict between course wishing to be added
	 * and existing courses based on time and day of courses
	 * @param course number and course department
	 */
	public boolean timeConflictExists(String days, String time) {
		ArrayList<Course> existingCourses = getStudentsCurrentCourseList();
		for(Course course : existingCourses) {
			if(days.equals(course.day) && time.equals(course.time)) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * This method checks to see if the email already exists
	 * returns true if the email does already exist, false if it doesn't
	 */
	
	public boolean emailExists() {
		for(Student students : Methods.getAllStudentInfo()) {
			if(this.email.equals(students.email)) {
				return true;
			}
		}return false;
	}
	

    /*
     * this method checks to see if student already exists in database
     */
  public boolean studentExists() {
		for(Student students : Methods.getAllStudentInfo()) {
			if(this.firstName.equals(students.firstName) 
					&& this.lastName.equals(students.lastName)) {
				return true;
			}
		}
		return false;
	}


    /*
     * this method retrieves information from the database about the student's
     * current courses and creates and returns an array list of Course objects
     * with the information
     */
    public ArrayList<Course> getStudentsCurrentCourseList() {
        if (courses != null) {
            return courses;
        }

        courses = new ArrayList<>();

        //selecting all information from student's table
        String sqlCode = "SELECT * FROM `" + studentCourseTbl + "`";
        try (Connection conn = Methods.connectToStudentsTable("root", "password")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlCode);

            //name, department, roomNum, time,  day, courseNum, credits, instructor
            //creating course object for all courses

            while (rs.next()) {
                Course course = new Course(rs.getString(2), rs.getString(8), rs.getInt(7), rs.getString(4),
                        rs.getString(3), rs.getInt(1), rs.getInt(5), rs.getString(6));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("show student current: " + e.getMessage());
        }

        return courses;

    }

    /*
     * this method verifies that the student's course list exists
     */
    public boolean studentCourseListExists() {
        try (Connection conn = Methods.connectToStudentsTable("root", "password")) {
            DatabaseMetaData dbm = conn.getMetaData();
            // check if student course list table is there
            ResultSet tables = dbm.getTables(null, null, studentCourseTbl, null);
            if (tables.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("course list exists: " + e.getMessage());
        }
        return false;
    }
}
