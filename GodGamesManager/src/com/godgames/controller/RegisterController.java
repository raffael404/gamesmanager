package com.godgames.controller;

import java.util.List;

import com.godgames.model.Register;

public class RegisterController {
	private List<Register> registers;
	
	public void addRegistter(Register register){
		registers.add(register);
	}
}
