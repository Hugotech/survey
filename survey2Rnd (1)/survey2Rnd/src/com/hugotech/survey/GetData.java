package com.hugotech.survey;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.xml.crypto.Data;

import com.hugotech.survey.exception.RecordNotFoundException;
import com.hugotech.survey.listeners.CheckBoxListener;
import com.hugotech.survey.listeners.ChooseMultipleTextChangeListener;
import com.hugotech.survey.listeners.ChooseMultipleWithOtherChangeListener;
import com.hugotech.survey.listeners.ChooseSingleTextChangeListener;
import com.hugotech.survey.listeners.ComponentFocusListener;
import com.hugotech.survey.listeners.EnterKeyListener;
import com.hugotech.survey.listeners.LongTextListener;
import com.hugotech.survey.listeners.RadioListener;
import com.hugotech.survey.listeners.SingleOtherChangeListener;
import com.hugotech.survey.listeners.TextListener;

public class GetData {

	String choiceText = null;
	String questionText = null;
	String language = null;
	private static Font fontHindi = new Font("Kruti Dev 016", Font.PLAIN, 25);
	Connection con = null;
	PreparedStatement stm = null;
	ResultSet rs = null;
	
	JLabel questionLabel = null;
	
	static Decorator decorator = null;
	
	Vector<JLabel> labels;
	Vector<JTextField> textFields;
	
	Vector<Questions> questions;
	Vector<Answers> answers;
	ButtonGroup buttonGroup = null;
	
	EnterKeyListener enterKeyListener = null;
	CheckBoxListener boxListener = null;
	ComponentFocusListener componentFocusListener = null;
	RadioListener radioListener = null;
	TextListener textListener = null;
	LongTextListener longTextListener = null;
	
	SingleOtherChangeListener singleOtherChangeListener = null;
	ChooseSingleTextChangeListener chooseSingleTextChangeListener = null;
	ChooseMultipleWithOtherChangeListener chooseMultipleWithOtherChangeListener = null; 
	ChooseMultipleTextChangeListener chooseMultipleTextChangeListener = null;
	
	
	int width = 100;
	int height = 20;
	int x=100;
	int y=100;
	
	boolean flagForCheckBox = true;
	
	static String questionDataQuery = null;//"select * from questions where question_id = ?";
	static String answerDataQuery = "select * from answers where question_id = ?";
	
	Vector<JCheckBox> CHOOSE_MULTIPLE = null;
	Map<JRadioButton, JTextField> CHOOSE_SINGLE_OTHER = null;
	Map<JRadioButton, JTextField> CHOOSE_SINGLE_TEXT = null;
	Map<JCheckBox, JTextField> CHOOSE_MULTIPLE_OTHER = null;
	Map<JCheckBox, JTextField> CHOOSE_MULTIPLE_TEXT = null;
	Vector<JRadioButton> CHOOSE_SINGLE = null;
	Vector<JTextField> TEXT = null;
	Vector<JTextField> NUMERIC = null;
	Vector<JTextArea> LONG_TEXT = null;
	
	
	private String groupName;
	
	
	
	public GetData(String language, String groupName) throws Exception{
		this.language= language; 
		this.groupName = groupName;
		if(language.equalsIgnoreCase("hindi")){
			choiceText = "choice_hindi";
			questionText = "question_hindi";
		}else if(language.equalsIgnoreCase("orissa")){
			choiceText = "choice_orissa";
			questionText = "question_orissa";
		}else{
			choiceText = "choice_text";
			questionText = "question_text";
		}
		decorator = new Decorator();
		questions = new Vector<Questions>();
		answers = new Vector<Answers>();
		labels = new Vector<JLabel>();
		textFields = new Vector<JTextField>();
		
		
        if(language.equalsIgnoreCase("Hindi")){	
			questionDataQuery="SELECT * FROM questions where question_id = ? and question_group = '"+groupName+"'";
		}
		else if(language.equalsIgnoreCase("orissa")){
			questionDataQuery="SELECT * FROM questions where question_id = ? and question_group = '"+groupName+"'";
		}
		else if(language.equalsIgnoreCase("English")) {
			questionDataQuery="SELECT * FROM questions where question_id = ? and question_group = '"+groupName+"'";
		}
		else{
			return;
		}

	}
	
