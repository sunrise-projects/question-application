package com.testing.poc.service;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.questionfactory.core.factory.Question;
import com.questionfactory.core.factory.QuestionFactory;
import com.questionfactory.core.factory.QuestionType;
import com.questionfactory.core.simple.model.QuestionBucket;
import com.questionfactory.persistence.factory.Persistence;
import com.questionfactory.persistence.factory.PersistenceFactory;
import com.questionfactory.persistence.factory.PersistenceType;
 
@Service
public class QuestionService
{

	private Question question = null;
	
	@PostConstruct
	public void initIt() throws Exception {
	  System.out.println("Init method after properties are set");
	
		Persistence  persistence = PersistenceFactory.buildPersistence(PersistenceType.FILE);
		
	    question = QuestionFactory.buildQuestion(QuestionType.SIMPLE, persistence );
	
	}
 
	@PreDestroy
	public void cleanUp() throws Exception {
	  System.out.println("Spring Container is destroy! Customer clean up");
	}
 
	public QuestionBucket getFirstQuestion(Object object, String memberNumber) {
		QuestionBucket questionBucket = question.getFirstQuestion("",memberNumber);
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
	
}