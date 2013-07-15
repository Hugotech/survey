package com.hugotech.survey.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;

public class CheckBoxListener implements ActionListener{

	public static Vector<JCheckBox> checkboxs = new Vector<JCheckBox>();
	
	public CheckBoxListener() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBox cb = (JCheckBox)e.getSource();
		
		for(JCheckBox boxs:checkboxs){
			if(boxs.isSelected()){
				//System.out.print("/"+boxs.getActionCommand()+"/");
			}else{
				
			}
			
		}
		
	}
	
	
	public void addCheckBox(JCheckBox checkBox){
		checkBox.addActionListener(this);
		checkboxs.add(checkBox);
	}
}
