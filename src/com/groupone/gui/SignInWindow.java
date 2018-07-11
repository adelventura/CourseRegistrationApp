package com.groupone.gui;
import com.groupone.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.*;
import java.awt.event.*;

public class SignInWindow extends JFrame {

	// variables for database connection 
	
	private String host = "jdbc:mysql://localhost:3306/students";
	private String dbUser = "root";
	private String dbPass = "password";
	private JPanel contentPane;
	
	// variables for text fields
	
	private JPasswordField password;
	private JTextField username;

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
					SignInWindow frame = new SignInWindow();
					
					frame.setVisible(true);
			
					/* This if-else statement is for when checkLoginInfo method is complete.
					 * It disposes the login frame if the username/password is correct,
					 * and it shows a dialog box saying incorrect username/password if it is incorrect.
					 */
					
					if(frame.checkLoginInfo()) {
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(new JButton("OK"), "Incorrect Password");
					}
					
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
		setBounds(100, 100, 450, 300);
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
		
		loginFormPanel.add(password, gbc_password);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ButtonClickListener());
		
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
	
	public class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()=="btnSignIn") {
				studentID = Integer.parseInt(username.getText());
				doesIdExist(studentID);
				checkLoginInfo();
			}
		}
		
	}
	
	/* 
	 * This method checks whether the given Student id exists in the database or not
	 */
	private boolean doesIdExist(int id) {
		
		boolean doesExist = false;
		
		Connection connectToStudents = null;
		PreparedStatement preparedStm = null;
		
		String lookForStudentId = "SELECT [student id] FROM students WHERE [sudent id] = ?";
		
		try {
			
			connectToStudents = DriverManager.getConnection(host, dbUser, dbPass);
			
			preparedStm = connectToStudents.prepareStatement(lookForStudentId);
			preparedStm.setInt(1, id);
			
			ResultSet rs = preparedStm.executeQuery();
			
			doesExist = true;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return doesExist;
	}
	
	/*
	 *  This method checks if the given password is correct.
	 *  It calls doesIdExist in a if-statement to make sure that the student exists in the database.
	 */
	
	private boolean checkLoginInfo() {
		
		boolean loginInfo = false;
		Connection connectToStudents = null;
		PreparedStatement preparedStm = null;
		
		studentID = Integer.parseInt(username.getText());
		studentPassword = new String(password.getPassword());
		
		if(doesIdExist(studentID)) {
			try {
				
				// TODO: Query database for the student's password to compare it with the given password
				
				loginInfo = true;
				
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			
		}
		return loginInfo;
	}
}
