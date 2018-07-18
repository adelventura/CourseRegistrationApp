package com.groupone.gui;
import com.groupone.middle.PasswordSecurity;
import com.groupone.middle.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

public class SignInWindow extends JFrame {

	// variables for database connection 
	
	private static String host = "jdbc:mysql://localhost:3306/students";
	private static String dbUser = "root";
	private static String dbPass = "password";
	private JPanel contentPane;
	
	// variables for text fields
	
	private JPasswordField password;
	private JTextField username;
	private JButton btnSignIn;

	// variables for the inputs of the text fields
	
	private int studentID;
	private String studentPassword;
	
	/**
	 * Main method launches the GUI
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					columnsExist();
					SignInWindow frame = new SignInWindow();
					
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * This method creates the LoginWindow Frame.
	 * It contains all of the GUI components.
	 * It also contains all of the GUI layout.
	 */
	public SignInWindow() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel loginFormPanel = new JPanel();
		contentPane.add(loginFormPanel, BorderLayout.NORTH);
		
		// Setting up the GridBagLayout for the loginFormPanel
		
		GridBagLayout gbl_loginFormPanel = new GridBagLayout();
		
		gbl_loginFormPanel.columnWidths = new int[]{0, 0, 0, 0, 185, 0, 0, 0, 0, 0};
		gbl_loginFormPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_loginFormPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_loginFormPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		loginFormPanel.setLayout(gbl_loginFormPanel);
		
		JLabel lblSignIn = new JLabel("Sign in below.");
		
		// GridBagConstraints for signIn label
		
		GridBagConstraints gbc_lblSignIn = new GridBagConstraints();
		gbc_lblSignIn.gridheight = 2;
		gbc_lblSignIn.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignIn.gridx = 4;
		gbc_lblSignIn.gridy = 0;
		
		loginFormPanel.add(lblSignIn, gbc_lblSignIn);
		lblSignIn.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblUsername = new JLabel("Username (Student ID):");
		
		// GridBagConstraints for username label
		
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.WEST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 4;
		gbc_lblUsername.gridy = 2;
		
		loginFormPanel.add(lblUsername, gbc_lblUsername);
		
		username = new JTextField();
		
		// GridBagConstraints for username text field
		
		GridBagConstraints gbc_username = new GridBagConstraints();
		gbc_username.insets = new Insets(0, 0, 5, 5);
		gbc_username.fill = GridBagConstraints.HORIZONTAL;
		gbc_username.gridx = 4;
		gbc_username.gridy = 3;
		
