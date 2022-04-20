package com.javaforever.membooking.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * DBConf must owned by the Project Manager
 * You can change the configure to fit your situation.
 * 
 * This class should not change others except the databasename,
 * username and password
 * 
 * @author Jerry Shen
 * @release under GPLv2
 * @email jerry.shen.sjf@gmail.com
 *
 */
public final class DBConf{
    private static String databaseURL = "";
    private static String databaseUName = "";
    private static String databasePWord = "";
    private static String databaseName = "";
    
    private static String testDatabaseURL = "";
    private static String testDatabaseUName = "";
    private static String testDatabasePWord = "";
    private static String testDatabaseName = "";
    private static boolean isNotTest = false;					// Do not change this value, change them in dbconfig.xml
    private static boolean isProductProtectLockOffline = false;  	// In production: set true ; In test: set false
    private static boolean isTestsuiteOffline = false;			// Test suite offline: set true ; Test suite online: set false
    private static boolean isGpinterfaceOffline = false;	    // Testing gpinterface offline: set true ; Test suite online: set false
    private static boolean multiclause = false;
    private static Connection connection;
        
    static {
    	ReadConfigXml reader = new ReadConfigXml("dbconfig.xml");
    	databaseURL = reader.getUrl();
    	databaseName = reader.getDataBase();
    	databaseUName  = reader.getUserName();
    	databasePWord = reader.getPassWord();
    	
    	testDatabaseURL = reader.getTestUrl();
    	testDatabaseName = reader.getTestDataBase();
    	testDatabaseUName  = reader.getTestUserName();
    	testDatabasePWord = reader.getTestPassWord();
    	
    	isNotTest = reader.isNotTest();
    	isTestsuiteOffline = reader.isTestsuiteOffline();
    	isProductProtectLockOffline = reader.isProductProtectLockOffline();
    	isGpinterfaceOffline = reader.isGpinterfaceOffline();  
    }

	public synchronized static Connection initDB() throws Exception{
        try {
			String tdatabaseURL ="";
		    String tdatabaseUName ="";
		    String tdatabasePWord = "";
		    String tdatabaseName = "";
			if (isNotTest && isProductProtectLockOffline){
				tdatabaseURL = databaseURL;
			    tdatabaseUName = databaseUName;
			    tdatabasePWord = databasePWord;
			    tdatabaseName = databaseName;
			} else {
				tdatabaseURL = testDatabaseURL;
			    tdatabaseUName = testDatabaseUName;
			    tdatabasePWord = testDatabasePWord;
			    tdatabaseName = testDatabaseName;
			}	

            Class.forName("com.mysql.jdbc.Driver");
            String url = tdatabaseURL + tdatabaseName + "?useUnicode=true&characterEncoding=utf-8";
            System.out.println("JerryDebugMysql:"+ url);
            if (tdatabasePWord == null || "".equals(tdatabasePWord)){
            	url = tdatabaseURL + tdatabaseName + "?user="+tdatabaseUName+"&useUnicode=true&characterEncoding=utf-8";
            	connection = DriverManager.getConnection(url);
            }else {
            	connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);
            }
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }

        DBConf.setConnection(connection);

        return connection;
    }

	public synchronized static Connection initDB(boolean multiclause) throws Exception{
        try {
			String tdatabaseURL ="";
		    String tdatabaseUName ="";
		    String tdatabasePWord = "";
		    String tdatabaseName = "";
			if (isNotTest && isProductProtectLockOffline){
				tdatabaseURL = databaseURL;
			    tdatabaseUName = databaseUName;
			    tdatabasePWord = databasePWord;
			    tdatabaseName = databaseName;
			} else {
				tdatabaseURL = testDatabaseURL;
			    tdatabaseUName = testDatabaseUName;
			    tdatabasePWord = testDatabasePWord;
			    tdatabaseName = testDatabaseName;
			}

			DBConf.multiclause = multiclause;
			if (multiclause == false){
	            Class.forName("com.mysql.jdbc.Driver");
		            String url = tdatabaseURL + tdatabaseName + "?useUnicode=true&characterEncoding=utf-8";
	            System.out.println("JerryDebugMysql:"+ url);
	            connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);
			}else {
				if (connection != null) return connection;
				else {
					Class.forName("com.mysql.jdbc.Driver");
		            String url = tdatabaseURL + tdatabaseName + "?useUnicode=true&characterEncoding=utf-8";
		            System.out.println("JerryDebugMysql:"+ url);
		            connection = DriverManager.getConnection(url,tdatabaseUName,tdatabasePWord);
		            connection.setAutoCommit(false);
				}
			}
        } catch (Exception e){
            throw new IOException(e.getMessage());
        }

        DBConf.setConnection(connection);

        return connection;
    }

    public static synchronized void closeDB(Connection connection) {
        try {
        	if (multiclause==false){
	           connection.close();
	           connection = null;
        	}
        } catch (Exception e){
       }
   }

    public static synchronized void closeDB(Connection connection,boolean closemulti) {
        try {
           if (multiclause==false){
        	   connection.close();
        	   connection = null;
           } else if (multiclause==true&&closemulti==true){
        	   connection.commit();
        	   connection.close();
        	   connection = null;
           }
        } catch (Exception e){
//		           connection.rollback();
//		     	   connection.close();
     	   connection = null;
       }
   }

     public static void switchToTest(){
    	isNotTest = false;
     }

     public  static void switchToProduction(){
    	 isNotTest = true;
    }

	public static void setConnection(Connection connection) {
		DBConf.connection = connection;
	}

	public static boolean isTestsuiteOffline() {
		return isTestsuiteOffline;
	}

	public static boolean isGpinterfaceOffline() {
		return isGpinterfaceOffline;
	}	
}

