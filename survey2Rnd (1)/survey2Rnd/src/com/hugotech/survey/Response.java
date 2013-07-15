package com.hugotech.survey;

public class Response {

	private int id;
	private String answerText;
	private int instanceId;
	private int questionId;
	private int answerId;
	private String createdBy;
	private String createdDate;
	
	
	public Response() {

	}
	
	public Response(int id,String answerText, int instanceId, int answerId, String createdBy, String createdDate) {
		this.id = id;
		this.answerText = answerText;
		this.instanceId = instanceId;
		this.answerId = answerId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
	
	public Response(String answerText, int instanceId, int answerId, String createdBy, String createdDate) {
		this.answerText = answerText;
		this.instanceId = instanceId;
		this.answerId = answerId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	public int getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	
	
	
	
	
}
