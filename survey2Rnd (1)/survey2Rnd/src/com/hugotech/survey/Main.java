package com.hugotech.survey;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;

import com.hugotech.survey.listeners.ComponentFocusListener;
import com.hugotech.survey.listeners.EnterKeyListener;

public class Main extends JFrame implements ActionListener{

	Container container = getContentPane();
	public static ContentPanel contentPanel = new ContentPanel();
	public static ButtonPanel buttonPanel = null;
	public static GetData getData = null;
	
	int inset = 50;
	String language = null;
	private String groupName;
	
	
	public Main(HashMap<String, String> mapList) throws Exception{
	/*	super("survey frame");
		container.setLayout(new  BorderLayout());
		container.add(buttonPanel = new ButtonPanel(),BorderLayout.SOUTH);
		container.add(contentPanel,BorderLayout.CENTER);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(inset, inset);
		setSize(size.width-(inset*2),size.height-(inset*2));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getData = new GetData();*/
		
		super("survey frame");
		this.language=mapList.get("language");
		this.groupName=mapList.get("groupName");
		container.setLayout(new  BorderLayout());
		container.add(buttonPanel = new ButtonPanel(),BorderLayout.SOUTH);
		container.add(contentPanel,BorderLayout.CENTER);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(inset, inset);
		setSize(size.width-(inset*2),size.height-(inset*2));		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		getData = new GetData(language,groupName);
		ButtonPanel.nextButton.doClick();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	/*public static void main(String[] args)throws Exception{
		new Main();
	}*/
}
