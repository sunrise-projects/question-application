package com.questionfactory.core.simple.actions;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.services.QAPersistenceAwareAction;


public class CheckAnswerNegativeAction extends QAPersistenceAwareAction {

	protected void doExecute(Object arg) throws Exception {
		ContextDAO application = (ContextDAO) arg;

	}

}
