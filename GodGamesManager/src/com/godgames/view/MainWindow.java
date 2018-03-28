package com.godgames.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.godgames.dao.Database;
import com.godgames.model.Register;
import com.godgames.util.DateConverter;
import com.godgames.util.ErrorMessage;
import com.godgames.util.Label;

public class MainWindow {

	private JFrame frmMain;
	
	private JTable tableRegisters;
	private JTable tableTotal;
	private DefaultTableModel tableModelRegisters = new DefaultTableModel();
	private DefaultTableModel tableModelTotal = new DefaultTableModel();
	
	private Database database;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainWindow window = new MainWindow();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
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
		tabbedPane.addTab(Label.REGISTERS, null, panelRegisters, null);
		
		JButton btnAdd = new JButton(Label.ADD);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JScrollPane scrollPaneRegisters = new JScrollPane();
		
		JScrollPane scrollPaneTotal = new JScrollPane();
		scrollPaneTotal.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPaneTotal.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton btnEdit = new JButton(Label.EDIT);
		
		JButton btnRemove = new JButton(Label.REMOVE);
		
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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab(Label.CHANGE, null, panel_1, null);
		
		btnToday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String today = DateConverter.date2String(new Date());
				updateRegistersTable(today, today);
				updateTotalTable(today, today);
			}
		});
		
		btnMonth.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String monthYear = DateConverter.date2String(new Date()).split("/", 2)[1];
				String startDate = "01/" + monthYear;
				String endDate = "31/" + monthYear;
				updateRegistersTable(startDate, endDate);
				updateTotalTable(startDate, endDate);
			}
		});
		
		btnYear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String year = DateConverter.date2String(new Date()).split("/")[2];
				String startDate = "01/01/" + year;
				String endDate = "31/12/" + year;
				updateRegistersTable(startDate, endDate);
				updateTotalTable(startDate, endDate);
			}
		});
		
		btnDate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DateWindow date = new DateWindow(MainWindow.this);
				date.getFrame().setVisible(true);
				if (date.isConfirmed()) {
					String startDate = date.getStartDate();
					String endDate = date.getEndDate();
					updateRegistersTable(startDate, endDate);
					updateTotalTable(startDate, endDate);
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				NewWindow newRegister = new NewWindow(MainWindow.this);
				newRegister.getFrame().setVisible(true);
				if (newRegister.isConfirmed()) {
					addRegister(new Register(newRegister.getDate(), newRegister.getStartTime(), newRegister.getEndTime(),
							newRegister.getTV(), newRegister.isPaid(), newRegister.getValue()));
				}
			}
		});
		
		//TODO tentar transformar new edit na mesma janela
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tableRegisters.getSelectedRow();
				if (row != -1) {
					Register register = new Register((String) tableModelRegisters.getValueAt(row, 0),
							(String) tableModelRegisters.getValueAt(row, 1),
							(String) tableModelRegisters.getValueAt(row, 2),
							(Integer) tableModelRegisters.getValueAt(row, 3),
							((String) tableModelRegisters.getValueAt(row, 4)).equals("Sim"),
							Float.valueOf(((String) tableModelRegisters.getValueAt(row, 5)).split(" ")[1].replace(",", ".")));
					EditWindow editRegister = new EditWindow(MainWindow.this, register);
					editRegister.getFrame().setVisible(true);
				}
			}
		});
		
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Remover do BD
				int row = tableRegisters.getSelectedRow();
				if (row != -1) {
					String date = (String) tableModelRegisters.getValueAt(row, 0);
					String hour = (String) tableModelRegisters.getValueAt(row, 1);
					removeRegister(date, hour);
					tableModelRegisters.removeRow(row);
					//TODO alterar manualmente o valor da coluna total busca
//					String today = DateConverter.date2String(new Date());
//					updateTotalTable(today, today);
				}
			}
		});
		
		createDatabaseConnection("localhost", "god_games", "root", "root");
		String today = DateConverter.date2String(new Date());
		updateRegistersTable(today, today);
		updateTotalTable(today, today);

	}
	
	private void createDatabaseConnection(String serverName, String databaseName, String user, String password){
		try {
			database = new Database(serverName, databaseName, user, password);
		} catch (ClassNotFoundException e1) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.DRIVER_NOT_FOUND, e1);
		} catch (SQLException e1) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.DATABASE_CONNECTION, e1);
		}
	}
	
	public void updateRegistersTable(String startDate, String endDate) {
		try {
			List<Register> registers = database.getRegistersByDate(startDate, endDate);
			tableModelRegisters.setRowCount(0);
			for (Register register : registers) {
				Object object[] = new Object[6];
				object[0] = register.getDate();
				object[1] = register.getStartTime();
				object[2] = register.getEndTime();
				object[3] = register.getTv();
				if (register.isPaid())
					object[4] = "Sim";
				else
					object[4] = "Não";
				object[5] = NumberFormat.getCurrencyInstance().format(register.getValue());
				tableModelRegisters.addRow(object);
			}
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.DATABASE_CONNECTION, e);
		} catch (ParseException e) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.CONVERTING_DATE, e);
		}
	}
	
	public void updateTotalTable(String startDate, String endDate) {		
		try {
			String today = DateConverter.date2String(new Date());
			List<Register> registers;
			registers = database.getRegistersByDate(today, today);
			Object object[] = new Object[4];
			double total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[0] = NumberFormat.getCurrencyInstance().format(total);
			
			String monthYear = DateConverter.date2String(new Date()).split("/", 2)[1];
			String firstDay = "01/" + monthYear;
			String lastDay = "31/" + monthYear;
			registers = database.getRegistersByDate(firstDay, lastDay);
			total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[1] = NumberFormat.getCurrencyInstance().format(total);
			
			String year = DateConverter.date2String(new Date()).split("/")[2];
			firstDay = "01/01/" + year;
			lastDay = "31/12/" + year;
			registers = database.getRegistersByDate(firstDay, lastDay);
			total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[2] = NumberFormat.getCurrencyInstance().format(total);
			
			registers = database.getRegistersByDate(startDate, endDate);
			total = 0;
			for (Register register : registers) {
				total += register.getValue();
			}
			object[3] = NumberFormat.getCurrencyInstance().format(total);
			
			tableModelTotal.setRowCount(0);
			tableModelTotal.addRow(object);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.DATABASE_CONNECTION, e);
		} catch (ParseException e) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.CONVERTING_DATE, e);
		}
	}
	
	public void addRegister(Register register) {
		try {
			database.insertRegister(register);
			Object object[] = new Object[6];
			object[0] = register.getDate();
			object[1] = register.getStartTime();
			object[2] = register.getEndTime();
			object[3] = register.getTv();
			if (register.isPaid())
				object[4] = "Sim";
			else
				object[4] = "Não";
			object[5] = NumberFormat.getCurrencyInstance().format(register.getValue());
			tableModelRegisters.addRow(object);
			
			float searchTotal = Float.parseFloat(((String) tableModelTotal.getValueAt(0, 3))
					.split(" ")[1].replace(",", "."));
			searchTotal += register.getValue();
			tableModelTotal.setValueAt(NumberFormat.getCurrencyInstance().format(searchTotal), 0, 3);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.INSERTING_DATABASE_DATA, e);
		}
	}
	
	public void removeRegister(String dateKey, String timeKey) {
		try {
			database.deleteRegister(dateKey, timeKey);
		} catch (SQLException e) {
			ErrorMessage.showErrorMessage(frmMain, ErrorMessage.REMOVING_DATABASE_DATA, e);
		}
	}
	
	public JFrame getFrame(){
		return this.frmMain;
	}
}
