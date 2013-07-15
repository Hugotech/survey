package com.hugotech.survey;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.hugotech.survey.listeners.EnterKeyListener;

public class ContentPanel extends JPanel{
	
	
	public ContentPanel() {
		super();
		setLayout(new BorderLayout());
		this.setBackground(Color.DARK_GRAY);
		addKeyListener(new EnterKeyListener());
	}
}
