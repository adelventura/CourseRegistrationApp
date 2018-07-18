package com.groupone.middle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
	
public class PasswordSecurity {

	private static String host = "jdbc:mysql://localhost:3306/students";
	private static String dbUser = "root";
	private static String dbPass = "password";
	
	// This method creates a hash for the given password
	
	private static String generateHash(String password) throws NoSuchAlgorithmException, NoSuchProviderException {
		
		String hashedPassword = toMD5Hash(password);
		return hashedPassword;
		
	}
	
	// This method converts the given password into a MD5 Hash
	
	private static String toMD5Hash(String userPassword) {
		
		String myHash = null;
		
		try {
			
			MessageDigest passwordDigest = MessageDigest.getInstance("MD5");
			passwordDigest.update(userPassword.getBytes());
			
			byte[] digestBytes = passwordDigest.digest();
			
			StringBuilder stringBuilder = new StringBuilder();
			
			// converts bytes to hexademical
			
			for(int i=0; i<digestBytes.length; i++) {
				stringBuilder.append(Integer.toString((digestBytes[i] & 0xff) + 0x100,16).substring(1));
			}
			
			myHash = stringBuilder.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return myHash;
	}
	
	// This method adds the generated hash to the student in the database. It also checks if the hash column exists in the database and creates the column if it does not.
	
	public static void addHashToStudent(String username, String password) {
		
		Connection connectToDB;
		PreparedStatement preparedStatement = null;
		Statement createHashColumn = null;
		
		String sql = "UPDATE `students`.`student_list` SET hash = ? WHERE username = ?";
		
		try {
			connectToDB = Methods.connectToStudentsTable(dbUser, dbPass);
			
			DatabaseMetaData studentListMetadata = connectToDB.getMetaData();
			ResultSet metadata = studentListMetadata.getColumns(null, null, "studet_list", "hash");
			
			if(metadata.next()) {
				preparedStatement = connectToDB.prepareStatement(sql);
				preparedStatement.setString(1, generateHash(password));
				preparedStatement.setString(2, username);
				preparedStatement.executeUpdate();
			}
			else {
				
				// adds hash column to student_list table
				
				String updateColumn = "ALTER TABLE `students`.`student_list` ADD hash";
				createHashColumn = connectToDB.createStatement();
				createHashColumn.executeUpdate(updateColumn);
				
				// adds hash after creating hash column
		
				preparedStatement = connectToDB.prepareStatement(sql);
				preparedStatement.setString(1, generateHash(password));
				preparedStatement.setString(2, username);
				preparedStatement.executeUpdate();
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	/*
	 *  This method compares the hash value in the database and the hash value of the entered password.
	 *  If they are the same then, the user can sign in
	 */

	public static boolean checkStudentPassword(String username, String password) {
		
		boolean passCheck = false;
		
		// Hash of the password in the database
		String hashInDatabase = getHashInDatabase(username, password);
		
		try {
			
			// Hash of the given password
			String checkThisHash = generateHash(password);
			
			if (!(hashInDatabase.equals(checkThisHash))) {
				
				passCheck = false;
				JOptionPane.showMessageDialog(new JButton("OK"),"Password Incorrect. Please try again.");

			}
			else {
				passCheck = true;
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return passCheck;
	}
	
	/*
	 * 	This method retrieves the hash of a student in the database
	 */
	
	private static String getHashInDatabase(String username, String password) {
		
		String hashInDatabase = null;
		Connection connectToDB = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "SELECT `hash` FROM `students`.`student_list` WHERE username = ?";
		try {
			connectToDB = DriverManager.getConnection(host,dbUser, dbPass);
			
			preparedStatement = connectToDB.prepareStatement(sql);
			preparedStatement.setString(1, username);
			
			ResultSet rs = preparedStatement.executeQuery();

			if(rs.next()) {
				hashInDatabase = rs.getString(1);
			}
		
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return hashInDatabase;
	}
	
}
	