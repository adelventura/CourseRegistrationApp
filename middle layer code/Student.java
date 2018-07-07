import java.sql.*;
import java.util.ArrayList;


public class Student {
	
	public String username;
	public String password;
	public String firstName;
	public String lastName;
	public String email;
	public String studentCourseTbl;
	public ArrayList<Course> courses = new ArrayList<Course>();
	
	public Student(String userName, String pass, String fN, String lN, String Email) {
		this.username = userName;
		this.password = pass;
		this.firstName = fN;
		this.lastName = lN;
		this.email=Email;
		this.studentCourseTbl = lastName.toLowerCase()+"_table";
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
		
		if(!studentExists()) {
			String sql = "INSERT INTO `students`.`student_list`(`First_Name`,`Last_Name`, `email`)"
						+ " VALUES(?,?,?)";
			try(Connection conn = Methods.connectToStudentsTable("root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setString(1, this.firstName);
					pstmt.setString(2, this.lastName);
					pstmt.setString(3, this.email);
					pstmt.executeUpdate();
						
				} catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			//when new student is added to student database
			//automatically creates course list for newly created student
			//course list is initially empty
			createCourseListForStudent();
		}else {
			System.out.println("Student already exists in database.");
		}
	
	}
	
	
	/*
	 * method creates course list in database for student with specified fields
	 */
	public void createCourseListForStudent() {
		String sqlCode = "CREATE TABLE "+studentCourseTbl+"("
				+ "Course_Num int NOT NULL, "
				+ "Course_Name varchar(45), "
				+ "Days varchar(45), "
				+ "Time varchar(45), "
				+ "Credits int NOT NULL, "
				+ "Instructor varchar(45), "
				+ "Room_Numberber int NOT NULL, "
				+ "PRIMARY KEY(Course_Name))";
		
		try(Connection conn = Methods.connectToStudentsTable("root", "password");
				PreparedStatement pstmt = conn.prepareStatement(sqlCode)) {
				
				pstmt.executeUpdate();
						
		} catch(SQLException e) {
			System.out.println(e.getMessage());
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
		
		//if the student's course list exists and there is no time conflict between
		//course wishing to be added and existing courses, adds course to course list
		if(!timeConflictExists(courseNum, department)) {
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
					+ "SELECT Course_Num, Course_Name, Days, Time, Credits, Instructor, Room_Number "
					+ "FROM `department_tables`.`all_courses` WHERE `Course_Num` = '"+courseNum+"'"
							+ " AND `department` = '"+department+"'";
			
			try(Connection conn = Methods.connectToStudentsTable("root", "password");
					PreparedStatement pstmt = conn.prepareStatement(sqlCode)) {
					pstmt.executeUpdate();
					
					//adding course to courses array list for student
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(sql);
					
					while(rs.next()) {
						Course course = new Course(rs.getString(2), rs.getString(8), rs.getInt(7), rs.getString(4),
								rs.getString(3), rs.getInt(1), rs.getInt(5), rs.getString(6));
						courses.add(course);
						
					}
			} catch(SQLException e) {
				System.out.println("add course to existing: "+e.getMessage());
			}
		}else {
			System.out.println("Time conflict.");
		}
	
	}
	
	
	/*
	 * method checks to see if there is a time conflict between course wishing to be added
	 * and existing courses based on time and day of courses
	 * @param course number and course department
	 */
	public boolean timeConflictExists(int courseNum, String department) {
		
		String daysOfAdded = "";
		String timeOfAdded = "";
		
		ArrayList<Course> current = getStudentsCurrentCourseList();
		String[] currentDays = new String[current.size()];
		String[] currentTimes = new String[current.size()];
		//putting all days of current courses into an array to be used to compare
		for(int i=0; i<current.size(); i++) {
			currentDays[i] = current.get(i).getDay();
		}
		//purring all times of current courses into an array to be used to compare
		for(int i=0; i<current.size(); i++) {
			currentTimes[i] = current.get(i).getTime();
		}
		
		//getting day & time of course wishing to be added
		String sqlCode = "SELECT `Days`, `Time` FROM `department_tables`.`all_courses` WHERE "
				+ "`Course_Num` = '"+courseNum+"' AND `department` = '"+department+"'";
		try(Connection conn = Methods.connectToDeptTable("root", "password")){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlCode);
			daysOfAdded = rs.getString(1);
			timeOfAdded = rs.getString(2);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		//checking times & days to see if there are any conflicts
		for(int j=0; j<current.size(); j++) {
			if(currentDays[j].equals(daysOfAdded)&&currentTimes[j].equals(timeOfAdded)) {
					return true; //time conflict exists
			}
		}
		
		return false;

	}
	
	/*
	 * this method checks to see if student already exists in database
	 */
	public boolean studentExists() {
		ArrayList<Student> students = Methods.getAllStudentInfo();
		for(int i=0; i<students.size();i++) {
			if(this.firstName.equals(students.get(i).getFirstName()) 
					&& this.lastName.equals(students.get(i).getLastName())) {
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
	
			//selecting all information from student's table
			String sqlCode = "SELECT * FROM `"+studentCourseTbl+"`";
			try(Connection conn = Methods.connectToStudentsTable("root", "password")){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCode);
				
				//name, department, roomNum, time,  day, courseNum, credits, instructor
				//creating course object for all courses
				
				while(rs.next()) {
					Course course = new Course(rs.getString(2), rs.getString(8), rs.getInt(7), rs.getString(4),
							rs.getString(3), rs.getInt(1), rs.getInt(5), rs.getString(6));
					courses.add(course);
				}
			}catch(SQLException e) {
				System.out.println("show student current: "+e.getMessage());
			}
			
			return courses;
		
	}
	
	/*
	 * this method verifies that the student's course list exists
	 */
	public boolean studentCourseListExists() {
		try(Connection conn = Methods.connectToStudentsTable("root", "password")){
			DatabaseMetaData dbm = conn.getMetaData();
			// check if student course list table is there
			ResultSet tables = dbm.getTables(null, null, studentCourseTbl, null);
			if (tables.next()) {
				return true;
			}
		}catch(SQLException e) {
			System.out.println("course list exists: "+e.getMessage());
		}
		return false;
		
	}
	
	


}
