package com.question.engine.factory.client;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.engine.factory.impl.simple.utils.QAUtils;
import com.question.engine.factory.spi.Question;
import com.question.engine.factory.spi.QuestionFactory;
import com.question.engine.factory.spi.QuestionType;
import com.question.persistence.factory.spi.Persistence;
import com.question.persistence.factory.spi.PersistenceFactory;
import com.question.persistence.factory.spi.PersistenceType;

public class QuestionApp {
	
	private static Logger LOG = Logger.getLogger(QuestionApp.class);
	
	public static void main(String[] args) {
	    
		
		Persistence  persistence = PersistenceFactory.getInstance(PersistenceType.FILE);
		
	    Question question = QuestionFactory.getInstance(QuestionType.SIMPLE, persistence );
	    
	    QuestionBucket questionBucket = question.getFirstQuestion("","12345");
	    System.out.println(questionBucket.toString());
	    System.out.println(questionBucket.getStatus());
	    
	    String answer = QAUtils.showQuestionHelper(questionBucket, false);
	    System.out.println(answer);
	    
	    AnswerBucket answerBucket = question.checkAnswer(answer, "12345", 
	    		questionBucket.getSessionId(), questionBucket.getQuestionNumber());
	    System.out.println(answerBucket);
	    
	    boolean moreQuestion = true;
	    while(moreQuestion) {
	    	questionBucket = question.getNextQuestion("12345", questionBucket.getSessionId());
		    System.out.println(questionBucket.toString());
	    	System.out.println(questionBucket.getStatus());
	    	
	    	if(questionBucket.getStatus() == null) {
	    		
	    		System.out.println("Done looping of more question.");
	    		moreQuestion = false;
	    		
	    	} else if( questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_AVAILABLE )) {
	    		
	    		answer = QAUtils.showQuestionHelper(questionBucket, false);
			    System.out.println(answer);
			    
			    answerBucket = question.checkAnswer(answer, "12345", 
			    		questionBucket.getSessionId(), questionBucket.getQuestionNumber());
			    System.out.println(answerBucket);
			    
	    	} else if( questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED )) {
	    		
	    		if(LOG.isDebugEnabled()) {
	    			LOG.debug("check for wrong answer list");
	    		}
		    	boolean repeatWrongList = true;
			    while(repeatWrongList) {
			    	questionBucket = question.getWrongAnswer("12345", questionBucket.getSessionId());
				    System.out.println(questionBucket.toString());
			    	System.out.println(questionBucket.getStatus());
			    	
			    	if (questionBucket.getStatus() == null) {
			    		//done looping on answer
			    		System.out.println("Done looping of answer list.");
			    		repeatWrongList = false;
			    	} else if( questionBucket.getStatus().equals(QuestionBucketStatus.WRONG_ANSWER_FOUND )) {
			    		QAUtils.showQuestionHelper(questionBucket, true);
					} else {
						System.out.println("Done looping of answer list. Status = " + questionBucket.getStatus());
						repeatWrongList = false;
					}
			    	
			    }
	    	} else {
	    		moreQuestion = false;
				System.out.println(questionBucket.getStatus());
				System.out.println("No more question. Exiting..");
	    	}
	    	
		    

	    }
	    
	    System.out.println("No more question.");
	    		
		
	}
}
