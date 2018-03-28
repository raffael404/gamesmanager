package com.godgames.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import com.godgames.model.Register;
import com.godgames.util.DateConverter;

import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

public class EditWindow {

	private JDialog dlgEdit;
	
	private JTextField textFieldPrice;
	private JTextField textFieldTv;
	private JTextField textFieldDay;
	private JTextField textFieldStartHour;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textFieldYear;
	private JTextField textFieldMonth;
	private JTextField textFieldStartMinute;
	private JTextField textFieldEndHour;
	private JTextField textFieldEndMinute;
	private JCheckBox chckbxEditDateTime;
	
	private JRadioButton rdbtnNo, rdbtnYes;
	
	private JXDatePicker pickerDate;
	private JSpinner startTimeSpinner, endTimeSpinner;
	
	//TODO buscar em arquivo
	private float hourPrice = 2;
	
	private MainWindow mainWindow;
	
	private String dateKey, timeKey;
	private Register register;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditWindow window = new EditWindow();
//					window.dlgNewRegister.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public EditWindow(MainWindow mainWindow, Register register) {
		this.mainWindow = mainWindow;
		this.dateKey = register.getDate();
		this.timeKey = register.getStartTime();
		this.register = register;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dlgEdit = new JDialog();
		dlgEdit.setTitle("Preencha os dados do registro");
		dlgEdit.setModal(true);
		dlgEdit.setResizable(false);
		dlgEdit.setBounds(100, 100, 590, 130);
		dlgEdit.setLocationRelativeTo(mainWindow.getFrame());
		dlgEdit.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dlgEdit.getContentPane().setLayout(null);
		
		JLabel lblPrice = new JLabel("Pre\u00E7o");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(10, 11, 46, 14);
		dlgEdit.getContentPane().add(lblPrice);
		
		textFieldPrice = new JTextField();
		textFieldPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrice.setBounds(66, 8, 86, 20);
		dlgEdit.getContentPane().add(textFieldPrice);
		textFieldPrice.setColumns(10);
		
		JLabel lblPago = new JLabel("Pago");
		lblPago.setHorizontalAlignment(SwingConstants.CENTER);
		lblPago.setBounds(314, 11, 46, 14);
		dlgEdit.getContentPane().add(lblPago);
		
		JLabel lblTv = new JLabel("TV");
		lblTv.setHorizontalAlignment(SwingConstants.CENTER);
		lblTv.setBounds(162, 11, 46, 14);
		dlgEdit.getContentPane().add(lblTv);
		
		textFieldTv = new JTextField();
		textFieldTv.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldTv.setBounds(218, 8, 86, 20);
		dlgEdit.getContentPane().add(textFieldTv);
		textFieldTv.setColumns(10);
		
		JLabel lblDate = new JLabel("Data");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(10, 36, 46, 14);
		dlgEdit.getContentPane().add(lblDate);
		
		textFieldDay = new JTextField();
		textFieldDay.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDay.setEditable(false);
		textFieldDay.setBounds(66, 33, 20, 20);
		dlgEdit.getContentPane().add(textFieldDay);
		textFieldDay.setColumns(10);
		
		textFieldMonth = new JTextField();
		textFieldMonth.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMonth.setEditable(false);
		textFieldMonth.setColumns(10);
		textFieldMonth.setBounds(92, 33, 20, 20);
		dlgEdit.getContentPane().add(textFieldMonth);
		
		textFieldYear = new JTextField();
		textFieldYear.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldYear.setEditable(false);
		textFieldYear.setColumns(10);
		textFieldYear.setBounds(118, 33, 34, 20);
		dlgEdit.getContentPane().add(textFieldYear);
		
		JPanel panelDate = new JPanel();
		panelDate.setBounds(65, 64, 99, 32);
		dlgEdit.getContentPane().add(panelDate);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		pickerDate = new JXDatePicker();
		pickerDate.setFormats(dateFormat);
		pickerDate.setEditable(false);
		panelDate.add(pickerDate);
		
		JPanel panelStartTime = new JPanel();
		panelStartTime.setBounds(218, 64, 99, 32);
		dlgEdit.getContentPane().add(panelStartTime);
		
		startTimeSpinner = new JSpinner(new SpinnerDateModel());
		startTimeSpinner.setEnabled(false);
		JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm:ss");
		startTimeSpinner.setEditor(startTimeEditor);
		panelStartTime.add(startTimeSpinner);
		
