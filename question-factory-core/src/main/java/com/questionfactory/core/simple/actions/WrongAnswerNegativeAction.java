package com.questionfactory.core.simple.actions;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.services.QAPersistenceAwareAction;


public class WrongAnswerNegativeAction extends QAPersistenceAwareAction {

	protected void doExecute(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;
		if(ContextDAO.INSUFFICIENT_DATA.equals(application.getStatus()))
			this.getPersistenceService().recordIncomplete(application);
		else
			this.getPersistenceService().recordRejection(application);

	}

}
