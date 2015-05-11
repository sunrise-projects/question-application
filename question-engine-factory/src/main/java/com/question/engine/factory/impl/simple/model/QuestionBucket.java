package com.question.engine.factory.impl.simple.model;

import java.util.Map;

public class QuestionBucket {
	
	private String question;
	private Map<String, String> selection;
	private int questionNumber;
	private String sessionId;
	
	private String answer;
	private String explanation;
	
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	private QuestionBucketDetails questionBucketDetails;
	
	private QuestionBucketStatus status;
	
	public QuestionBucketDetails getQuestionBucketDetails() {
		return questionBucketDetails;
	}
	public void setQuestionBucketDetails(QuestionBucketDetails questionBucketDetails) {
		this.questionBucketDetails = questionBucketDetails;
	}
	public QuestionBucketStatus getStatus() {
		return status;
	}
	public void setStatus(QuestionBucketStatus status) {
		this.status = status;
	}
	
	public int getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Map<String, String> getSelection() {
		return selection;
	}
	public void setSelection(Map<String, String> selection) {
		this.selection = selection;
	}
	@Override
	public String toString() {
		return "QuestionBucket [question=" + question + ", selection="
				+ selection + ", questionNumber=" + questionNumber
				+ ", sessionId=" + sessionId + ", answer=" + answer
				+ ", explanation=" + explanation + ", questionBucketDetails="
				+ questionBucketDetails + ", status=" + status + "]";
	}
	
}
