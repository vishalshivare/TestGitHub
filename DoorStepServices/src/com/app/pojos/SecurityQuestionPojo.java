package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name="DoorStep_Security_Questions")
public class SecurityQuestionPojo 
{
	private int questionId;
	private String question;
	
	@Id
	@GeneratedValue
	@Column(name="Question_Id",length=10)
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	
	@Column(name="Question",length=255)
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
		return "SecurityQuestionPojo [questionId=" + questionId + ", question="
				+ question + "]";
	}
	
	
}
