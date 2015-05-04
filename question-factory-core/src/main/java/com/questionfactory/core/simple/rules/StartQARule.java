package com.questionfactory.core.simple.rules;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.framework.AbstractRule;


public class StartQARule extends AbstractRule {

	protected boolean makeDecision(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		
		if(application.getMemberNumber() == null) {
			application.setStatus(ContextDAO.INSUFFICIENT_DATA);
			return false;
		}
		
		
		
		
		return true;						
	}

}
