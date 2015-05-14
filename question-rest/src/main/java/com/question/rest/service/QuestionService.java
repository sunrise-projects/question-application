package com.question.rest.service;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;
 
@Service
public class QuestionService
{

	private Question question = null;
	
	@PostConstruct
	public void initIt() throws Exception {
	  System.out.println("Init method after properties are set");
	
		Persistence  persistence = PersistenceFactory.getInstance(PersistenceType.FILE);
		
	    question = QuestionFactory.getInstance(QuestionType.SIMPLE, persistence );
	
	}
 
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	}
 
	public QuestionBucket getFirstQuestion(Object object, String memberNumber) {
		QuestionBucket questionBucket = question.getFirstQuestion(object, memberNumber);
	    System.out.println(questionBucket.toString());
	    System.out.println(questionBucket.getStatus());
		return questionBucket;
	}
	

	public QuestionBucket getNextQuestion(String memberNumber, String sessionId) {
		QuestionBucket questionBucket = question.getNextQuestion(memberNumber, sessionId);
	    System.out.println(questionBucket.toString());
		System.out.println(questionBucket.getStatus());
		return questionBucket;
	}

	public QuestionBucket getWrongAnswer(String memberNumber, String sessionId) {
		QuestionBucket questionBucket = question.getWrongAnswer(memberNumber, sessionId);
	    System.out.println(questionBucket.toString());
		System.out.println(questionBucket.getStatus());
		return questionBucket;
	}	

	public AnswerBucket checkAnswer(String answer, String memberNumber, String sessionId, int questionNumber) {
		AnswerBucket answerBucket = question.checkAnswer(answer, memberNumber, sessionId, questionNumber);
	    System.out.println(answerBucket.getAnswer());
		return answerBucket;
	}	


}