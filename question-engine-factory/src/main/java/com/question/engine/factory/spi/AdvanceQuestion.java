package com.question.engine.factory.spi;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.persistence.factory.spi.Persistence;

public class AdvanceQuestion extends Question {
	 
	AdvanceQuestion(Persistence persistence) {
        super(QuestionType.ADVANCE, persistence);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building advance question");
    }

	@Override
	public <T> QuestionBucket getFirstQuestion(T input, String memberNumber) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public AnswerBucket checkAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionBucket getNextQuestion(String memberNumber,
			String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QuestionBucket getWrongAnswer(String memberNumber,
			String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

}