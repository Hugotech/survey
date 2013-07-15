package com.hugotech.survey;

public class Questions {
	
	
	private int questionId;
	private int questionSequence;
	private int questionSubSequence;
	private String answerType;
	private String questionText;
	
	public Questions() {
	}
	
	public Questions(int questionId,int questionSequence,int questionSubSequence,String answerType,String questionText){
		this.questionId = questionId;
		this.questionSequence = questionSequence;
		this.questionSubSequence = questionSubSequence;
		this.answerType = answerType;
		this.questionText = questionText;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public int getQuestionSequence() {
		return questionSequence;
	}

	public void setQuestionSequence(int questionSequence) {
		this.questionSequence = questionSequence;
	}

	public int getQuestionSubSequence() {
		return questionSubSequence;
	}

	public void setQuestionSubSequence(int questionSubSequence) {
		this.questionSubSequence = questionSubSequence;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	
}
