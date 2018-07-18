package com.groupone.gui;


import com.groupone.middle.Student;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;


public class CreateAccountWindow extends JFrame {
	private String host = "jdbc:mysql://localhost:3306/students";
	private String dbUser = "root";
	private String dbPass = "password";
	private JPanel contentPane;
	
	private JTextField FirstName;
	private JTextField LastName;
	private JPasswordField password;  
	private JPasswordField ComfPassword;
	private JTextField username;
	private JButton btnCreateAccount;
	private JTextField Email;
	private JTextField ComfEmail;
	
	private String StudentFirstName;
	private String StudentLastName;
	private int studentID;
	private String StudentPassword;
	private String StudentComfPassword;
	private String StudentEmail;
	private String StudentComfEmail;
	
//Gui layout
public CreateAccountWindow() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 400);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	
	JPanel CreateAccountPanel = new JPanel();
	contentPane.add(CreateAccountPanel, BorderLayout.NORTH);
	
	
	GridBagLayout gbl_CreateAccountFormPanel = new GridBagLayout();
	
	gbl_CreateAccountFormPanel.columnWidths = new int[]{0, 0, 0, 0, 185, 0, 0, 0, 0, 0};
	gbl_CreateAccountFormPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
	gbl_CreateAccountFormPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	gbl_CreateAccountFormPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
	CreateAccountPanel.setLayout(gbl_CreateAccountFormPanel);
	
	JLabel lblCreateAccount = new JLabel("Enter account information below.");
	
	GridBagConstraints gbc_CreateAccount = new GridBagConstraints();
	gbc_CreateAccount.gridheight = 2;
	gbc_CreateAccount.insets = new Insets(0, 0, 5, 5);
	gbc_CreateAccount.gridx = 4;
	gbc_CreateAccount.gridy = 0;
	
	
	CreateAccountPanel.add(lblCreateAccount, gbc_CreateAccount);
	lblCreateAccount.setHorizontalAlignment(SwingConstants.CENTER);
	
	//First Name field
	JLabel lblFirstName = new JLabel("First Name");
	
	GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
	gbc_lblFirstName.anchor = GridBagConstraints.WEST;
	gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
	gbc_lblFirstName.gridx = 4;
	gbc_lblFirstName.gridy = 2;
	
	CreateAccountPanel.add(lblFirstName, gbc_lblFirstName);
	
	FirstName = new JTextField();
	
	GridBagConstraints gbc_FirstName = new GridBagConstraints();
	gbc_FirstName.insets = new Insets(0, 0, 5, 5);
	gbc_FirstName.fill = GridBagConstraints.HORIZONTAL;
	gbc_FirstName.gridx = 4;
	gbc_FirstName.gridy = 3;
	
	CreateAccountPanel.add(FirstName, gbc_FirstName);
	FirstName.setColumns(10);
	
	//Last Name field
	JLabel lblLastName = new JLabel("Last Name");
	
	GridBagConstraints gbc_lblLastName = new GridBagConstraints();
	gbc_lblLastName.anchor = GridBagConstraints.WEST;
	gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
	gbc_lblLastName.gridx = 4;
	gbc_lblLastName.gridy = 4;
	
	CreateAccountPanel.add(lblLastName, gbc_lblLastName);
	
	LastName = new JTextField();
	
	
	GridBagConstraints gbc_LastName = new GridBagConstraints();
	gbc_LastName.insets = new Insets(0, 0, 5, 5);
	gbc_LastName.fill = GridBagConstraints.HORIZONTAL;
	gbc_LastName.gridx = 4;
	gbc_LastName.gridy = 5;
	
	CreateAccountPanel.add(LastName, gbc_LastName);
	LastName.setColumns(10);
	
	//Password Field
	JLabel lblPassword = new JLabel("Password:");
	
	GridBagConstraints gbc_lblPassword = new GridBagConstraints();
	gbc_lblPassword.anchor = GridBagConstraints.WEST;
	gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
	gbc_lblPassword.gridx = 4;
	gbc_lblPassword.gridy = 6;
	
	CreateAccountPanel.add(lblPassword, gbc_lblPassword);
	
	password = new JPasswordField();
	
	GridBagConstraints gbc_password = new GridBagConstraints();
	gbc_password.fill = GridBagConstraints.HORIZONTAL;
	gbc_password.insets = new Insets(0, 0, 5, 5);
	gbc_password.gridx = 4;
	gbc_password.gridy = 7;
	
	CreateAccountPanel.add(password, gbc_password);
	
	//Confirm Password field
	JLabel lblComfPassword = new JLabel("Confirm Password:");
	
	GridBagConstraints gbc_lblComfPassword = new GridBagConstraints();
	gbc_lblComfPassword.anchor = GridBagConstraints.WEST;
	gbc_lblComfPassword.insets = new Insets(0, 0, 5, 5);
	gbc_lblComfPassword.gridx = 4;
	gbc_lblComfPassword.gridy = 8;
	
	CreateAccountPanel.add(lblComfPassword, gbc_lblComfPassword);
	
	ComfPassword = new JPasswordField();
	
	
	GridBagConstraints gbc_Comfpassword = new GridBagConstraints();
	gbc_Comfpassword.fill = GridBagConstraints.HORIZONTAL;
	gbc_Comfpassword.insets = new Insets(0, 0, 5, 5);
	gbc_Comfpassword.gridx = 4;
	gbc_Comfpassword.gridy = 9;
	
	CreateAccountPanel.add(ComfPassword, gbc_Comfpassword);
	
	//Email field	
	JLabel lblEmail = new JLabel("Enter E-mail Address");
	
	GridBagConstraints gbc_lblEmail = new GridBagConstraints();
	 gbc_lblEmail.anchor = GridBagConstraints.WEST;
	 gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
	 gbc_lblEmail.gridx = 4;
	 gbc_lblEmail.gridy = 10;
	
	CreateAccountPanel.add(lblEmail,  gbc_lblEmail);
	
	Email = new  JTextField();
	
	
	GridBagConstraints gbc_Email = new GridBagConstraints();
	gbc_Email.fill = GridBagConstraints.HORIZONTAL;
	gbc_Email.insets = new Insets(0, 0, 5, 5);
	gbc_Email.gridx = 4;
	gbc_Email.gridy = 11;
	
	CreateAccountPanel.add(Email, gbc_Email);
	
	//Confirm email field
	JLabel lblComfEmail = new JLabel("Confirm E-mail Address");
	
	GridBagConstraints gbc_lblComfEmail = new GridBagConstraints();
	 gbc_lblComfEmail.anchor = GridBagConstraints.WEST;
	 gbc_lblComfEmail.insets = new Insets(0, 0, 5, 5);
	 gbc_lblComfEmail.gridx = 4;
	 gbc_lblComfEmail.gridy = 12;
	
	CreateAccountPanel.add(lblComfEmail,  gbc_lblComfEmail);
	
	ComfEmail = new  JTextField();
	
	
	GridBagConstraints gbc_ComfEmail = new GridBagConstraints();
	gbc_ComfEmail.fill = GridBagConstraints.HORIZONTAL;
	gbc_ComfEmail.insets = new Insets(0, 0, 5, 5);
	gbc_ComfEmail.gridx = 4;
	gbc_ComfEmail.gridy = 13;
	
	CreateAccountPanel.add(ComfEmail, gbc_ComfEmail);
	
	
	btnCreateAccount = new JButton("Create Account");
	btnCreateAccount.addActionListener(new ButtonClickListener());
	
	GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
	gbc_btnCreateAccount.gridheight = 2;
	gbc_btnCreateAccount.anchor = GridBagConstraints.EAST;
	gbc_btnCreateAccount.insets = new Insets(0, 0, 0, 5);
	gbc_btnCreateAccount.gridx = 4;
	gbc_btnCreateAccount.gridy = 14;
	
	CreateAccountPanel.add(btnCreateAccount, gbc_btnCreateAccount);
	
	
}



