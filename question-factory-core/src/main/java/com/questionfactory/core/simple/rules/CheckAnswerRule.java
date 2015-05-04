package com.questionfactory.core.simple.rules;

import java.util.LinkedList;
import java.util.List;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.dao.QuestionDAO;
import com.questionfactory.core.simple.dao.SessionDAO;
import com.questionfactory.core.simple.services.QAPersistenceAwareRule;

public class CheckAnswerRule extends QAPersistenceAwareRule {

	protected boolean makeDecision(Object arg) throws Exception {
		
		ContextDAO application = (ContextDAO) arg;
		
		if(application.getMemberNumber() == null) {
			application.setStatus(ContextDAO.INSUFFICIENT_DATA);
			return false;
		}
		
		return true;

	}

}
