package com.hugotech.survey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class DataStore {

	static Connection connection = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	static String url1 = "jdbc:sqlite:C:/survey/survey";
	static String url2 = "jdbc:sqlite:D:/survey/surveyhindi";
	static String url3 = "jdbc:sqlite:D:/survey/surveyfinal";
	static String url4 = "jdbc:sqlite:C:/survey/surveyhindi";
	private String answerType;
	
			/*"jdbc:sqlite:D:/survey/surveyhindi"*/
	public DataStore() {
		try{
			Class.forName("org.sqlite.JDBC");			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	

	public static String load(){
		return url1;
	}
	
	
	
	public static int getQuestionCount(){
		int i = 0;
		try{
			connection = DriverManager.getConnection(load());
			preparedStatement = connection.prepareStatement("select count(question_id) from questions");
			resultSet = preparedStatement.executeQuery();
			i = resultSet.getInt(1);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				if(connection!=null){
					connection.close();
				}if(preparedStatement!=null){
					preparedStatement.close();
				}if(resultSet!=null){
					resultSet.close();
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
		return i;
	}



	public void store(int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try{
			connection = DriverManager.getConnection(load());
			statement = connection.prepareStatement("select answer_type from questions where question_id = ?");
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			String answerType = null;
			if(resultSet.next()){
				answerType = resultSet.getString(1);
				this.answerType = answerType;
			}
			
			resultSet.close();
			statement.close();
			connection.close();
			
			if(answerType.equals("CHOOSE_SINGLE_OTHER")){
				Main.getData.CHOOSE_SINGLE_OTHER(id);
			}else if(answerType.equals("CHOOSE_SINGLE_TEXT")){
				Main.getData.CHOOSE_SINGLE_TEXT(id);
			}else if(answerType.equals("CHOOSE_MULTIPLE_OTHER")){
				Main.getData.CHOOSE_MULTIPLE_OTHER(id);
			}else if(answerType.equals("CHOOSE_MULTIPLE_TEXT")){
				Main.getData.CHOOSE_MULTIPLE_TEXT(id);
			}else if(answerType.equals("CHOOSE_SINGLE")){
				Main.getData.CHOOSE_SINGLE(id);
			}else if(answerType.equals("CHOOSE_MULTIPLE")){
				Main.getData.CHOOSE_MULTIPLE(id);
			}else if(answerType.equals("NUMERIC")){
				Main.getData.NUMERIC(id);
			}else if(answerType.equals("TEXT")){
				Main.getData.TEXT(id);
			}else if(answerType.equals("LONG_TEXT")){
				Main.getData.LONG_TEXT(id);
			}
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

	public static void insertChooseSingleOtherData(String text, String answerChoice, int id) {
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, answerChoice);
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,text);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			////JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}

	public static void insertChooseSingleTextData(String text, String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, answerChoice);
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,text);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}

		
	}

	public static void insertChooseMultipleOtherData(String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, answerChoice);
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,answerChoice);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
	
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}

	public static void insertChooseMultipleOtherTextData(String text, String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, answerChoice);
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,text);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	
	public static void insertChooseSingle(String answerChoice, int id){
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, answerChoice);
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,answerChoice);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}

	public static void insertChooseMultiple(String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}

			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, answerChoice);
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,answerChoice);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}

	public static void insertText(String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2,"");
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,answerChoice);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	
	public static void insertNumeric(String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ? and choice_text = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2,"");
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,answerChoice);
			statement.setString(4,new Date().toString());
			int i = statement.executeUpdate();
			//JOptionPane.showMessageDialog(new JFrame(),""+i);
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	public static void insertLongText(String answerChoice, int id) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		
		try{
			connection = DriverManager.getConnection(load());
			String sql = "";
			if(Main.getData.language.equalsIgnoreCase("hindi")){
				sql = "select answer_id from answers where question_id = ? and choice_hindi = ?";
			}
			else if(Main.getData.language.equalsIgnoreCase("orissa")){
				sql = "select answer_id from answers where question_id = ? and choice_orissa = ?";
			}else{
				sql = "select answer_id from answers where question_id = ?";
			}
			
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			
			
			resultSet = statement.executeQuery();
			int answerId = 0;
			if(resultSet.next()){
				answerId = resultSet.getInt("answer_id");
				System.out.println("answerID"+answerId);
			}
			resultSet.close();
			statement.close();
			
			statement = connection.prepareStatement("insert into response(question_id,answer_id,answer_text,created_date) values(?,?,?,?)");
			statement.setInt(1, id);
			statement.setInt(2, answerId);
			statement.setString(3,answerChoice);
			statement.setString(4,new java.util.Date().toString());
			int i = statement.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e);
		}finally{
			try{
				resultSet.close();
				statement.close();
				connection.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		
	}
	
	
}
