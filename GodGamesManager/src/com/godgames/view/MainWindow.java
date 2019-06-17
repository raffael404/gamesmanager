package com.godgames.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.godgames.dao.Database;
import com.godgames.model.Register;
import com.godgames.util.ErrorMessage;
import com.godgames.util.FormatConverter;
import com.godgames.util.Label;

public class MainWindow {

	private JFrame frmMain;
	
	private JTable tableRegisters;
	private JTable tableTotal;
	private DefaultTableModel tableModelRegisters = new DefaultTableModel();
	private DefaultTableModel tableModelTotal = new DefaultTableModel();
	
	private Database database;
	private JTextField textFieldUser;
	private JTextField textFieldHourPrice;
	private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public MainWindow(Database database) {
		this.database = database;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle(Label.PROGRAM_NAME);
		frmMain.setBounds(100, 100, 700, 460);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmMain.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelRegisters = new JPanel();
		tabbedPane.addTab(Label.TITLE_TAB_REGISTERS, null, panelRegisters, null);
		
		JButton btnAdd = new JButton(Label.ADD);
		JButton btnEdit = new JButton(Label.EDIT);
		JButton btnRemove = new JButton(Label.REMOVE);
		
		JScrollPane scrollPaneRegisters = new JScrollPane();
		
		JScrollPane scrollPaneTotal = new JScrollPane();
		scrollPaneTotal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneTotal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		tableModelRegisters.addColumn(Label.DATE);
		tableModelRegisters.addColumn(Label.START_HOUR);
		tableModelRegisters.addColumn(Label.FINAL_HOUR);
		tableModelRegisters.addColumn(Label.TV);
		tableModelRegisters.addColumn(Label.PAID);
		tableModelRegisters.addColumn(Label.PRICE);
		
		tableRegisters = new JTable(tableModelRegisters);
		tableRegisters.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		tableRegisters.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(4).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(5).setPreferredWidth(30);
		
		scrollPaneRegisters.setViewportView(tableRegisters);
		
		tableModelTotal.addColumn(Label.DAY_TOTAL);
		tableModelTotal.addColumn(Label.MONTH_TOTAL);
		tableModelTotal.addColumn(Label.YEAR_TOTAL);
		tableModelTotal.addColumn(Label.SEARCH_TOTAL);
		
		tableTotal = new JTable(tableModelTotal);
		tableTotal.setEnabled(false);
		tableTotal.setRowSelectionAllowed(false);
		tableTotal.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		scrollPaneTotal.setViewportView(tableTotal);
		
		JButton btnToday = new JButton(Label.TODAY);
		
		JButton btnMonth = new JButton(Label.MONTH);
		
		JButton btnYear = new JButton(Label.YEAR);
		
		JButton btnDate = new JButton(Label.DATE);
		GroupLayout gl_panelRegisters = new GroupLayout(panelRegisters);
		gl_panelRegisters.setHorizontalGroup(
			gl_panelRegisters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegisters.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panelRegisters.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneTotal, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panelRegisters.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnToday, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnMonth, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnYear, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDate, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(scrollPaneRegisters, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panelRegisters.setVerticalGroup(
			gl_panelRegisters.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegisters.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelRegisters.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemove)
						.addComponent(btnEdit)
						.addComponent(btnAdd)
						.addComponent(btnToday)
						.addComponent(btnMonth)
						.addComponent(btnYear)
						.addComponent(btnDate))
					.addGap(11)
					.addComponent(scrollPaneRegisters, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
					.addGap(29)
					.addComponent(scrollPaneTotal, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelRegisters.setLayout(gl_panelRegisters);
		
		tableTotal.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableTotal.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableTotal.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		JPanel panelSettings = new JPanel();
		tabbedPane.addTab(Label.TITLE_TAB_SETTINGS, null, panelSettings, null);
		
		JPanel panelPrice = new JPanel();
		panelPrice.setBorder(new TitledBorder(null, Label.SAVED_CREDENTIALS, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelCredentials = new JPanel();
		panelCredentials.setBorder(new TitledBorder(null, Label.CHARGES, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panelSettings = new GroupLayout(panelSettings);
		gl_panelSettings.setHorizontalGroup(
			gl_panelSettings.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelSettings.createSequentialGroup()
					.addGroup(gl_panelSettings.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSettings.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelPrice, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
						.addGroup(gl_panelSettings.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelCredentials, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelSettings.setVerticalGroup(
			gl_panelSettings.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSettings.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCredentials, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(266))
		);
		
		JLabel lblHourPrice = new JLabel(Label.HOUR_PRICE);
		panelCredentials.add(lblHourPrice);
		
		textFieldHourPrice = new JTextField();
		textFieldHourPrice.setHorizontalAlignment(SwingConstants.CENTER);
		panelCredentials.add(textFieldHourPrice);
		textFieldHourPrice.setColumns(10);
		
		JButton btnChange = new JButton(Label.CHANGE);
		panelCredentials.add(btnChange);
		
		JLabel lblUser = new JLabel(Label.USER);
		panelPrice.add(lblUser);
		
		textFieldUser = new JTextField();
		textFieldUser.setHorizontalAlignment(SwingConstants.CENTER);
		panelPrice.add(textFieldUser);
		textFieldUser.setEditable(false);
		textFieldUser.setColumns(10);
		
		JLabel lblPassword = new JLabel(Label.PASSWORD);
		panelPrice.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setEditable(false);
		passwordField.setColumns(10);
		panelPrice.add(passwordField);
		
		JButton btnDelete = new JButton(Label.DELETE);
		panelPrice.add(btnDelete);
		panelSettings.setLayout(gl_panelSettings);
		
		btnToday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String today = FormatConverter.date2String(new Date());
				updateRegistersTable(today, today);
				updateTotalTable();
			}
		});
		
		btnMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String monthYear = FormatConverter.date2String(new Date()).split("/", 2)[1];
				String startDate = "01/" + monthYear;
				String endDate = "31/" + monthYear;
				updateRegistersTable(startDate, endDate);
				updateTotalTable();
			}
		});
		
		btnYear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String year = FormatConverter.date2String(new Date()).split("/")[2];
				String startDate = "01/01/" + year;
				String endDate = "31/12/" + year;
				updateRegistersTable(startDate, endDate);
				updateTotalTable();
			}
		});
		
		btnDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DateWindow date = new DateWindow(MainWindow.this);
				date.getFrame().setVisible(true);
				updateTotalTable();
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RegisterWindow newRegister = new RegisterWindow(MainWindow.this, null, Float.valueOf(((String) textFieldHourPrice.getText()).replace(",", ".")));
				newRegister.getFrame().setVisible(true);
				updateTotalTable();
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableRegisters.getSelectedRow();
				if (row != -1) {
					try {
						Register register = tableObject2Register(row);
						RegisterWindow editRegister = new RegisterWindow(MainWindow.this, register, Float.valueOf(((String) textFieldHourPrice.getText()).replace(",",  ".")));
						editRegister.getFrame().setVisible(true);
						updateTotalTable();
					} catch (ParseException e1) {
						ErrorMessage.showErrorMessage(frmMain, Label.ERROR_WRONG_FORMAT, e1);
					}
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableRegisters.getSelectedRow();
				if (row != -1) {
					String date = (String) tableModelRegisters.getValueAt(row, 0);
					String hour = (String) tableModelRegisters.getValueAt(row, 1);
					removeRegister(date, hour);
					updateTotalTable();
				}
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Files.deleteIfExists(Paths.get(Label.CREDENTIALS_FILE_NAME));
					textFieldUser.setText(null);
					passwordField.setText(null);
				} catch (IOException e1) {
					ErrorMessage.showErrorMessage(frmMain, Label.ERROR_DELETING_CREDENTIALS, e1);
				}
			}
		});
		
		btnChange.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String hourPrice = ((String) textFieldHourPrice.getText()).replace(",", ".");
					Float.valueOf(hourPrice);
					Files.write(Paths.get(Label.SETTINGS_FILE_NAME), hourPrice.getBytes());
					JOptionPane.showMessageDialog(frmMain, Label.HOUR_PRICE_CHANGED_TO + hourPrice, Label.SUCCESSFUL_OPERATION, JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException e1) {
					ErrorMessage.showErrorMessage(frmMain, Label.ERROR_WRONG_FORMAT, e1);
				} catch (Exception e1) {
					ErrorMessage.showErrorMessage(frmMain, Label.ERROR_LOADING_CREDENTIALS, e1);
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
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_LOADING_CREDENTIALS, e1);
		}
		
		try {
			String hourPrice = new String(Files.readAllBytes(Paths.get(Label.SETTINGS_FILE_NAME)));
			textFieldHourPrice.setText(String.format("%.2f", Float.valueOf(hourPrice)));
		} catch (NoSuchFileException e) {
			try {
				Files.write(Paths.get(Label.SETTINGS_FILE_NAME), "2".getBytes());
				textFieldHourPrice.setText(String.format("%.2f", 2));
			} catch (IOException e1) {
				ErrorMessage.showErrorMessage(frmMain, Label.ERROR_SAVING_SETTINGS, e1);
			}
		} catch (IOException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_LOADING_SETTINGS, e);
		}
		
		String today = FormatConverter.date2String(new Date());
		updateRegistersTable(today, today);
		updateTotalTable();

	}
	
	private Object[] register2TableObject(Register register) {
		Object object[] = new Object[6];
		object[0] = register.getDate();
		object[1] = register.getStartTime();
		object[2] = register.getEndTime();
		object[3] = register.getTv();
		if (register.isPaid())
			object[4] = Label.YES;
		else
			object[4] = Label.NO;
		object[5] = FormatConverter.float2Currency(register.getValue());
		return object;
	}
	
	private Register tableObject2Register(int row) throws ParseException{
		return new Register(
				(String)tableModelRegisters.getValueAt(row, 0),
				(String)tableModelRegisters.getValueAt(row, 1),
				(String)tableModelRegisters.getValueAt(row, 2),
				(int)tableModelRegisters.getValueAt(row, 3),
				((String)tableModelRegisters.getValueAt(row, 4)).equals(Label.YES),
				FormatConverter.currency2Float((String)tableModelRegisters.getValueAt(row, 5)));
	}
	
	public void updateRegistersTable(String startDate, String endDate) {
		try {
			List<Register> registers = database.getRegistersByDate(startDate, endDate);
			tableModelRegisters.setRowCount(0);
			for (Register register : registers) {
				Object object[] = register2TableObject(register);
				tableModelRegisters.addRow(object);
			}
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_DATABASE_CONNECTION, e);
		} catch (ParseException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_CONVERTING_DATE, e);
		}
	}
	
	public void updateTotalTable() {		
		try {
			String today = FormatConverter.date2String(new Date());
			List<Register> registers;
			registers = database.getRegistersByDate(today, today);
			Object object[] = new Object[4];
			float total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[0] = FormatConverter.float2Currency(total);
			
			String monthYear = FormatConverter.date2String(new Date()).split("/", 2)[1];
			String firstDay = "01/" + monthYear;
			String lastDay = "31/" + monthYear;
			registers = database.getRegistersByDate(firstDay, lastDay);
			total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[1] = FormatConverter.float2Currency(total);
			
			String year = FormatConverter.date2String(new Date()).split("/")[2];
			firstDay = "01/01/" + year;
			lastDay = "31/12/" + year;
			registers = database.getRegistersByDate(firstDay, lastDay);
			total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[2] = FormatConverter.float2Currency(total);
			
			total = 0;
			for (int i = 0; i < tableModelRegisters.getRowCount(); i++)
				total += FormatConverter.currency2Float((String)tableModelRegisters.getValueAt(i, 5));
			object[3] = FormatConverter.float2Currency(total);
			
			tableModelTotal.setRowCount(0);
			tableModelTotal.addRow(object);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_DATABASE_CONNECTION, e);
		} catch (ParseException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_CONVERTING_DATE, e);
		}
	}
	
	public void addRegister(Register register) {
		try {
			database.insertRegister(register);
			Object object[] = register2TableObject(register);
			tableModelRegisters.addRow(object);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_INSERTING_DATABASE_DATA, e);
		}
	}
	
	public void removeRegister(String dateKey, String timeKey) {
		try {
			database.deleteRegister(dateKey, timeKey);
			tableModelRegisters.removeRow(tableRegisters.getSelectedRow());
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_REMOVING_DATABASE_DATA, e);
		}
	}
	
	public void editRegister(String dateKey, String timeKey, Register register) {
		try {
			database.deleteRegister(dateKey, timeKey);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_REMOVING_DATABASE_DATA, e);
		}
		try {
			database.insertRegister(register);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, Label.ERROR_INSERTING_DATABASE_DATA, e);
		}
		Object tableRegister[] = register2TableObject(register);
		for (int i = 0; i < tableRegister.length; i++)
			tableModelRegisters.setValueAt(tableRegister[i], tableRegisters.getSelectedRow(), i);
	}
	
	public JFrame getFrame(){
		return this.frmMain;
	}
}
