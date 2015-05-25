package com.question.rest.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Service;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

@Service
public class QuestionService {

	private Question question = null;

	@PostConstruct
	public void initIt() throws Exception {
		System.out.println("Init method after properties are set");

		Persistence persistence = PersistenceFactory
				.getInstance(PersistenceType.FILE);

		question = QuestionFactory
				.getInstance(QuestionType.SIMPLE, persistence);

	}

	@PreDestroy
	public void cleanUp() throws Exception {
		System.out.println("Spring Container destroy! clean up");
	}
	
    public List<QuizBucket> getQuizQuestions(Object object, String memberNumber) {
    	List<QuizBucket> questionList = question.getQuizQuestions(object,memberNumber);
    	System.out.println("questionList.sizee() {} "+questionList.size());
    	return questionList;
    }
    
	public SaveAnswerResponse saveQuizAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) {
		SaveAnswerResponse saveAnswerResponse = question.saveQuizAnswer(answer, 
				memberNumber, sessionId, questionNumber);
		System.out.println("saveAnswerResponse " + saveAnswerResponse.toString());
		return saveAnswerResponse; 
	}

	public List<QuizBucket> getQuizResult(String memberNumber, String sessionId) {
		List<QuizBucket> result = question.getQuizResult(memberNumber, 
				sessionId);
		return result;
	}

	public QuestionBucket getFirstQuestion(Object object, String memberNumber) {
		QuestionBucket questionBucket = question.getFirstQuestion(object,
				memberNumber);
		System.out.println(questionBucket.toString());
		System.out.println(questionBucket.getStatus());
		return questionBucket;
	}

	public QuestionBucket getNextQuestion(String memberNumber, String sessionId) {
		QuestionBucket questionBucket = question.getNextQuestion(memberNumber,
				sessionId);
		System.out.println(questionBucket.toString());
		System.out.println(questionBucket.getStatus());
		return questionBucket;
	}

	public QuestionBucket getWrongAnswer(String memberNumber, String sessionId) {
		QuestionBucket questionBucket = question.getWrongAnswer(memberNumber,
				sessionId);
		System.out.println(questionBucket.toString());
		System.out.println(questionBucket.getStatus());
		return questionBucket;
	}

	public AnswerBucket checkAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) {
		AnswerBucket answerBucket = question.checkAnswer(answer, memberNumber,
				sessionId, questionNumber);
		System.out.println(answerBucket.getAnswer());
		return answerBucket;
	}

}