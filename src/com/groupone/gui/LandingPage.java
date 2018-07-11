package com.groupone.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class LandingPage extends JPanel {

	private JPanel landingPagePanel;
	private JButton btnCreateAccount;
	private JButton btnSignIn;
	/**
	 * This Constructor loads the GUI
	 */
	
	public LandingPage() {
		initialize();
		add(landingPagePanel);
	}

	/**
	 * This method sets up all of the components and layouts for the GUI
	 */
	
	private void initialize() {
		
		landingPagePanel = new JPanel();
		
		JPanel panel = new JPanel();
		landingPagePanel.add(panel, BorderLayout.CENTER);
		
		// Sets up the GridBagLayout for the JPanel
		
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 129, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		btnSignIn = new JButton("Sign In");
		
		JLabel lblCourseRegistration = new JLabel("Course Registration");
		lblCourseRegistration.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		// GridBag Constraints for Course Registration Label
		
		GridBagConstraints gbc_lblCourseRegistration = new GridBagConstraints();
		gbc_lblCourseRegistration.gridheight = 2;
		gbc_lblCourseRegistration.insets = new Insets(0, 0, 5, 5);
		gbc_lblCourseRegistration.gridx = 5;
		gbc_lblCourseRegistration.gridy = 2;
		
		panel.add(lblCourseRegistration, gbc_lblCourseRegistration);
		
		// GridBag Constraints for the Sign In button
		
		GridBagConstraints gbc_btnSignIn = new GridBagConstraints();
		gbc_btnSignIn.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSignIn.insets = new Insets(0, 0, 5, 5);
		gbc_btnSignIn.gridx = 5;
		gbc_btnSignIn.gridy = 4;
		
		panel.add(btnSignIn, gbc_btnSignIn);
		
		btnCreateAccount = new JButton("Create Account");
		
		// GridBag Constraints for the Create Account button
		
		GridBagConstraints gbc_btnCreateAccount = new GridBagConstraints();
		gbc_btnCreateAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateAccount.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateAccount.gridx = 5;
		gbc_btnCreateAccount.gridy = 5;
		
		panel.add(btnCreateAccount, gbc_btnCreateAccount);
	}
	
	/*
	 *  Action Listener that listens for when either the Sign In or Create Account buttons are clicked
	 */
	
	public class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();
			if(source == "btnSignIn") {
				// Opens the Sign In window
				SignInWindow openSignInWindow = new SignInWindow();
				openSignInWindow.setVisible(true);
			}
			else if(source == "btnCreateAccount") {
				// TODO: create a new window to create an account
			}
		}
		
	}
}
