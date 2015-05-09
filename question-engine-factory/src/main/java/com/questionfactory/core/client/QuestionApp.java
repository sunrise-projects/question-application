package com.questionfactory.core.client;

import org.apache.log4j.Logger;

import com.questionfactory.core.factory.Question;
import com.questionfactory.core.factory.QuestionFactory;
import com.questionfactory.core.factory.QuestionType;
import com.questionfactory.core.simple.model.AnswerBucket;
import com.questionfactory.core.simple.model.QuestionBucket;
import com.questionfactory.core.simple.model.QuestionBucketStatus;
import com.questionfactory.core.simple.utils.QAUtils;
import com.questionfactory.persistence.factory.Persistence;
import com.questionfactory.persistence.factory.PersistenceFactory;
import com.questionfactory.persistence.factory.PersistenceType;

public class QuestionApp {
	
	private static Logger LOG = Logger.getLogger(QuestionApp.class);
	
	public static void main(String[] args) {
	    
		
		Persistence  persistence = PersistenceFactory.buildPersistence(PersistenceType.FILE);
		
	    Question question = QuestionFactory.buildQuestion(QuestionType.SIMPLE, persistence );
	    
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
