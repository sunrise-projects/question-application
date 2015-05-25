package com.question.engine.factory.client;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.engine.factory.impl.simple.utils.QAUtils;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class QuizApp {
	
	private static Logger LOG = Logger.getLogger(QuizApp.class);
	
	public static void main(String[] args) {
	    
		
		Persistence  persistence = PersistenceFactory.getInstance(PersistenceType.FILE);
		
	    Question question = QuestionFactory.getInstance(QuestionType.SIMPLE, persistence );
	    
	    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
	    InputStream is = classloader.getResourceAsStream("excel/sample-multi.xlsx");

	    List<QuizBucket> questionList = question.getQuizQuestions(is,"12345");
	    
	    for(QuizBucket quizQuestion : questionList) {
	    	System.out.println(quizQuestion.toString());
	    }
	    
	    SaveAnswerResponse saveAnswerResponse = question.saveQuizAnswer("a", questionList.get(0).getMemberNumber(), 
	    		questionList.get(0).getSessionId(), questionList.get(0).getQuestionNumber());

	    System.out.println(saveAnswerResponse.toString());
	    
	    System.out.println("No more question.");

	    List<QuizBucket> result = question.getQuizResult(questionList.get(0).getMemberNumber(), 
	    		questionList.get(0).getSessionId());
	    
	    for(QuizBucket quizQuestion : result ) {
	    	System.out.println(quizQuestion.toString());
	    }	    
		
	}
}
