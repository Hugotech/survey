package com.hugotech.survey.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JRadioButton;

public class RadioListener implements ActionListener{

	public static Vector<JRadioButton> radiobuttons = new Vector<JRadioButton>();
	
	public RadioListener() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}
	
	
	public void addRadioButton(JRadioButton button){
		button.addActionListener(this);
		radiobuttons.add(button);
	}
}

