package com.godgames.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorMessage {
	
	public static void showErrorMessage(Component parentComponent, String title, Exception exception){
		StackTraceElement[] traces = exception.getStackTrace();
		String finalTrace = "";
		for (StackTraceElement trace : traces) {
			if (trace.toString().contains(Label.ENTERPRISE_SIGNATURE)) {
				finalTrace += trace + "\n";
			}
		}
		
		JOptionPane.showMessageDialog(parentComponent, Label.CAUSE + ": " + exception.getMessage()
				+ "\n\n" + Label.TRACE + ":\n" + finalTrace, title, JOptionPane.ERROR_MESSAGE);
	}

}
