package com.hugotech.survey.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextListener/* implements ActionListener*/{

	public static Vector<JTextField> textFields = new Vector<JTextField>();
	
	public TextListener() {
		
	}
	
/*	@Override
	public void actionPerformed(ActionEvent e) {
	}
	*/
	
	public void addTextField(JTextField textField){
		//textField.addActionListener(this);
		textFields.add(textField);
	}
	
}

