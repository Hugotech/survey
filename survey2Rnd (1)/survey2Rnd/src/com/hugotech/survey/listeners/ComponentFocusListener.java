package com.hugotech.survey.listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ComponentFocusListener implements FocusListener {

	@Override
	public void focusGained(FocusEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JCheckBox){
			JCheckBox box = (JCheckBox)obj;
			box.setBackground(new Color(224,224,224));
		}
		if(obj instanceof JRadioButton){
			JRadioButton box = (JRadioButton)obj;
			box.setBackground(new Color(224,224,224));
		}
		
		if(obj instanceof JTextField){
			JTextField box = (JTextField)obj;
			box.setBackground(new Color(224,224,224));
		}
		if(obj instanceof JTextArea){
			JTextArea area = (JTextArea)obj;
			area.setBackground(new Color(224,224,224));
		}
		

	}

	@Override
	public void focusLost(FocusEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JCheckBox){
			JCheckBox box = (JCheckBox)obj;
			box.setBackground(Color.yellow);
		}
		
		if(obj instanceof JRadioButton){
			JRadioButton box = (JRadioButton)obj;
			box.setBackground(Color.yellow);
		}
		
		if(obj instanceof JTextField){
			JTextField box = (JTextField)obj;
			box.setBackground(Color.white);
		}
		
		if(obj instanceof JTextArea){
			JTextArea area = (JTextArea)obj;
			area.setBackground(Color.white);
		}
		
		

	}

}
