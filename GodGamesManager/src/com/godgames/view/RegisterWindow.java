package com.godgames.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
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
	
	private float hourPrice;
	
	private MainWindow mainWindow;
	
	private String dateKey, timeKey;
	private Register register;

	/**
	 * Create the application.
	 */
	public RegisterWindow(MainWindow mainWindow, Register register, float hourPrice) {
		this.mainWindow = mainWindow;
		this.hourPrice = hourPrice;
		
		if (register == null) {
			this.register = new Register(
					FormatConverter.date2String(new Date()), FormatConverter.time2String(new Date()), FormatConverter.time2String(new Date()), 0, false, 0);
		} else {
			this.dateKey = register.getDate();
			this.timeKey = register.getStartTime();
			this.register = register;			
		}
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
		dlgRegister.setBounds(100, 100, 570, 110);
		dlgRegister.setLocationRelativeTo(mainWindow.getFrame());
		dlgRegister.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgRegister.getContentPane().setLayout(null);
		
		JLabel lblPrice = new JLabel(Label.PRICE);
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(10, 11, 27, 14);
		dlgRegister.getContentPane().add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrice.setBounds(47, 7, 99, 22);
		dlgRegister.getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblPago = new JLabel(Label.PAID);
		lblPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblPago.setBounds(308, 11, 27, 14);
		dlgRegister.getContentPane().add(lblPago);
		
		JLabel lblTv = new JLabel(Label.TV);
		lblTv.setHorizontalAlignment(SwingConstants.CENTER);
		lblTv.setBounds(156, 11, 13, 14);
		dlgRegister.getContentPane().add(lblTv);
		
		textFieldTv = new JTextField();
		textFieldTv.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTv.setBounds(199, 7, 99, 22);
		dlgRegister.getContentPane().add(textFieldTv);
		textFieldTv.setColumns(10);
		
		JLabel lblDate = new JLabel(Label.DATE);
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(10, 31, 27, 32);
		dlgRegister.getContentPane().add(lblDate);
		
		JPanel panelDate = new JPanel();
		panelDate.setBounds(47, 31, 99, 32);
		dlgRegister.getContentPane().add(panelDate);
		
		pickerDate = new JXDatePicker();
		pickerDate.setFormats(new SimpleDateFormat("dd/MM/yyyy"));
		pickerDate.setEditable(false);
		panelDate.add(pickerDate);
		
		JPanel panelStartTime = new JPanel();
		panelStartTime.setBounds(199, 31, 99, 32);
		dlgRegister.getContentPane().add(panelStartTime);
		
		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		startTimeSpinner.setEnabled(false);
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm:ss");
		startTimeSpinner.setEditor(startTimeEditor);
		panelStartTime.add(startTimeSpinner);
		
		JPanel panelEndTime = new JPanel();
		panelEndTime.setBounds(336, 31, 99, 32);
		dlgRegister.getContentPane().add(panelEndTime);
		
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		endTimeSpinner.setEnabled(false);
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm:ss");
		endTimeSpinner.setEditor(endTimeEditor);
		panelEndTime.add(endTimeSpinner);
				
		JLabel lblBegin = new JLabel(Label.START);
		lblBegin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBegin.setBounds(156, 31, 33, 32);
		dlgRegister.getContentPane().add(lblBegin);
		
		JLabel lblEnd = new JLabel(Label.END);
		lblEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd.setBounds(308, 31, 18, 32);
		dlgRegister.getContentPane().add(lblEnd);
		
		chckbxEditDateTime = new JCheckBox(Label.EDIT_DATE_TIME);
		chckbxEditDateTime.setBounds(441, 31, 107, 32);
		dlgRegister.getContentPane().add(chckbxEditDateTime);
		
		rdbtnYes = new JRadioButton(Label.YES);
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(341, 9, 46, 18);
		dlgRegister.getContentPane().add(rdbtnYes);
		
		rdbtnNo = new JRadioButton(Label.NO);
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(389, 11, 46, 14);
		dlgRegister.getContentPane().add(rdbtnNo);
		
		JButton btnOK = new JButton(Label.OK);
		btnOK.setBounds(441, 6, 107, 25);
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
					ErrorMessage.showErrorMessage(dlgRegister, Label.ERROR_WRONG_FORMAT, new Exception(Label.ERROR_EMPTY_FIELD_DATE));
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
		
		try {
			String price = new String(Files.readAllBytes(Paths.get(Label.SETTINGS_FILE_NAME)));
			hourPrice = Float.valueOf(price);
		} catch (NoSuchFileException e) {
			try {
				Files.write(Paths.get(Label.SETTINGS_FILE_NAME), "2".getBytes());
				hourPrice = 2;
			} catch (IOException e1) {
				ErrorMessage.showErrorMessage(dlgRegister, Label.ERROR_SAVING_SETTINGS, e1);
			}
		} catch (IOException e) {
			ErrorMessage.showErrorMessage(dlgRegister, Label.ERROR_LOADING_SETTINGS, e);
		}
		
		if (register.getValue() != 0)
			textFieldPrice.setText(String.format("%.2f", register.getValue()));
		if (register.getTv() != 0)
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
