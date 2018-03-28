package com.godgames.util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ErrorMessage {
	
	public static String CONVERTING_DATE = "Erro ao converter o formato de data!";
	public static String DRIVER_NOT_FOUND = "Driver nao encontrado!";
	public static String DATABASE_CONNECTION = "Erro de conexão com o Banco de Dados!";
	public static String REMOVING_DATABASE_DATA = "Erro ao remover dados no Banco!";
	public static String INSERTING_DATABASE_DATA = "Erro ao inserir dados no Banco!";
	
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
