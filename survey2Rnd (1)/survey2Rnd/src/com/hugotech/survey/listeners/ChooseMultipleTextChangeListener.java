package com.hugotech.survey.listeners;

import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ChooseMultipleTextChangeListener implements ChangeListener{

	
	Map<JCheckBox, JTextField> map = null;
	
	public ChooseMultipleTextChangeListener() {
		// TODO Auto-generated constructor stub
	}
	
	public ChooseMultipleTextChangeListener(Map<JCheckBox, JTextField> map){
		this.map = map;
	}
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JCheckBox cb = (JCheckBox)e.getSource();
		if(cb.isSelected()){
			JTextField tf = (JTextField)map.get(cb);
			if(tf!=null){
			tf.setEditable(true);
			}
		}
		if(!cb.isSelected()){
			JTextField tf = (JTextField)map.get(cb);
			if(tf!=null){
			tf.setEditable(false);
			tf.setText("");
			}
		}
		//System.out.println("123");
	}
}
