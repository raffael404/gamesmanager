package com.godgames.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.godgames.dao.Database;
import com.godgames.util.ErrorMessage;
import com.godgames.util.FormatConverter;
import com.godgames.util.Label;
import javax.swing.SwingConstants;

public class LoginWindow {

	private JFrame frmLogin;
	private JTextField textFieldUser;
	private JPasswordField passwordField;
	
	private Database database;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					LoginWindow window = new LoginWindow();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle(Label.TITLE_WINDOW_LOGIN);
		frmLogin.setResizable(false);
		frmLogin.setBounds(100, 100, 305, 120);
		frmLogin.setLocationRelativeTo(null);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblUser = new JLabel(Label.USER);
		lblUser.setBounds(12, 17, 37, 16);
		frmLogin.getContentPane().add(lblUser);
		
		textFieldUser = new JTextField();
		textFieldUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setLabelFor(textFieldUser);
		textFieldUser.setBounds(59, 14, 99, 22);
		frmLogin.getContentPane().add(textFieldUser);
		textFieldUser.setColumns(10);
		
		JLabel lblPassword = new JLabel(Label.PASSWORD);
		lblPassword.setBounds(12, 49, 30, 16);
		frmLogin.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(59, 46, 99, 22);
		frmLogin.getContentPane().add(passwordField);
		
		JButton btnLogin = new JButton(Label.LOGIN);
		btnLogin.setBounds(168, 13, 107, 25);
		frmLogin.getContentPane().add(btnLogin);
		
		JCheckBox chckbxSaveCredentials = new JCheckBox(Label.SAVE_CREDENTIALS);
		chckbxSaveCredentials.setBounds(164, 46, 118, 22);
		frmLogin.getContentPane().add(chckbxSaveCredentials);
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String user = (String) textFieldUser.getText();
					String password = new String(passwordField.getPassword());
					database = new Database(Label.SERVER_NAME, Label.DATABASE_NAME, user, password);
					if (chckbxSaveCredentials.isSelected()) {
						try {
							Files.write(Paths.get(Label.CREDENTIALS_FILE_NAME), (user + "\n" + FormatConverter.encrypt(password, Label.ENCRYPTION_KEY)).getBytes());
						} catch (Exception e1) {
							ErrorMessage.showErrorMessage(frmLogin, Label.ERROR_SAVING_CREDENTIALS, e1);
						}
					}
					MainWindow mainWindow = new MainWindow(database);
					mainWindow.getFrame().setVisible(true);
					frmLogin.dispose();
				} catch (ClassNotFoundException e1) {
					ErrorMessage.showErrorMessage(frmLogin, Label.ERROR_DRIVER_NOT_FOUND, e1);
				} catch (SQLException e1) {
					ErrorMessage.showErrorMessage(frmLogin, Label.ERROR_DATABASE_CONNECTION, e1);
				}
			}
		});
		
		try {
			String[] credentials = new String(Files.readAllBytes(Paths.get(Label.CREDENTIALS_FILE_NAME))).split("\n");
			textFieldUser.setText(credentials[0]);
			passwordField.setText(FormatConverter.decrypt(credentials[1], Label.ENCRYPTION_KEY));
		} catch (NoSuchFileException e) {
			System.out.println("O arquivo de credenciais não foi criado!");
		} catch (Exception e1) {
			ErrorMessage.showErrorMessage(frmLogin, Label.ERROR_LOADING_CREDENTIALS, e1);
		}
		
	}
}
