//THESE ARE METHODS THAT ARE CALLED IN THE STUDENT CLASS

import java.sql.*;

public class Methods {
	
	public static Connection connectToStudentsTable(String user, String pass) {
		//connection string
		String url = "jdbc:mysql://localhost:3306/students";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user, pass);
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return conn;
	}
	
	public static Connection connectToDeptTable(String user, String pass) {
		//connection string
		String url = "jdbc:mysql://localhost:3306/department_tables";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,user, pass);
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return conn;
	}
	
	public static void selectAllCoursesFromASpecificDept(String department) {
		
		if(deptExists(department)) {
			String sqlCode = "SELECT * FROM `department_tables`.`all_courses` WHERE `department` = '"+department+"'";
			
			try(Connection conn = connectToDeptTable("root", "password")){
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sqlCode);
				ResultSetMetaData rsmd = rs.getMetaData(); 
				int colCount = rsmd.getColumnCount();  

				while(rs.next()) {
					for (int i = 1; i <= colCount ; i++){  
						System.out.println(rsmd.getColumnName(i)+": "+rs.getString(i));  
					}
				}
				
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Department is not listed in Database. Please enter a department that exists.");
		}
		
		
		
	}
	
	public static boolean deptExists(String department) {
		//checking to see if dept exists
		String[] existingDepts = new String[]{"Chemistry","Physics","Math", "Computer Science"};
		
		for(int i =0; i<existingDepts.length; i++) {
			if(existingDepts[i].equals(department)) {
				return true;
			}
		}
		return false;
	}
	
