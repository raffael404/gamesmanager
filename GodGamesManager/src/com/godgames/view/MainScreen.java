package com.godgames.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame frmGodGamesManager;
	private JTable tableRegisters;
	private DefaultTableModel tableModelRegisters = new DefaultTableModel();
	private DefaultTableModel tableModelTotal = new DefaultTableModel();
	private JButton btnAdicionarRegistro;
	private JTable tableTotal;
	private JScrollPane scrollPaneTotal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmGodGamesManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGodGamesManager = new JFrame();
		frmGodGamesManager.setTitle("God Games Manager");
		frmGodGamesManager.setResizable(false);
		frmGodGamesManager.setBounds(100, 100, 640, 460);
		frmGodGamesManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGodGamesManager.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 18, 500, 340);
		frmGodGamesManager.getContentPane().add(scrollPane);
		
		tableRegisters = new JTable(tableModelRegisters);
		tableRegisters.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		tableModelRegisters.addColumn("Hora inicial");
		tableModelRegisters.addColumn("Hora final");
		tableModelRegisters.addColumn("TV");
		tableModelRegisters.addColumn("Pago");
		tableModelRegisters.addColumn("Preço");
		
		tableRegisters.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(1).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(3).setPreferredWidth(30);
		tableRegisters.getColumnModel().getColumn(4).setPreferredWidth(30);
		
		scrollPane.setViewportView(tableRegisters);
		
		btnAdicionarRegistro = new JButton("Adicionar");
		btnAdicionarRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdicionarRegistro.setBounds(520, 18, 104, 30);
		frmGodGamesManager.getContentPane().add(btnAdicionarRegistro);
		tableModelTotal.addColumn("Total dia");
		tableModelTotal.addColumn("Total semana");
		tableModelTotal.addColumn("Total mes");
		tableModelTotal.addColumn("Total ano");
		
		scrollPaneTotal = new JScrollPane();
		scrollPaneTotal.setBounds(10, 369, 500, 51);
		frmGodGamesManager.getContentPane().add(scrollPaneTotal);
		
		tableTotal = new JTable(tableModelTotal);
		scrollPaneTotal.setViewportView(tableTotal);
		
		tableTotal.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		
		tableTotal.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableTotal.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableTotal.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableTotal.getColumnModel().getColumn(3).setPreferredWidth(50);
	}
}