		JPanel panelEndTime = new JPanel();
		panelEndTime.setBounds(366, 64, 99, 32);
		dlgEdit.getContentPane().add(panelEndTime);
		
		endTimeSpinner = new JSpinner(new SpinnerDateModel());
		endTimeSpinner.setEnabled(false);
		JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm:ss");
		endTimeSpinner.setEditor(endTimeEditor);
		panelEndTime.add(endTimeSpinner);
		
		JLabel lblSlash = new JLabel("/");
		lblSlash.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlash.setBounds(86, 36, 6, 14);
		dlgEdit.getContentPane().add(lblSlash);
		
		JLabel lblSlash2 = new JLabel("/");
		lblSlash2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlash2.setBounds(112, 36, 6, 14);
		dlgEdit.getContentPane().add(lblSlash2);
		
		JLabel lblBegin = new JLabel("In\u00EDcio");
		lblBegin.setHorizontalAlignment(SwingConstants.CENTER);
		lblBegin.setBounds(162, 36, 46, 14);
		dlgEdit.getContentPane().add(lblBegin);
		
		textFieldStartHour = new JTextField();
		textFieldStartHour.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldStartHour.setEditable(false);
		textFieldStartHour.setBounds(218, 33, 38, 20);
		dlgEdit.getContentPane().add(textFieldStartHour);
		textFieldStartHour.setColumns(10);
		
		textFieldStartMinute = new JTextField();
		textFieldStartMinute.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldStartMinute.setEditable(false);
		textFieldStartMinute.setColumns(10);
		textFieldStartMinute.setBounds(266, 33, 38, 20);
		dlgEdit.getContentPane().add(textFieldStartMinute);
		
		JLabel lblColon = new JLabel(":");
		lblColon.setHorizontalAlignment(SwingConstants.CENTER);
		lblColon.setBounds(256, 36, 10, 14);
		dlgEdit.getContentPane().add(lblColon);
		
		textFieldEndHour = new JTextField();
		textFieldEndHour.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEndHour.setEditable(false);
		textFieldEndHour.setColumns(10);
		textFieldEndHour.setBounds(366, 33, 38, 20);
		dlgEdit.getContentPane().add(textFieldEndHour);
		
		textFieldEndMinute = new JTextField();
		textFieldEndMinute.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldEndMinute.setEditable(false);
		textFieldEndMinute.setColumns(10);
		textFieldEndMinute.setBounds(414, 33, 38, 20);
		dlgEdit.getContentPane().add(textFieldEndMinute);
		
		JLabel lblColon2 = new JLabel(":");
		lblColon2.setHorizontalAlignment(SwingConstants.CENTER);
		lblColon2.setBounds(404, 36, 10, 14);
		dlgEdit.getContentPane().add(lblColon2);
		
		JLabel lblEnd = new JLabel("Fim");
		lblEnd.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnd.setBounds(314, 36, 46, 14);
		dlgEdit.getContentPane().add(lblEnd);
		
		chckbxEditDateTime = new JCheckBox("Editar Data/Hora");
		chckbxEditDateTime.setBounds(471, 34, 107, 19);
		dlgEdit.getContentPane().add(chckbxEditDateTime);
		
		rdbtnYes = new JRadioButton("Sim");
		buttonGroup.add(rdbtnYes);
		rdbtnYes.setBounds(366, 9, 46, 18);
		dlgEdit.getContentPane().add(rdbtnYes);
		
		rdbtnNo = new JRadioButton("N\u00E3o");
		buttonGroup.add(rdbtnNo);
		rdbtnNo.setBounds(410, 11, 46, 14);
		dlgEdit.getContentPane().add(rdbtnNo);
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(485, 7, 89, 23);
		dlgEdit.getContentPane().add(btnOK);
		
