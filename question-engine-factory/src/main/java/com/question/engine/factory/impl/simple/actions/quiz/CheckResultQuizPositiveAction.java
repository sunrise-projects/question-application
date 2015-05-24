package com.question.engine.factory.impl.simple.actions.quiz;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.services.QAPersistenceAwareAction;

public class CheckResultQuizPositiveAction extends QAPersistenceAwareAction {

	protected void doExecute(Object arg) throws Exception {

		ContextDAO application = (ContextDAO) arg;

		SessionDAO session = this.getPersistenceService().getSessionData(
				application);
		application.setQandaSessionDAO(session);
		System.out.println("Quiz loaded from persistence.");
	}

}
