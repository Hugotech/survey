package com.hugotech.survey;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.hugotech.survey.listeners.EnterKeyListener;

public class Decorator extends JPanel{

	EnterKeyListener enterKeyListener = null; 
	
	public Decorator() {
		setLayout(null);
		setBackground(Color.yellow);
		this.addKeyListener(enterKeyListener = new EnterKeyListener());
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		requestFocusInWindow();
	}
}

