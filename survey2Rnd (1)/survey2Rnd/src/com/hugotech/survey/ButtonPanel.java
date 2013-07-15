package com.hugotech.survey;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.hugotech.survey.listeners.CheckBoxListener;
import com.hugotech.survey.listeners.EnterKeyListener;
import com.hugotech.survey.listeners.LongTextListener;
import com.hugotech.survey.listeners.RadioListener;
import com.hugotech.survey.listeners.TextListener;

public class ButtonPanel extends JPanel implements ActionListener,FocusListener{

	
	public static JButton nextButton = null;
	public static JButton previousButton = null;
	public DataStore dataStore = null;
	public ClassCount classCount = null;
	public EnterKeyListener enterKeyListener = null;
	public volatile int i=0;
	
	List<JComponent> components = null;
	
	public ButtonPanel() {
		super();
		this.setLayout(new BorderLayout());
		nextButton = new JButton("Next >");
		previousButton = new JButton("< Previous");
		this.add(nextButton,BorderLayout.EAST);
		this.add(previousButton,BorderLayout.WEST);
		this.setBackground(Color.gray);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);
		nextButton.addFocusListener(this);
		previousButton.addFocusListener(this);
		nextButton.addKeyListener(enterKeyListener);
		previousButton.addKeyListener(enterKeyListener);
		nextButton.setBackground(Color.white);
		previousButton.setBackground(Color.white);
		components = new ArrayList<JComponent>();
		dataStore = new DataStore();
		classCount = new ClassCount();
		//previousButton.setVisible(false);
		enterKeyListener = new EnterKeyListener();
		nextButton.addKeyListener(new EnterKeyListener());
	}
	
	
	@Override
	public void focusGained(FocusEvent e) {
		JButton b = (JButton)e.getSource();
		b.setBackground(Color.red);
		
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		JButton b = (JButton)e.getSource();
		b.setBackground(Color.white);
		
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==nextButton){
			Decorator p1 = null;
	
			if(i!=0){
			dataStore.store(i);
			}
			
		
			
			
			CheckBoxListener.checkboxs.clear();
			RadioListener.radiobuttons.clear();
			TextListener.textFields.clear();
			
			
			Vector<Questions> questions = Main.getData.getQuestionData(++i>classCount.getI()?classCount.getI():i);
			Main.getData.getAnswerData(questions);
			p1 = GetData.getDecorator();
		
			
			
			Main.contentPanel.removeAll();
			Main.contentPanel.revalidate();
			Main.contentPanel.add(p1);
		
			
			
		}else if(e.getSource() == previousButton){
			Decorator p1 = null;
			if(i>classCount.getI()){
				i=classCount.getI();
			}
			Vector<Questions> questions  = Main.getData.getQuestionData(--i<1?1:i);
			Vector<Answers> answers = Main.getData.getAnswerData(questions);
			p1 = GetData.getDecorator();
			Main.contentPanel.removeAll();
			Main.contentPanel.revalidate();
			Main.contentPanel.add(p1);
			EditSurveyDetail.store(i);
			if(i<1){
				i=1;
			}
			
		}
	}
	
	
}


/*
 * 	if(!textFields.isEmpty()){
				for(JTextField tf:textFields){
				
					if(!(tf.getText()).isEmpty()){
						textFieldData.add(tf.getText());
						if(!checkboxes.isEmpty()){
							for(JCheckBox cb:checkboxes){
								if(cb.isSelected()){
									if(cb.getActionCommand().toUpperCase().contains("SPECIFY"))
									dataStore.storeTextField(tf.getText(),i,cb.getActionCommand());
									cb.setSelected(false);
								}
							}
							//checkboxes.clear();
						}
						else if(!radiobuttons.isEmpty()){
							for(JRadioButton rb:radiobuttons){
								if(rb.isSelected()){
									if(rb.getActionCommand().toUpperCase().contains("SPECIFY"))
									dataStore.storeTextField(tf.getText(),i,rb.getActionCommand());
									rb.setSelected(false);
								}
							}
							//radiobuttons.clear();
						}
						else
							dataStore.storeTextField(tf.getText(),i,"null");
							
					}
				
				}
			}
 * */