	public void reset(){
		
		decorator.removeAll();
		decorator = null;
		questions = null;
		answers = null;
		labels = null;
		textFields = null;
		buttonGroup = null;
		boxListener = null;
		enterKeyListener = null;
		componentFocusListener = null;
		
		
		
		radioListener = null;
		textListener = null;
		longTextListener = null;
		
		CHOOSE_MULTIPLE = null;
		CHOOSE_SINGLE_OTHER = null;
		CHOOSE_SINGLE_TEXT = null;
		CHOOSE_MULTIPLE_OTHER = null;
		CHOOSE_MULTIPLE_TEXT = null;
		CHOOSE_SINGLE = null;
		CHOOSE_MULTIPLE = null;
		TEXT = null;
		NUMERIC = null;
		LONG_TEXT = null;
		
		singleOtherChangeListener = null;
		chooseSingleTextChangeListener = null;
		chooseMultipleWithOtherChangeListener = null;
		chooseMultipleTextChangeListener = null;
		
		
		
		decorator = new Decorator();
		questions = new Vector<Questions>();
		answers = new Vector<Answers>();
		labels = new Vector<JLabel>();
		textFields = new Vector<JTextField>();
		buttonGroup = new ButtonGroup();
		boxListener = new CheckBoxListener();
		enterKeyListener = new EnterKeyListener();
		componentFocusListener = new ComponentFocusListener();
		
		
		radioListener = new RadioListener(); 
		textListener = new TextListener();
		longTextListener = new LongTextListener();
		
		

		CHOOSE_SINGLE_OTHER = new HashMap<JRadioButton, JTextField>();
		CHOOSE_SINGLE_TEXT = new HashMap<JRadioButton, JTextField>();
		CHOOSE_MULTIPLE_OTHER = new HashMap<JCheckBox,JTextField>();
		CHOOSE_MULTIPLE_TEXT = new HashMap<JCheckBox,JTextField>();
		CHOOSE_SINGLE = new Vector<JRadioButton>();
		CHOOSE_MULTIPLE = new Vector<JCheckBox>();
		
		TEXT = new Vector<JTextField>();
		NUMERIC = new Vector<JTextField>();
		LONG_TEXT = new Vector<JTextArea>();
		
		singleOtherChangeListener = new SingleOtherChangeListener(CHOOSE_SINGLE_OTHER);
		chooseSingleTextChangeListener = new ChooseSingleTextChangeListener(CHOOSE_SINGLE_TEXT);
		chooseMultipleWithOtherChangeListener = new ChooseMultipleWithOtherChangeListener(CHOOSE_MULTIPLE_OTHER);
		chooseMultipleTextChangeListener = new ChooseMultipleTextChangeListener(CHOOSE_MULTIPLE_TEXT);
		
		
		stm = null;
		rs = null;
		
		setWidth(100);
		setHeight(20);
		setX(100);
		setY(100);
		setFlagForCheckBox(true);
	}
	public Vector<Questions> getQuestionData(int questionId){
			reset();
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(DataStore.load());
			
			stm = con.prepareStatement(questionDataQuery);
			stm.setInt(1, questionId);
			rs = stm.executeQuery();
			while(rs.next()){
				questions.add(new Questions(rs.getInt("question_id"), rs.getInt("question_seq"), rs.getInt("question_subseq"), rs.getString("answer_type"), rs.getString(questionText)));
				createQuestionLabel(questions);
			}
		}catch(Exception e){
			System.out.println("getData: "+e);
		}finally{
			try{
			rs.close();
			stm.close();
			con.close();
			}catch(Exception e){
				System.out.println("finally block: "+e);
			}
		}
		return questions;
	}
	
	public Vector<Answers> getAnswerData(Vector<Questions> questions){
		try{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(DataStore.load());
			stm = con.prepareStatement(answerDataQuery);
			if(!questions.isEmpty()){
				for(int i=0;i<questions.size();i++){
				stm.setInt(1, questions.get(i).getQuestionId());
				rs = stm.executeQuery();
				while(rs.next()){
					answers.add(new Answers(rs.getInt("answer_id"), rs.getInt("question_id"), rs.getInt("choice_id"), rs.getString(choiceText), rs.getInt("choice_value")));
					
				}
			}
				for(int i=0;i<answers.size();i++){
					checkForAnswerType(answers.get(i).getChoiceId(),questions.get(0).getAnswerType(),answers.get(i).getChoiceText(),answers.size(),i);
				}
			}else{
				throw new RecordNotFoundException("no records found");
			}			
		}catch(Exception e){
			JOptionPane.showMessageDialog(new JFrame(), "No data !", "Error", JOptionPane.ERROR_MESSAGE);
		}finally{
			try{
			rs.close();
			stm.close();
			con.close();
			}catch(Exception e){
				System.out.println("finally block: "+e);
			}
		}
		return answers;
	}
	
	
	public void checkForAnswerType(int choiceId, String answerType, String answerText, int size, int currentIndex){
	
		if(answerType.equals("TEXT")){
			text();
		}
		if(answerType.equals("NUMERIC")){
			numeric();
		}	
		if(answerType.equals("CHOOSE_MULTIPLE")){
			chooseMultiple(choiceId,answerText);
		}	
	
		if(answerType.equals("CHOOSE_SINGLE")){
			chooseSingle(choiceId,answerText);
		}
		if(answerType.equals("CHOOSE_SINGLE_OTHER")){
			choosingSingleOther(choiceId,answerText,size,currentIndex);
		}
		if(answerType.equals("CHOOSE_SINGLE_TEXT")){
			chooseSingleText(choiceId,answerText);
		}
		if(answerType.equals("CHOOSE_MULTIPLE_OTHER")){
			chooseMultipleWithOther(choiceId,answerText,size,currentIndex);
		}
		if(answerType.equals("CHOOSE_MULTIPLE_TEXT")){
			chooseMultipleText(choiceId,answerText);
		}
		if(answerType.equals("LONG_TEXT")){
			longText();
		}
		
	}
	
	

	public void createQuestionLabel(Vector<Questions> questions){
		int quesTionNumber = questions.get(0).getQuestionId();
		String questionText = questions.get(0).getQuestionText();
		if(questionText==null){
			JOptionPane.showMessageDialog(new JFrame(), "No Data in Question table", "No Data Error", JOptionPane.ERROR_MESSAGE);
				return;
		}
		if(language.equalsIgnoreCase("hindi")){
			questionLabel = new JLabel("iz'u "+quesTionNumber+" ]  "+questionText+"");
			questionLabel.setFont(fontHindi);
		}
		else{
			questionLabel = new JLabel("Q"+quesTionNumber+") "+questionText);
		}
		
		//JLabel questionLabel = new JLabel("Q"+quesTionNumber+") "+questionText);
		int lengthOfString = (questionText.length()+3)*20;
		questionLabel.setBounds(100, 100, lengthOfString, height);//(int)questionLabel.getSize().getWidth()
		questionLabel.setBackground(Color.yellow);
		questionLabel.setForeground(Color.blue);
		questionLabel.setOpaque(true);
		decorator.add(questionLabel);
		
	
	}
	
	private void chooseMultipleText(int choiceId, String answerText){
		for(int i=0;i<1;i++){
			
			JCheckBox button = new JCheckBox(answerText);
			width = answerText.length()+5*20;
			button.setBounds(x,y+=20,width,height);
			button.setBackground(Color.yellow);
			button.addKeyListener(enterKeyListener);
			button.addFocusListener(componentFocusListener);
			boxListener.addCheckBox(button);
			decorator.add(button);
			button.addChangeListener(chooseMultipleTextChangeListener);
			
			if(language.equalsIgnoreCase("hindi")){
				button.setFont(fontHindi);
			}
			
			/*if(flagForCheckBox){
				button.setSelected(true);
				flagForCheckBox=false;
			}*/
			for(int j=0;j<1;j++){
				JTextField field = new JTextField();
				field.setBackground(Color.white);
				field.setBounds(x+200,y,200,18);
				field.addKeyListener(enterKeyListener);
				field.addFocusListener(componentFocusListener);
				field.setEditable(false);
				textListener.addTextField(field);
				decorator.add(field);
				CHOOSE_MULTIPLE_TEXT.put(button, field);
				
				
			}
		}
		
	}
	
	
	private void chooseMultipleWithOther(int choiceId, String answerText, int size, int currentIndex) {
		
		JCheckBox checkBox = new JCheckBox(answerText);
		width = answerText.length()+12*20;
		checkBox.setBounds(x,y+=20,width,height);
		checkBox.addKeyListener(enterKeyListener);
		checkBox.addFocusListener(componentFocusListener);
		checkBox.addChangeListener(chooseMultipleWithOtherChangeListener);
		if(language.equalsIgnoreCase("hindi")){
			checkBox.setFont(fontHindi);
		}
		/*if(flagForCheckBox){
			checkBox.setSelected(true);
			flagForCheckBox=false;
		}*/
		boxListener.addCheckBox(checkBox);
		decorator.add(checkBox);
		checkBox.setBackground(Color.yellow);
		
		CHOOSE_MULTIPLE_OTHER.put(checkBox, null);
		
		if(currentIndex==size-1){
			JTextField textField = new JTextField(100);
			textField.setBounds(width+x+25,y,200,18);
			textField.addKeyListener(enterKeyListener);
			decorator.add(textField);
			textField.setBackground(Color.white);
			textField.addFocusListener(componentFocusListener);	
			textField.setEditable(false);
			textListener.addTextField(textField);
			CHOOSE_MULTIPLE_OTHER.put(checkBox, textField);
			return;
		}
	}
	
	
	private void chooseSingleText(int choiceId, String answerText){
		//for(int i=0;i<1;i++){
			JRadioButton button = new JRadioButton(answerText);
			width = answerText.length()+5*20;
			button.setBounds(x,y+=20,width,height);
			button.setBackground(Color.yellow);
			button.addKeyListener(enterKeyListener);
			button.addFocusListener(componentFocusListener);
			decorator.add(button);
			buttonGroup.add(button);
			button.addChangeListener(chooseSingleTextChangeListener);
			if(language.equalsIgnoreCase("hindi")){
				button.setFont(fontHindi);
			}
			radioListener.addRadioButton(button);
			/*if(flagForCheckBox){
				button.setSelected(true);
				flagForCheckBox=false;
			}*/
			//for(int j=0;j<1;j++){
				JTextField field = new JTextField();
				field.setBackground(Color.white);
				field.setBounds(x+200,y,200,18);
				field.addKeyListener(enterKeyListener);
				field.addFocusListener(componentFocusListener);
				field.setEditable(false);
				
				textListener.addTextField(field);
				decorator.add(field);
				
			//}
			//CHOOSE_SINGLE_TEXT.put(button, field);
		//}
		CHOOSE_SINGLE_TEXT.put(button, field);
		//System.out.println(CHOOSE_SINGLE_TEXT);
		
	}
	

	
	
	private void choosingSingleOther(int choiceId, String answerText, int size, int currentIndex) {
		
		/*chooseSingle(choiceId, answerText);*/
		width = answerText.length()+10*20;
		JRadioButton radioButton = new JRadioButton(answerText);
		radioButton.setBounds(x,y+=20,width,height);
		radioButton.addKeyListener(enterKeyListener);
		radioButton.addFocusListener(componentFocusListener);
		radioListener.addRadioButton(radioButton);
		radioButton.addChangeListener(singleOtherChangeListener);
		decorator.add(radioButton);
		if(language.equalsIgnoreCase("hindi")){
			radioButton.setFont(fontHindi);
		}
		radioButton.setBackground(Color.yellow);
		buttonGroup.add(radioButton);
		CHOOSE_SINGLE_OTHER.put(radioButton, null);
		if(currentIndex==size-1){
			JTextField textField = new JTextField(100);
			textField.setBounds(350,y,200,18);
			textField.addKeyListener(enterKeyListener);
			decorator.add(textField);
			textField.setBackground(Color.white);
			textField.addFocusListener(componentFocusListener);
			textField.setEditable(false);
			textListener.addTextField(textField);
			CHOOSE_SINGLE_OTHER.put(radioButton, textField);
		}
		
	}


	private void chooseMultiple(int choiceId, String answerText) {
		System.out.println(answerText);
		JCheckBox checkBox = new JCheckBox(answerText);
		width = answerText.length()+10*20;
		checkBox.setBounds(x,y+=20,width,height);
		checkBox.addKeyListener(enterKeyListener);
		checkBox.addFocusListener(componentFocusListener);
		
		if(language.equalsIgnoreCase("hindi")){
			checkBox.setFont(fontHindi);
		}
		
		/*if(flagForCheckBox){
			checkBox.setSelected(true);
			flagForCheckBox=false;
		}*/
		boxListener.addCheckBox(checkBox);
		decorator.add(checkBox);
		checkBox.setBackground(Color.yellow);
		CHOOSE_MULTIPLE.add(checkBox);
	}
	
	private void chooseSingle(int choiceId, String answerText){
		width = answerText.length()+10*20;
		JRadioButton radioButton = new JRadioButton(answerText);
		radioButton.setBounds(x,y+=20,width,height);
		radioButton.addKeyListener(enterKeyListener);
		radioButton.addFocusListener(componentFocusListener);
		radioListener.addRadioButton(radioButton);
		if(language.equalsIgnoreCase("hindi")){
			radioButton.setFont(fontHindi);
		}
		/*if(flagForCheckBox){
			radioButton.setSelected(true);
			flagForCheckBox=false;
		}*/
		decorator.add(radioButton);
		radioButton.setBackground(Color.yellow);
		buttonGroup.add(radioButton);
		CHOOSE_SINGLE.add(radioButton);
	}
	

	private void numeric() {
		
		JTextField textField = new JTextField(100);
		textField.setBounds(100,150,200,18);
		textField.addKeyListener(enterKeyListener);
		textField.addFocusListener(componentFocusListener);
		decorator.add(textField);
		textField.setBackground(Color.white);
		textListener.addTextField(textField);
		NUMERIC.add(textField);
		
	}


	private void text() {
		
		JTextField textField = new JTextField(100);
		textField.setBounds(100,150,200,18);
		textField.addKeyListener(enterKeyListener);
		decorator.add(textField);
		textField.setBackground(Color.white);
		textField.addFocusListener(componentFocusListener);
		textListener.addTextField(textField);
		TEXT.add(textField);
	}

	private void longText(){
		JTextArea textArea = new JTextArea();
		textArea.setBounds(100,150,300,200);
		textArea.addKeyListener(enterKeyListener);
		decorator.add(textArea);
		textArea.setBackground(Color.white);
		textArea.addFocusListener(componentFocusListener);
		longTextListener.addTextArea(textArea);
		LONG_TEXT.add(textArea);
		textArea.setBorder(new LineBorder(Color.black));
		textArea.requestFocus();
	}
	
	
	public void CHOOSE_SINGLE_OTHER(int id){
		for(Map.Entry<JRadioButton, JTextField> map:CHOOSE_SINGLE_OTHER.entrySet()){
			JRadioButton rb = (JRadioButton)map.getKey();
			if(rb.isSelected()){
				JTextField field = CHOOSE_SINGLE_OTHER.get(rb);
				if(field!=null){
					String text = field.getText();
					String answerChoice = rb.getActionCommand();
					DataStore.insertChooseSingleOtherData(text,answerChoice,id);
				}else{
					String answerChoice = rb.getActionCommand();
					DataStore.insertChooseSingle(answerChoice, id);
				}
			}
		}
	}
	
	
	public void CHOOSE_SINGLE_TEXT(int id) {
		for(Map.Entry<JRadioButton, JTextField> map:CHOOSE_SINGLE_TEXT.entrySet()){
			JRadioButton rb = (JRadioButton)map.getKey();
			if(rb.isSelected()){
				JTextField field = CHOOSE_SINGLE_TEXT.get(rb);
				if(field!=null){
					String text = field.getText();
					String answerChoice = rb.getActionCommand();
					DataStore.insertChooseSingleTextData(text,answerChoice,id);
				}else{
					String answerChoice = rb.getActionCommand();
					DataStore.insertChooseSingle(answerChoice, id);
				}
			}
		}
		
	}

	public void CHOOSE_MULTIPLE_OTHER(int id) {
		for(Map.Entry<JCheckBox, JTextField> map:CHOOSE_MULTIPLE_OTHER.entrySet()){
			JCheckBox rb = (JCheckBox)map.getKey();
			if(rb.isSelected()){
				JTextField field = CHOOSE_MULTIPLE_OTHER.get(rb);
				String text = null;
				String answerChoice = null;
				if(field!=null){
					text = field.getText();
					answerChoice = rb.getActionCommand();
					DataStore.insertChooseMultipleOtherTextData(text,answerChoice,id);
				} else{
					answerChoice = rb.getActionCommand();
					DataStore.insertChooseMultipleOtherData(answerChoice,id);
				}
				
			}
		}
		
	}

	public void CHOOSE_MULTIPLE_TEXT(int id) {
		for(Map.Entry<JCheckBox, JTextField> map:CHOOSE_MULTIPLE_TEXT.entrySet()){
			JCheckBox rb = (JCheckBox)map.getKey();
			if(rb.isSelected()){
				JTextField field = CHOOSE_MULTIPLE_TEXT.get(rb);
				String text = null;
				String answerChoice = null;
				if(field!=null){
					text = field.getText();
					answerChoice = rb.getActionCommand();
					DataStore.insertChooseMultipleOtherTextData(text,answerChoice,id);
				} else{
					answerChoice = rb.getActionCommand();
					DataStore.insertChooseMultipleOtherData(answerChoice,id);
				}
				
			}
		}
		
	}

	public void CHOOSE_SINGLE(int id) {
			
			for(JRadioButton rb:CHOOSE_SINGLE){
			if(rb.isSelected()){
					String answerChoice = rb.getActionCommand();
					DataStore.insertChooseSingle(answerChoice,id);
				
			
			}
	}
	
	}

	public void CHOOSE_MULTIPLE(int id) {
		for(JCheckBox cb:CHOOSE_MULTIPLE){
			if(cb.isSelected()){
				String answerChoice = cb.getActionCommand();
				System.out.println("Choice Answer : "+answerChoice);
				DataStore.insertChooseMultiple(answerChoice,id);
			}
		}
		
	}

	public void NUMERIC(int id) {
		for(JTextField tf:NUMERIC){
			if(tf.getText()!=null){
				String answerChoice = tf.getText();
				DataStore.insertNumeric(answerChoice,id);
			}
		}
		
	}

	public void TEXT(int id) {
		for(JTextField tf:TEXT){
			if(tf.getText()!=null){
				String answerChoice = tf.getText();
				DataStore.insertText(answerChoice,id);
			}
		}
		
	}
	
	public void LONG_TEXT(int id){
		for(JTextArea ta:LONG_TEXT){
			if(ta.getText()!=null){
				String answerChoice = ta.getText();
				DataStore.insertLongText(answerChoice,id);
			}
		}
	}
	

	public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}


	public PreparedStatement getStm() {
		return stm;
	}


	public void setStm(PreparedStatement stm) {
		this.stm = stm;
	}


	public ResultSet getRs() {
		return rs;
	}


	public void setRs(ResultSet rs) {
		this.rs = rs;
	}


	

	public Vector<Questions> getQuestions() {
		return questions;
	}


	public void setQuestions(Vector<Questions> questions) {
		this.questions = questions;
	}


	public Vector<Answers> getAnswers() {
		return answers;
	}


	public void setAnswers(Vector<Answers> answers) {
		this.answers = answers;
	}


	public static String getQuestiondataquery() {
		return questionDataQuery;
	}


	public static String getAnswerdataquery() {
		return answerDataQuery;
	}


	public Vector<JLabel> getLabels() {
		return labels;
	}


	public void setLabels(Vector<JLabel> labels) {
		this.labels = labels;
	}


	public Vector<JTextField> getTextFields() {
		return textFields;
	}


	public void setTextFields(Vector<JTextField> textFields) {
		this.textFields = textFields;
	}


	public static Decorator getDecorator() {
		return decorator;
	}


	public void setDecorator(Decorator decorator) {
		GetData.decorator = decorator;
	}

	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}

	public void setButtonGroup(ButtonGroup buttonGroup) {
		this.buttonGroup = buttonGroup;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isFlagForCheckBox() {
		return flagForCheckBox;
	}

	public void setFlagForCheckBox(boolean flagForCheckBox) {
		this.flagForCheckBox = flagForCheckBox;
	}

	
}
