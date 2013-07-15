package com.hugotech.survey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EditSurveyDetail {
	
	static List<String> list = null;
	
	static String answerType = "";
	static String answerText = "";
	
	public static void store(int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(DataStore.load());
			statement = connection.prepareStatement("select answer_type from questions where question_id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				answerType = resultSet.getString("answer_type");
				//EditSurveyDetail.answerType = answerType;
			}
			
			
			statement = connection.prepareStatement("select answer_text from response where question_id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			while(resultSet.next()){
				answerText = resultSet.getString("answer_text");
				if(answerType.equals("CHOOSE_SINGLE_OTHER")){
					CHOOSE_SINGLE_OTHER(id,answerText);
				}else if(answerType.equals("CHOOSE_SINGLE_TEXT")){
					CHOOSE_SINGLE_TEXT(id,answerText);
				}else if(answerType.equals("CHOOSE_MULTIPLE_OTHER")){
					CHOOSE_MULTIPLE_OTHER(id,answerText);
				}else if(answerType.equals("CHOOSE_MULTIPLE_TEXT")){
					CHOOSE_MULTIPLE_TEXT(id,answerText);
				}else if(answerType.equals("CHOOSE_SINGLE")){
					CHOOSE_SINGLE(id,answerText);
				}else if(answerType.equals("CHOOSE_MULTIPLE")){
					CHOOSE_MULTIPLE(id,answerText);
				}else if(answerType.equals("NUMERIC")){
					NUMERIC(id,answerText);
				}else if(answerType.equals("TEXT")){
					TEXT(id,answerText);
				}else if(answerType.equals("LONG_TEXT")){
					LONG_TEXT(id,answerText);
				}
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(resultSet!=null)
					resultSet.close();
				if(statement!=null) 
					statement.close();
				if(connection!=null)
					connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	
	
	
	
	
	public static void CHOOSE_SINGLE_OTHER(int id, String answerText){
		for(Map.Entry<JRadioButton, JTextField> map:Main.getData.CHOOSE_SINGLE_OTHER.entrySet()){	
			JRadioButton rb = (JRadioButton)map.getKey();
			JTextField field = Main.getData.CHOOSE_SINGLE_OTHER.get(rb);
			
			
			if(rb.getActionCommand().equals(answerText)){
				rb.setSelected(true);
				rb.requestFocus();
				break;
			}else if(field!=null){
				field.setText(answerText);
				field.setEditable(true);
				field.requestFocus();
				rb.setSelected(true);
			}	
			
		}

		
	}
	
	
	public static void CHOOSE_SINGLE_TEXT(int id, String answerText) {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		String choiceText = "";
		try{
			connection = DriverManager.getConnection(DataStore.load());
			statement = connection.prepareStatement("select choice_text from answers where answer_id = (select answer_id from response where question_id = ? and answer_text = ?)");
			statement.setInt(1, id);
			statement.setString(2, answerText);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				choiceText = resultSet.getString("choice_text");
			}

			resultSet.close();
			statement.close();
			connection.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(resultSet!=null)
					resultSet.close();
				if(statement!=null)
					statement.close();
				if(connection!=null)
					connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		for(Map.Entry<JRadioButton, JTextField> map:Main.getData.CHOOSE_SINGLE_TEXT.entrySet()){
			JRadioButton rb = (JRadioButton)map.getKey();
			JTextField field = Main.getData.CHOOSE_SINGLE_TEXT.get(rb);
			if(rb.getActionCommand().equalsIgnoreCase(choiceText)){
				rb.setSelected(true);
				rb.requestFocus();
				if(field!=null){
				field.setText(answerText);
				field.setEditable(true);
				field.requestFocus();
				}
				
			}
		}
		
	}

	public static void CHOOSE_MULTIPLE_OTHER(int id, String answerText) {
		
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		String choiceText = "";
		try{
			connection = DriverManager.getConnection(DataStore.load());
			statement = connection.prepareStatement("select choice_text from answers where answer_id = (select answer_id from response where question_id = ? and answer_text = ?)");
			statement.setInt(1, id);
			statement.setString(2, answerText);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				choiceText = resultSet.getString("choice_text");
			}

			resultSet.close();
			statement.close();
			connection.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(resultSet!=null)
					resultSet.close();
				if(statement!=null)
					statement.close();
				if(connection!=null)
					connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		
		for(Map.Entry<JCheckBox, JTextField> map:Main.getData.CHOOSE_MULTIPLE_OTHER.entrySet()){
			JCheckBox cb = (JCheckBox)map.getKey();
			JTextField field = Main.getData.CHOOSE_MULTIPLE_OTHER.get(cb);
			if(cb.getActionCommand().equalsIgnoreCase(choiceText)){
				cb.setSelected(true);
				cb.requestFocus();
				if(field!=null){
				field.setText(answerText);
				field.setEditable(true);
				field.requestFocus();
				}
				
			}
			
		}
		
	}

	public static void CHOOSE_MULTIPLE_TEXT(int id, String answerText) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		String choiceText = "";
		try{
			connection = DriverManager.getConnection(DataStore.load());
			statement = connection.prepareStatement("select choice_text from answers where answer_id = (select answer_id from response where question_id = ? and answer_text = ?)");
			statement.setInt(1, id);
			statement.setString(2, answerText);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				choiceText = resultSet.getString("choice_text");
			}

			resultSet.close();
			statement.close();
			connection.close();
			
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(resultSet!=null)
					resultSet.close();
				if(statement!=null)
					statement.close();
				if(connection!=null)
					connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		
		for(Map.Entry<JCheckBox, JTextField> map:Main.getData.CHOOSE_MULTIPLE_TEXT.entrySet()){
			JCheckBox cb = (JCheckBox)map.getKey();
			JTextField field = Main.getData.CHOOSE_MULTIPLE_TEXT.get(cb);
			if(cb.getActionCommand().equalsIgnoreCase(choiceText)){
				cb.setSelected(true);
				cb.requestFocus();
				if(field!=null){
				field.setText(answerText);
				field.setEditable(true);
				field.requestFocus();
				}
				
			}
			
		}
		
		
			
			
		
		
	}

	public static void CHOOSE_SINGLE(int id, String answerText) {
			for(JRadioButton rb:Main.getData.CHOOSE_SINGLE){
				if(rb.getActionCommand().equalsIgnoreCase(answerText)){
					rb.setSelected(true);
					rb.requestFocus();
				}
			}
	
	}

	public static void CHOOSE_MULTIPLE(int id, String answerText) {
		for(JCheckBox cb:Main.getData.CHOOSE_MULTIPLE){
			if(cb.getActionCommand().equalsIgnoreCase(answerText)){
				cb.setSelected(true);
				cb.requestFocus();
			}
		}
		
	}

	public static void NUMERIC(int id, String answerText) {
		for(JTextField tf:Main.getData.NUMERIC){
			tf.setText(answerText);
			tf.requestFocus();
		}
		
	}

	public static void TEXT(int id, String answerText) {
		for(JTextField tf:Main.getData.TEXT){
			tf.setText(answerText);
			tf.requestFocus();
		}
		
	}
	
	public static void LONG_TEXT(int id, String answerText){
		for(JTextArea ta:Main.getData.LONG_TEXT){
			ta.setText(answerText);
			ta.requestFocus();
		}
	}
	

}

