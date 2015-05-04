package com.questionfactory.core.simple.rules;


import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.services.QAPersistenceAwareRule;


public class NextQuestionRule extends QAPersistenceAwareRule {

	protected boolean makeDecision(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		
		if(application.getMemberNumber() == null) {
			application.setStatus(ContextDAO.INSUFFICIENT_DATA);
			return false;
		}
		
		
		
		
		return true;						
	}

}
