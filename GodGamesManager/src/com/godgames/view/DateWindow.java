package com.godgames.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import com.godgames.util.DateConverter;

public class DateWindow {

	private JDialog dlgDate;
	private JXDatePicker pickerFrom, pickerTo;
	private MainWindow mainWindow;
	
	private boolean confirmed = false;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DateWindow window = new DateWindow();
//					window.dlgDate.setVisible(true);
//					System.out.println("teste");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public DateWindow() {
//		initialize();
//	}
	
	/**
	 * Create the application.
	 */
	public DateWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgDate = new JDialog();
		dlgDate.setModal(true);
		dlgDate.setResizable(false);
		dlgDate.setTitle("Defina o intervalo de tempo");
		dlgDate.setBounds(100, 100, 405, 85);
		dlgDate.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgDate.setLocationRelativeTo(mainWindow.getFrame());
		
		JPanel panel = new JPanel();
		dlgDate.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panelFrom = new JPanel();
		panelFrom.setBounds(40, 11, 99, 32);
		panel.add(panelFrom);
		
		JPanel panelTo = new JPanel();
		panelTo.setBounds(179, 11, 99, 32);
		panel.add(panelTo);
		
		pickerFrom = new JXDatePicker();
        pickerFrom.setDate(Calendar.getInstance().getTime());
        pickerFrom.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        panelFrom.add(pickerFrom);
        
        pickerTo = new JXDatePicker();
        pickerTo.setDate(Calendar.getInstance().getTime());
        pickerTo.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
        panelTo.add(pickerTo);
        
        JButton btnOk = new JButton("OK");
        btnOk.setBounds(288, 11, 89, 32);
        panel.add(btnOk);
        
        JLabel lblFrom = new JLabel("De: ");
        lblFrom.setBounds(10, 11, 30, 32);
        panel.add(lblFrom);
        
        JLabel lblTo = new JLabel("At\u00E9: ");
        lblTo.setBounds(149, 11, 30, 32);
        panel.add(lblTo);
        
        btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				String from = DateConverter.date2String(pickerFrom.getDate());
//				String to = DateConverter.date2String(pickerTo.getDate());
//				mainWindow.updateRegistersTable(from, to);
//				mainWindow.updateTotalTable(from, to);
				confirmed = true;
				dlgDate.dispose();
			}
		});
	}
	
	public JDialog getFrame(){
		return this.dlgDate;
	}
	
	public String getStartDate(){
		return DateConverter.date2String(pickerFrom.getDate());
	}
	
	public String getEndDate(){
		return DateConverter.date2String(pickerTo.getDate());
	}
	
	public boolean isConfirmed(){
		return confirmed;
	}
}