		chckbxEditDateTime.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if (chckbxEditDateTime.isSelected()) {
					textFieldDay.setEditable(true);
					textFieldMonth.setEditable(true);
					textFieldYear.setEditable(true);
					textFieldStartHour.setEditable(true);
					textFieldStartMinute.setEditable(true);
					pickerDate.setEditable(true);
					startTimeSpinner.setEnabled(true);
				} else{
					textFieldDay.setEditable(false);
					textFieldMonth.setEditable(false);
					textFieldYear.setEditable(false);
					textFieldStartHour.setEditable(false);
					textFieldStartMinute.setEditable(false);
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
				
		KeyListener calculateFinalHour = new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(!textFieldPrice.getText().isEmpty() && !textFieldStartHour.getText().isEmpty() && !textFieldStartMinute.getText().isEmpty()){
					String endTime = "";
					float price = Float.parseFloat(textFieldPrice.getText().replace(",", "."));
					int startHour = Integer.parseInt(textFieldStartHour.getText());
					int startMinute = Integer.parseInt(textFieldStartMinute.getText());
					
					float fraction = (price/hourPrice)%1;
					int plusHour = (int)((price/hourPrice) - fraction);
					int plusMinute = (int) (fraction * 60);
					
					if ((startMinute + plusMinute) >= 60) {
						plusHour = plusHour + 1;
						plusMinute = plusMinute - 60;
					}
					if ((startHour + plusHour) >= 24)
						plusHour = plusHour - 24;
					
					String complement;
					
					if ((startHour + plusHour) < 10)
						complement = "0";
					else
						complement = "";
					textFieldEndHour.setText(complement + (startHour + plusHour));
					endTime = complement + (startHour + plusHour);
					
					if ((startMinute + plusMinute) < 10)
						complement = "0";
					else
						complement = "";
					textFieldEndMinute.setText(complement + (startMinute + plusMinute));
					endTime += ":" + (complement + (startMinute + plusMinute)) + ":00";
					try {
						endTimeSpinner.setValue(new SimpleDateFormat("HH:mm:ss").parse(endTime));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					textFieldEndHour.setText(textFieldStartHour.getText());
					textFieldEndMinute.setText(textFieldStartMinute.getText());
					endTimeSpinner.setValue(startTimeSpinner.getValue());
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {}			
			@Override
			public void keyPressed(KeyEvent e) {}
			
		};
		
		//TODO impedir que os campos de texto e botoes sejam nulos
		//TODO verificar a questao de virgula ou ponto para valor float
		//TODO trocar os campos de data e hora por JFormattedTextField ou outro mais automatico
		btnOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
//					mainWindow.removeRegister(dateKey, timeKey);
//					mainWindow.addRegister(new Register(textFieldDay.getText()+"/"+textFieldMonth.getText()+"/"+textFieldYear.getText(),
//							textFieldStartHour.getText()+":"+textFieldStartMinute.getText(), textFieldEndHour.getText()+":"+textFieldEndMinute.getText(),
//							Integer.valueOf(textFieldTv.getText()), rdbtnYes.isSelected(), Float.valueOf(textFieldPrice.getText().replace(",", "."))));
//					String today = DateConverter.date2String(new Date());
//					//TODO tentar adicionar as linhas novas e atualizar a tabela manualmente
//					mainWindow.updateRegistersTable(today, today);
//					mainWindow.updateTotalTable(today, today);
					dlgEdit.dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		textFieldPrice.addKeyListener(calculateFinalHour);
		textFieldStartHour.addKeyListener(calculateFinalHour);
		textFieldStartMinute.addKeyListener(calculateFinalHour);
		
		textFieldPrice.setText(String.valueOf(register.getValue()));
		textFieldTv.setText(String.valueOf(register.getTv()));
		
		String date[] = register.getDate().split("/");
		textFieldDay.setText(date[0]);
		textFieldMonth.setText(date[1]);
		textFieldYear.setText(date[2]);
		
		try {
			pickerDate.setDate(dateFormat.parse(register.getDate()));
			startTimeSpinner.setValue(new SimpleDateFormat("HH:mm:ss").parse(register.getStartTime()+":00"));
			endTimeSpinner.setValue(new SimpleDateFormat("HH:mm:ss").parse(register.getEndTime()+":00"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		String startTime[] = register.getStartTime().split(":");
		String endTime[] = register.getEndTime().split(":");
		textFieldStartHour.setText(startTime[0]);
		textFieldStartMinute.setText(startTime[1]);
		textFieldEndHour.setText(endTime[0]);
		textFieldEndMinute.setText(endTime[1]);
		
		if (register.isPaid())
			rdbtnYes.doClick();
		else
			rdbtnNo.doClick();
		
	}
	
	public JDialog getFrame(){
		return this.dlgEdit;
	}
}
