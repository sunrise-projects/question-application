package com.questionfactory.core.factory;

import com.questionfactory.core.simple.model.AnswerBucket;
import com.questionfactory.core.simple.model.QuestionBucket;
import com.questionfactory.persistence.factory.Persistence;

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