		loginFormPanel.add(username, gbc_username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		
		// GridBagConstraints for password label
		
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.WEST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 4;
		gbc_lblPassword.gridy = 4;
		
		loginFormPanel.add(lblPassword, gbc_lblPassword);
		
		password = new JPasswordField();
		
		// GridBagConstraints for password text field
		
		GridBagConstraints gbc_password = new GridBagConstraints();
		gbc_password.fill = GridBagConstraints.HORIZONTAL;
		gbc_password.insets = new Insets(0, 0, 5, 5);
		gbc_password.gridx = 4;
		gbc_password.gridy = 5;
		
		/*
		 * Below is an Key Listener for the password field
		 * It allows a user to press the enter key to sign in instead of only clicking the sign in button
		 */
		
		password.addKeyListener(new KeyAdapter()  {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					checkLoginInfo();
				}
			}
		});
		
		loginFormPanel.add(password, gbc_password);
		
		
		btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new myListener());
		
		GridBagConstraints gbc_btnSignIn = new GridBagConstraints();
		gbc_btnSignIn.gridheight = 2;
		gbc_btnSignIn.anchor = GridBagConstraints.EAST;
		gbc_btnSignIn.insets = new Insets(0, 0, 0, 5);
		gbc_btnSignIn.gridx = 4;
		gbc_btnSignIn.gridy = 6;
		
		loginFormPanel.add(btnSignIn, gbc_btnSignIn);
	}
	
	/*
	 *  This class listens for when the Sign In button is clicked
	 *  It then calls doesIdExist and checkLoginInfo
	 */
	
	public class myListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSignIn) {
				checkLoginInfo();
			}
		}		
	}
	
	/* 
	 * This method checks whether the given Student id exists in the database or not
	 */
	private boolean checkUsername(String studentUserName) {
		studentUserName = username.getText();
		boolean doesExist = false;
		
		Connection connectToStudents = null;
		PreparedStatement preparedStm = null;
		
		String lookForStudentUsername = "SELECT * FROM `students`.`student_list` WHERE username = ?";
		
		try {
			connectToStudents = DriverManager.getConnection(host, dbUser, dbPass);
			
			preparedStm = connectToStudents.prepareStatement(lookForStudentUsername);
			preparedStm.setString(1, studentUserName);
			
			ResultSet rs = preparedStm.executeQuery();
			if(rs.absolute(1)) {
				doesExist = true;
			}
			
		} catch (Exception e) {
			doesExist = false;
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return doesExist;
	}
	
	/*
	 *  This method checks if the given password is correct.
	 *  
	 */
	
	private void checkLoginInfo() {
		

		String username = this.username.getText();
		String password = new String(this.password.getPassword());
		if(checkUsername(username)) {
			
			if(PasswordSecurity.checkStudentPassword(username, password)) {
				
				JFrame frame = new JFrame("Course Registration");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				ScheduleView panel = new ScheduleView(prepareScheduleView());
				frame.getContentPane().add(panel);
				frame.pack();
				frame.setSize(900, 500);

				frame.setVisible(true);

				dispose();
			}
			else {
				JOptionPane.showMessageDialog(new JButton("OK"), "Password Incorrect. Please try again.");
			}
		}
		else {
			JOptionPane.showMessageDialog(new JButton("OK"), "Username does not exist.");
		}
	}
	
	// This method retrieves information from the student with the given username in order to construct a student. The student object will be used to initialize the Schedule View.
	
	private Student prepareScheduleView() {
		
		Student student;
		
		String firstName = null;
		String lastName = null;
		String email = null;
		
		String username = this.username.getText();
		String password = new String(this.password.getPassword());
		
		Connection connectToStudents = null;
		PreparedStatement preparedStm = null;
		
		String getThisStudent = "SELECT * FROM `students`.`student_list` WHERE username = ?";
		
		try {
			
			connectToStudents = DriverManager.getConnection(host, dbUser, dbPass);
			
			preparedStm = connectToStudents.prepareStatement(getThisStudent);
			preparedStm.setString(1, username);
			
			ResultSet rs = preparedStm.executeQuery();
			
			if(rs.absolute(1)) {
			
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				email = rs.getString(4);
				
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		student = new Student(firstName ,lastName, email, username, password);
		
		return student;
	}
	
	private static void columnsExist() {
		
		Connection connectToStudents = null;
		PreparedStatement preparedStm = null;
		Statement createUsernameColumn = null;
		Statement createPasswordColumn = null;
		
		String getThisStudent = "SELECT * FROM `students`.`student_list` WHERE username = ?";
		
		try {
			
			connectToStudents = DriverManager.getConnection(host, dbUser, dbPass);
			
			DatabaseMetaData studentListMetadata = connectToStudents.getMetaData();
			
			// Gets 
			
			ResultSet userNameColumn = studentListMetadata.getColumns(null, null, "student_list", "username");
			ResultSet passwordColumn = studentListMetadata.getColumns(null, null, "student_list", "password");
			
			if(userNameColumn.next() && passwordColumn.next()) {
				return;
			}
			
			else {
				
				// creates username column
				
				createUsernameColumn = connectToStudents.createStatement();
				createUsernameColumn.executeUpdate("ALTER TABLE `students`.`student_list` ADD username");
				
				// creates password column
				
				createPasswordColumn = connectToStudents.createStatement();
				createPasswordColumn.executeUpdate("ALTER TABLE `students`.`student_list` ADD password");
				
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
