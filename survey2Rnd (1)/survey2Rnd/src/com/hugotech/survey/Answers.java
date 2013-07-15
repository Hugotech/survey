package com.hugotech.survey;

public class Answers {

	
	private int answerId;
	private int questionId;
	private int choiceId;
	private String choiceText;
	private int choiceValue;
	
	public Answers() {
		// TODO Auto-generated constructor stub
	}
	
	public Answers(int answerId, int questionId, int choiceId,String choiceText,int choiceValue){
		this.answerId = answerId;
		this.questionId = questionId;
		this.choiceId = choiceId;
		this.choiceText = choiceText;
		this.choiceValue = choiceValue;
	}
	
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getChoiceId() {
		return choiceId;
	}
	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}
	public String getChoiceText() {
		return choiceText;
	}
	public void setChoiceText(String choiceText) {
		this.choiceText = choiceText;
	}
	public int getChoiceValue() {
		return choiceValue;
	}
	public void setChoiceValue(int choiceValue) {
		this.choiceValue = choiceValue;
	}
	
	
}
