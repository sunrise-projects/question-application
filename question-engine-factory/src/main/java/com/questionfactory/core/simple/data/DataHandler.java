package com.questionfactory.core.simple.data;

import com.questionfactory.core.simple.dao.ContextDAO;
import com.questionfactory.core.simple.dao.SessionDAO;


public abstract class DataHandler {

	public enum Types {
	    EXCEL, SERIALIZED
	}
	
	public static DataHandler getInstance() {
		DataHandler data = new SerializedDataHandler();
		return data;
	}
	
	public static DataHandler getInstance(Types types) {
		DataHandler data = null;
		if(types.equals(Types.EXCEL)) {
			data = new ExcelDataHandler();
		} else {
			data = new SerializedDataHandler();
		}
		return data;
	}
	
	
	public abstract boolean saveSessionData(ContextDAO ctx);
	
	public abstract SessionDAO getSessionData(ContextDAO application) throws Exception;
	
	public abstract boolean saveData(ContextDAO ctx, SessionDAO dao)  throws Exception;
	
	public abstract Object loadData(Object obj);
	

	
}
