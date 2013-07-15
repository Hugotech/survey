package com.hugotech.survey;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Experiment extends JFrame implements ActionListener,ChangeListener,KeyListener,FocusListener{

	
	JCheckBox cb1,cb2,cb3,cb4;
	JTextField tf1,tf2,tf3,tf4;
	
	JCheckBox[] cb = new JCheckBox[20];
	JTextField[] tf = new JTextField[20];
	
	int x = 100;
	int y = 100;
	int width = 100;
	int height = 20;
	
	Map<JCheckBox, JTextField> map = new HashMap<>();
	
	JPanel panel = new JPanel();
	
	
	public Experiment() {
		
		panel.setLayout(null);
		for(int i=0;i<cb.length;i++){
			cb[i] = new JCheckBox("No "+i);
			cb[i].setBounds(x, y+=25, width,height);
			System.out.println(x+" "+y);
			cb[i].addActionListener(this);
			cb[i].addChangeListener(this);
			panel.add(cb[i]);
			tf[i] = new JTextField(15);
			tf[i].setBounds(x+250,y,width,height);
			tf[i].setEditable(false);
			panel.add(tf[i]);
			map.put(cb[i], tf[i]);
			System.out.println((x+250)+" "+y);
			cb[i].addKeyListener(this);
			tf[i].addKeyListener(this);
			tf[i].addFocusListener(this);
			cb[i].addFocusListener(this);
			
		}
		
		getContentPane().add(panel,BorderLayout.CENTER);
		  setSize(1000,500);
		  setVisible(true);
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getSource());
		
	}





	@Override
	public void stateChanged(ChangeEvent e) {
		JCheckBox cb = (JCheckBox)e.getSource();
		if(cb.isSelected()){
			JTextField tf = (JTextField)map.get(cb);
			tf.setEditable(true);
		}
		if(!cb.isSelected()){
			JTextField tf = (JTextField)map.get(cb);
			tf.setEditable(false);
		}
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_DOWN){
			JComponent com = (JComponent)e.getSource();
			if(com instanceof JCheckBox){
				JCheckBox box = (JCheckBox)com;
				box.transferFocus();
			}else if(com instanceof JTextField){
				JTextField field = (JTextField)com;
				field.transferFocus();
			}
			System.out.println("String:  "+com.toString());
		}
		else if(e.getKeyCode() == KeyEvent.VK_UP){
			JComponent com = (JComponent)e.getSource();
			if(com instanceof JCheckBox){
				JCheckBox box = (JCheckBox)com;
				box.transferFocusBackward();
			}else if(com instanceof JTextField){
				JTextField field = (JTextField)com;
				field.transferFocusBackward();
			}
			System.out.println("String:  "+com.toString());
		}else{
			
		}
	}




	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		JComponent com = (JComponent)e.getSource();
		com.setBackground(Color.gray);
	}




	@Override
	public void focusLost(FocusEvent e) {
		JComponent com = (JComponent)e.getSource();
		com.setBackground(Color.white);
		
	}
	
}

