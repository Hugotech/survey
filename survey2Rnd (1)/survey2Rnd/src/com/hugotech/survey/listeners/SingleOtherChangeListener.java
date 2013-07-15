package com.hugotech.survey.listeners;

import java.util.Map;

import javax.swing.JRadioButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SingleOtherChangeListener implements ChangeListener{

	Map<JRadioButton, JTextField> map = null;
	
	public SingleOtherChangeListener() {
		
	}
	
	public SingleOtherChangeListener(Map<JRadioButton, JTextField> map) {	
		this.map = map;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JRadioButton rb = (JRadioButton)e.getSource();
		if(rb.isSelected()){
			JTextField tf = (JTextField)map.get(rb);
			if(tf!=null){
			tf.setEditable(true);
			}
		}
		if(!rb.isSelected()){
			JTextField tf = (JTextField)map.get(rb);
			if(tf!=null){
			tf.setEditable(false);
			tf.setText("");
			}
		}
	}
}
