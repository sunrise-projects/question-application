package com.questionfactory.persistence.factory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FilePersistence extends Persistence {
	 
	FilePersistence() {
        super(PersistenceType.FILE);
        construct();
    }
 
    @Override
    protected void construct() {
        System.out.println("Building file persistence");
    }

	@Override
	public boolean saveData(String memberNumber, String sessionId, Object obj) {

		boolean ret = true;
		FileOutputStream fout=null;
		try {
			String temp = System.getProperty("java.io.tmpdir");
			fout = new FileOutputStream(temp+"/memorizer-"+memberNumber+"-"+sessionId+".tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(obj);
						
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = false;
		} finally {
			try {
				fout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				ret = false;
			}
		}
		return ret;
	}

	@Override
	public Object retrieveData(String memberNumber, String sessionId) {
		try {
			String temp = System.getProperty("java.io.tmpdir");
			FileInputStream fin = new FileInputStream(temp+"/memorizer-"+memberNumber+"-"+sessionId+".tmp");
			ObjectInputStream ois = new ObjectInputStream(fin);
			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
		
	}
}