public class ButtonClickListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnCreateAccount) {
			if(CheckAccountInfo()) {
				CreateAccount();
				}
			}
			
		}
	}
	// Checks to see if data is entered fully, checks if password and email match.
	// Returns true only all data is correct.
	private boolean CheckAccountInfo() {
		String StudentFirstName = this.FirstName.getText();
		String StudentLastName = this.LastName.getText();
		String StudentPassword = new String(this.password.getPassword());
		String StudentComfPassword = new String(this.ComfPassword.getPassword());
		String StudentEmail = this.Email.getText();
		String StudentComfEmail =  this.ComfEmail.getText();
		
		
		if (StudentFirstName.isEmpty() || StudentLastName.isEmpty() || StudentPassword.isEmpty() 
			|| StudentEmail.isEmpty() || StudentComfEmail.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill in all forms.");
			return false;
		}
		
		if(!(StudentPassword.equals(StudentComfPassword))) {
			JOptionPane.showMessageDialog(null, "Passwords do not match.");
			return false;
		}
		if(!(StudentEmail.equalsIgnoreCase(StudentComfEmail))) {
			JOptionPane.showMessageDialog(null, "Emails do not mathch.");
					return false;
				}
			
		
		return true;
		
	}
	
	private void CreateAccount() {
		String StudentFirstName = this.FirstName.getText();
		String StudentLastName = this.LastName.getText();
		String StudentPassword = new String(this.password.getPassword());
		String StudentEmail = this.Email.getText();
	
		Student student = new Student(StudentFirstName, StudentLastName, StudentEmail);
		if(student.emailExists()) {
			JOptionPane.showMessageDialog(null, "Emails already exist.");
		}
		//TODO: Wait for student class to take password.
		
		
	}


<<<<<<< HEAD
}
=======
}
>>>>>>> branch 'master' of https://github.com/adelventura/CourseRegistrationApp
