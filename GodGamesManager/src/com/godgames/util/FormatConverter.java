package com.godgames.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class FormatConverter {
	private static final SimpleDateFormat leConverter = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat beConverter = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat timeConverter = new SimpleDateFormat("HH:mm:ss");
	
	public static String date2String(Date date) {
		return leConverter.format(date);
	}
	
	public static String bigEndian2LittleEndian(String date) throws ParseException {
		return leConverter.format(beConverter.parse(date));
	}
	
	public static String time2String(Date date) {
		return timeConverter.format(date);
	}
	
	public static Date string2Date(String string) throws ParseException{
		return leConverter.parse(string);
	}
	
	public static Date string2Time(String string) throws ParseException{
		return timeConverter.parse(string);
	}
	
	public static float currency2Float(String string) throws ParseException{
		return NumberFormat.getCurrencyInstance().parse(string).floatValue();
	}
	
	public static String float2Currency(float f) {
		return NumberFormat.getCurrencyInstance().format(f);
	}
	
	public static String encrypt(String text, String key) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		return new String(encrypted);
	}
	
	public static String decrypt(String encryptedText, String key) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "Blowfish");
		Cipher cipher = Cipher.getInstance("Blowfish");
		cipher.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decrypted = cipher.doFinal(encryptedText.getBytes());
		return new String(decrypted);
	}
}
