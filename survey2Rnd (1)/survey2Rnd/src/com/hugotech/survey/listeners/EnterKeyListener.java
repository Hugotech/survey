package com.hugotech.survey.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.hugotech.survey.ButtonPanel;
public class EnterKeyListener implements KeyListener {

	
	JCheckBox checkBox = null;
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			JComponent com = (JComponent)e.getSource();
			if(com instanceof JCheckBox){
				JCheckBox box = (JCheckBox)com;
				box.transferFocus();
			}else if(com instanceof JTextField){
				JTextField field = (JTextField)com;
				field.transferFocus();
			}else if(com instanceof JRadioButton){
				JRadioButton radioButton = (JRadioButton)com;
				radioButton.transferFocus();
			}
			
			System.out.println("VK_DOWN");
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			JComponent com = (JComponent)e.getSource();
			if(com instanceof JCheckBox){
				JCheckBox box = (JCheckBox)com;
				box.transferFocusBackward();
			}else if(com instanceof JTextField){
				JTextField field = (JTextField)com;
				field.transferFocusBackward();
			}else if(com instanceof JRadioButton){
				JRadioButton radioButton = (JRadioButton)com;
				radioButton.transferFocusBackward();
			}
			System.out.println("VK_UP");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			ButtonPanel.nextButton.doClick();
			System.out.println("VK_ENTER");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
