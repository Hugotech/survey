package com.hugotech.survey.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LongTextListener implements KeyListener{

	public static Vector<JTextArea> textAreas= new Vector<JTextArea>();
	
	public LongTextListener() {
		
	}
	
	public void addTextArea(JTextArea textArea){
		textArea.addKeyListener(this);
		textAreas.add(textArea);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_TAB){
			JTextArea area = (JTextArea)e.getSource();
			area.transferFocus();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}


