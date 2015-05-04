package com.questionfactory.core.simple.services;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.dao.SessionDAO;


public interface QAPersistenceInterface {
	public void recordApproval(ContextDAO application) throws Exception;
	public void recordRejection(ContextDAO application) throws Exception;
	public void recordIncomplete(ContextDAO application) throws Exception;
	
	public void saveSessionData(ContextDAO application) throws Exception;
	
	public SessionDAO getSessionData(ContextDAO application) throws Exception;
	
	public boolean saveData(ContextDAO ctx, SessionDAO dao)  throws Exception;
	
}
