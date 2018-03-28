package com.godgames.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.godgames.model.Register;
import com.godgames.util.DateConverter;

public class Database {
	private Connection connection;
	
	public Database(String serverName, String databaseName, String userName, String password)
			throws ClassNotFoundException, SQLException {
		connection = getMySQLConnection(serverName, databaseName, userName, password);
		Statement st = connection.createStatement();
		st.executeUpdate("create table if not exists registros(data date, "
						+ "hora_inicio time, hora_fim time, "
						+ "tv tinyint unsigned, pago boolean, "
						+ "valor decimal(4,2) unsigned, "
						+ "primary key (data, hora_inicio));");
	}
	
	private Connection getMySQLConnection(String serverName, String databaseName, String userName, String password)
			throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://" + serverName + "/" + databaseName, userName, password);
		return connection;
	}
	
	public void insertRegister(Register register) throws SQLException {
		Statement st = connection.createStatement();
		st.executeUpdate("insert into registros values(STR_TO_DATE('" + register.getDate() + "', '%d/%m/%Y'), '"
							+ register.getStartTime() + "', '" + register.getEndTime() + "', "
							+ register.getTv() + ", " + register.isPaid() + ", "
							+ register.getValue() + ");");
		st.close();
	}
	
	public List<Register> getRegistersByDate(String startDate, String endDate) throws SQLException, ParseException {
		Statement st = connection.createStatement();
		List<Register> registers = new ArrayList<Register>();
		ResultSet rs = st.executeQuery("select * from registros where data >= "
										+ "STR_TO_DATE('" + startDate + "', '%d/%m/%Y') and "
										+ "data <= STR_TO_DATE('" + endDate + "', '%d/%m/%Y') "
										+ "order by data;");
		while (rs.next()) {
			Register register = new Register(DateConverter.bigEndian2LittleEndian(rs.getString(1)),
					rs.getString(2), rs.getString(3),
					rs.getInt(4), rs.getBoolean(5), rs.getFloat(6));
			registers.add(register);						
		}
		st.close();
		return registers;
	}
	
	public void deleteRegister(String date, String hour) throws SQLException {
		Statement st = connection.createStatement();
		st.executeUpdate("delete from registros where data = STR_TO_DATE('" + date + "', '%d/%m/%Y')"
				+ "and hora_inicio = '" + hour + "';");
		st.close();
	}
	
//	//TODO alterar para pegar o valor da hora corretamente do BD
//	public List<Register> getRegistersByCurrentDate() throws CommunicationFailureException{
//		try {
//			Statement st = connection.createStatement();
//			List<Register> registers = new ArrayList<Register>();
//			ResultSet rs = st.executeQuery("select * from registros where data = CURDATE();");
//			while (rs.next()) {
//				Register register = new Register(rs.getString(1), rs.getInt(2), rs.getBoolean(3), rs.getFloat(4));
//				registers.add(register);						
//			}
//			st.close();
//			return registers;
//		} catch (SQLException e) {
//			throw new CommunicationFailureException("Erro de conexao com o Banco de Dados!");
//		}
//	}
	
}
