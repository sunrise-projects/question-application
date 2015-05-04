package com.questionfactory.core.simple.data;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.dao.SessionDAO;
import com.questionfactory.persistence.factory.Persistence;

public class SerializedDataHandler extends DataHandler {

	public boolean saveData(ContextDAO ctx, SessionDAO dao) {		
		Persistence p = ctx.getPersistence();
		boolean ret = p.saveData(ctx.getMemberNumber(), ctx.getSessiondId(), dao);
		return ret;
	}
	
	public boolean saveSessionData(ContextDAO ctx) {
		SessionDAO dao = ctx.getQandaSessionDAO();
		
		saveData(ctx, dao);

		return true;
	}


	public SessionDAO getSessionData(ContextDAO ctx) throws Exception {
		
		return (SessionDAO) ctx.getPersistence().retrieveData(ctx.getMemberNumber(), ctx.getSessiondId());
	}

	@Override
	public Object loadData(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
