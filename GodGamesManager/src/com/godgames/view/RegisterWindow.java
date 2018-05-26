package com.godgames.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

import com.godgames.model.Register;
import com.godgames.util.ErrorMessage;
import com.godgames.util.FormatConverter;
import com.godgames.util.Label;

public class RegisterWindow {

	private JDialog dlgRegister;
	
	private JTextField textFieldPrice;
	private JTextField textFieldTv;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chckbxEditDateTime;
	
	private JRadioButton rdbtnNo, rdbtnYes;
	
	private JXDatePicker pickerDate;
	private JSpinner startTimeSpinner, endTimeSpinner;
	
	//TODO buscar em arquivo
	private float hourPrice = 2;
	
	private MainWindow mainWindow;
	
	private String dateKey, timeKey;
	private Register register;

	/**
	 * Create the application.
	 */
	public RegisterWindow(MainWindow mainWindow, Register register) {
		this.mainWindow = mainWindow;
		this.dateKey = register.getDate();
		this.timeKey = register.getStartTime();
		this.register = register;
		initialize();
	}
	
	public RegisterWindow(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		this.register = new Register(
				FormatConverter.date2String(new Date()), FormatConverter.time2String(new Date()), FormatConverter.time2String(new Date()), 0, false, 0);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgRegister = new JDialog();
		dlgRegister.setTitle(Label.TITLE_WINDOW_REGISTERS);
		dlgRegister.setModal(true);
		dlgRegister.setResizable(false);
		dlgRegister.setBounds(100, 100, 590, 100);
		dlgRegister.setLocationRelativeTo(mainWindow.getFrame());
		dlgRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgRegister.getContentPane().setLayout(null);
		
		JLabel lblPrice = new JLabel(Label.PRICE);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(10, 11, 46, 14);
		dlgRegister.getContentPane().add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrice.setBounds(66, 8, 99, 20);
		dlgRegister.getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblPago = new JLabel(Label.PAID);
		lblPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblPago.setBounds(314, 11, 46, 14);
		dlgRegister.getContentPane().add(lblPago);
		
		JLabel lblTv = new JLabel(Label.TV);
		lblTv.setHorizontalAlignment(SwingConstants.CENTER);
		lblTv.setBounds(162, 11, 46, 14);
		dlgRegister.getContentPane().add(lblTv);
		
		textFieldTv = new JTextField();
		textFieldTv.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTv.setBounds(218, 8, 99, 20);
		dlgRegister.getContentPane().add(textFieldTv);
		textFieldTv.setColumns(10);
		
		JLabel lblDate = new JLabel(Label.DATE);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(10, 36, 46, 27);
		dlgRegister.getContentPane().add(lblDate);
		
		JPanel panelDate = new JPanel();
		panelDate.setBounds(66, 31, 99, 32);
		dlgRegister.getContentPane().add(panelDate);
		
		pickerDate = new JXDatePicker();
		pickerDate.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		pickerDate.setEditable(false);
		panelDate.add(pickerDate);
		
		JPanel panelStartTime = new JPanel();
		panelStartTime.setBounds(218, 31, 99, 32);
		dlgRegister.getContentPane().add(panelStartTime);
		
		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		startTimeSpinner.setEnabled(false);
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm:ss");
		startTimeSpinner.setEditor(startTimeEditor);
		panelStartTime.add(startTimeSpinner);
		
		JPanel panelEndTime = new JPanel();
		panelEndTime.setBounds(366, 31, 99, 32);
		dlgRegister.getContentPane().add(panelEndTime);
		
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		endTimeSpinner.setEnabled(false);
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm:ss");
		endTimeSpinner.setEditor(endTimeEditor);
		panelEndTime.add(endTimeSpinner);
				
		JLabel lblBegin = new JLabel(Label.START);
		lblBegin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBegin.setBounds(162, 31, 46, 32);
		dlgRegister.getContentPane().add(lblBegin);
		
		JLabel lblEnd = new JLabel(Label.END);
		lblEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd.setBounds(314, 31, 46, 32);
		dlgRegister.getContentPane().add(lblEnd);
		
		chckbxEditDateTime = new JCheckBox(Label.EDIT_DATE_TIME);
		chckbxEditDateTime.setBounds(471, 34, 107, 29);
		dlgRegister.getContentPane().add(chckbxEditDateTime);
		
		rdbtnYes = new JRadioButton(Label.YES);
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(366, 9, 46, 18);
		dlgRegister.getContentPane().add(rdbtnYes);
		
		rdbtnNo = new JRadioButton(Label.NO);
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(419, 11, 46, 14);
		dlgRegister.getContentPane().add(rdbtnNo);
		
		JButton btnOK = new JButton(Label.OK);
		btnOK.setBounds(485, 7, 89, 23);
		dlgRegister.getContentPane().add(btnOK);
		
		chckbxEditDateTime.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (chckbxEditDateTime.isSelected()) {
					pickerDate.setEditable(true);
					startTimeSpinner.setEnabled(true);
				} else{
					pickerDate.setEditable(false);
					startTimeSpinner.setEnabled(false);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
			
		});
		
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pickerDate.getDate() == null) {
					ErrorMessage.showErrorMessage(dlgRegister, Label.ERROR_WRONG_FORMAT, new Exception(Label.EXCEPTION_EMPTY_FIELD));
				} else {
					try {
						Register register = new Register(FormatConverter.date2String(pickerDate.getDate()),
								FormatConverter.time2String((Date)startTimeSpinner.getValue()), FormatConverter.time2String((Date)endTimeSpinner.getValue()),
								Integer.valueOf(textFieldTv.getText()), rdbtnYes.isSelected(), Float.valueOf(textFieldPrice.getText().replace(",", ".")));
						if (dateKey != null && timeKey != null)
							mainWindow.editRegister(dateKey, timeKey, register);
						else
							mainWindow.addRegister(register);
						dlgRegister.dispose();
					} catch (NumberFormatException e1) {
						ErrorMessage.showErrorMessage(dlgRegister, Label.ERROR_WRONG_FORMAT, e1);
					}
				}
			}
		});
		
		textFieldPrice.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if (!textFieldPrice.getText().isEmpty() && startTimeSpinner.getValue() != null) {
					float price = Float.parseFloat(textFieldPrice.getText().replace(",", "."));
					long plusTime = Math.round((price/hourPrice) * TimeUnit.HOURS.toMillis(1));
					endTimeSpinner.setValue(new Date(((Date)startTimeSpinner.getValue()).getTime() + plusTime));
				} else {
					endTimeSpinner.setValue(startTimeSpinner.getValue());
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}			
			@Override
			public void keyPressed(KeyEvent e) {}
			
		});
		
		startTimeSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if (!textFieldPrice.getText().isEmpty() && startTimeSpinner.getValue() != null) {
					float price = Float.parseFloat(textFieldPrice.getText().replace(",", "."));
					long plusTime = Math.round((price/hourPrice) * TimeUnit.HOURS.toMillis(1));
					endTimeSpinner.setValue(new Date(((Date)startTimeSpinner.getValue()).getTime() + plusTime));
				} else {
					endTimeSpinner.setValue(startTimeSpinner.getValue());
				}
			}
		});
		
		textFieldPrice.setText(String.valueOf(register.getValue()));
		textFieldTv.setText(String.valueOf(register.getTv()));
		
		try {
			pickerDate.setDate(FormatConverter.string2Date(register.getDate()));
			startTimeSpinner.setValue(FormatConverter.string2Time(register.getStartTime()));
			endTimeSpinner.setValue(FormatConverter.string2Time(register.getEndTime()));
		} catch (ParseException e) {
			ErrorMessage.showErrorMessage(dlgRegister, Label.ERROR_CONVERTING_DATE, e);
		}
				
		if (register.isPaid())
			rdbtnYes.doClick();
		else
			rdbtnNo.doClick();
		
	}
	
	public JDialog getFrame(){
		return this.dlgRegister;
	}
}
