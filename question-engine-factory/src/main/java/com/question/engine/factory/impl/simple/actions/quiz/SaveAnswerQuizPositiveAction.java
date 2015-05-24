package com.question.engine.factory.impl.simple.actions.quiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;


public class SaveAnswerQuizPositiveAction extends QAPersistenceAwareAction {

	static Logger logger = Logger.getLogger(SaveAnswerQuizPositiveAction.class);
	
	@SuppressWarnings("unused")
	protected void doExecute(Object arg) throws Exception {

		ContextDAO ctx = (ContextDAO) arg;

		SessionDAO qASessionDAO = this.getPersistenceService().getSessionData(
				ctx);

		QuestionDAO dao = null;
		for (QuestionDAO qAQuestionDAO : qASessionDAO.getQuestions()) {
			if (qAQuestionDAO.getQuestionNumber() == ctx
					.getQuestionSessionNumber()) {
				dao = qAQuestionDAO;
				break;
			}
		}

		System.out.println("ctx.getQuestionAnswer() {} " + ctx.getQuestionAnswer());
		System.out.println("dao.getAnswer() {} " + dao.getAnswer());
		
		boolean answerIsCorrect = true;
		//answer is same size and matching, i.e a and a and a = a amd length is 1
		if( (ctx.getQuestionAnswer().length() == dao.getAnswer().length()) 
				&& !ctx.getQuestionAnswer().equals(dao.getAnswer())
				&& ctx.getQuestionAnswer().length() == 1 ) {
			answerIsCorrect = false;
		}
		//answer is not same size. a,c and a or a and a,c
		if( !(ctx.getQuestionAnswer().length() == dao.getAnswer().length()) ) {
			answerIsCorrect = false;
		}	
		
		if( (ctx.getQuestionAnswer().length() == dao.getAnswer().length()) 
				&& ctx.getQuestionAnswer().length() > 1) {
			
			String[] yourAnswers = ctx.getQuestionAnswer().split(","); 
			String[] questionAnswer = dao.getAnswer().split(","); 
			
			List<String> yourAnswersList = Arrays.asList(yourAnswers);
			List<String> questionAnswerList = Arrays.asList(questionAnswer);
			
			for(String yourAnswer : yourAnswersList ) {
				if( !questionAnswerList.contains(yourAnswer)) answerIsCorrect = false;
			}
		}
		
		if(dao != null ) {

			if(answerIsCorrect) {
				dao.setCorrect(true);
				dao.setMemberAnswer(ctx.getQuestionAnswer());
				
			} else {
				dao.setCorrect(false);
				dao.setMemberAnswer(ctx.getQuestionAnswer());				
			}
			
			System.out.println(dao.toString());
			// persist
			this.getPersistenceService().saveData(ctx, qASessionDAO);

			
		} else {
			throw new RuntimeException("Error occurred! Dao is null." );
		}
		

	}

}
