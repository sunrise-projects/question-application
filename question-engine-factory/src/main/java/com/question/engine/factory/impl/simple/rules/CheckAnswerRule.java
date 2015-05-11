package com.question.engine.factory.impl.simple.rules;

import java.util.LinkedList;
import java.util.List;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareRule;

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
