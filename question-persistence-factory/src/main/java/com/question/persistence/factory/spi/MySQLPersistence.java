package com.question.persistence.factory.spi;


public class MySQLPersistence extends Persistence {
	 
	MySQLPersistence() {
        super(PersistenceType.FILE);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building mysql persistence");
    }

	@Override
	public boolean saveData(String memberNumber, String sessionId, Object obj) {
		return false;
		
	}

	@Override
	public Object retrieveData(String memberNumber, String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}
}