import java.sql.*;


public class Student extends AllStudents{
	
	public static String username;
	public static String password;
	public static String firstName;
	public static String lastName;
	public static int studentID;
	public static String email;
	public static String studentCourseTbl;
	
	public Student(String userName, String pass, String fN, String lN, String Email) {
		username = userName;
		password = pass;
		firstName = fN;
		lastName = lN;
		email=Email;
		studentID = super.getID(); 
		studentCourseTbl = lastName.toLowerCase()+"_table";
		//when a new student is created, he/she is automatically added to student database
		//addStudentInfoToStudentsTable(); 
	}
	
	public static void addStudentInfoToStudentsTable() {
		
		String sql = "INSERT INTO `students`.`student_list`(`First_Name`,`Last_Name`, `email`)"
					+ " VALUES(?,?,?)";
		try(Connection conn = Methods.connectToStudentsTable("root", "password");
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
					
				//pstmt.setInt(1, this.studentID);
				pstmt.setString(1, firstName);
				pstmt.setString(2, lastName);
				pstmt.setString(3, email);
				pstmt.executeUpdate();
					
			} catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		
		//when new student is added to student database
		//automatically creates course list for newly created student
		//course list is initially empty
		//createCourseListForStudent();
	
	}
	
	/*
	 * method creates course list for student with specified fields
	 */
	public static void createCourseListForStudent() {
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
	 */
	public static void addCourseToExistingCourseList(int courseNum, String department) {
		
		//if the student's course list exists and there is no time conflict between
		//course wishing to be added and existing courses, adds course to course list

			String sqlCode = "INSERT INTO `students`.`"+studentCourseTbl+"`("
					+ "Course_Num, "
					+ "Course_Name, "
					+ "Days, "
					+ "Time, "
					+ "Credits,"
					+ "Instructor, "
					+ "Room_Number) "
					+ "SELECT Course_Num, Course_Name, Days, Time, Credits, Instructor, Room_Number "
					+ "FROM `department_tables`.`all_courses` WHERE `Course_Num` = '"+courseNum+"'"
							+ " AND `department` = '"+department+"'";
			
			try(Connection conn = Methods.connectToStudentsTable("root", "password");
					PreparedStatement pstmt = conn.prepareStatement(sqlCode)) {
					pstmt.executeUpdate();
			} catch(SQLException e) {
				System.out.println("add course to existing: "+e.getMessage());
			}
	
	}
	
	

	
	
	//method prints out student's course list
	public static void showStudentsCurrentCourseList() {
		
		//check that student table exists
		if(studentCourseListExists()) {
			//selecting all information from student's table
			String sqlCode = "SELECT * FROM `"+studentCourseTbl+"`";
			try(Connection conn = Methods.connectToStudentsTable("root", "password")){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCode);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				
				while(rs.next()) {
					//printing out schedule information
					for(int i =1; i<columnCount; i++) {
						System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));
					}
				}
			}catch(SQLException e) {
				System.out.println("show student current: "+e.getMessage());
			}
			
		}else {
			System.out.println("This student does not have a course list.");
		}
		
	}
	
	//method verifying if student has a course list
	//returns true if course list exists, false if it doesn't
	public static boolean studentCourseListExists() {
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
