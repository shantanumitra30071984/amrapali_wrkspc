package com.amrapalicastle.ibatis;



import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class IBatisClientFactory {

	private static final ThreadLocal<SqlMapClient> threadLocal = new ThreadLocal<SqlMapClient>();
	
	private static SqlMapClient sqlMapClient;
	private final static String CONFIG_FILE_LOCATION = "com/amrapalicastle/ibatis/resource/IBatisConfig.xml";
	private static String configFile = CONFIG_FILE_LOCATION; 
	
	private IBatisClientFactory() {
		
	}
	
	public static void setConfigFile(String configFile) {
		IBatisClientFactory.configFile = configFile;
	}
	
	public static SqlMapClient getIBatisClient() {
		
		SqlMapClient sqlMap = threadLocal.get();
		
			if(sqlMap == null) {
				if(sqlMapClient == null) {
					rebuildClient();
				}
				threadLocal.set(sqlMapClient);
				sqlMap = sqlMapClient;
			}
		
		return sqlMap;
	}
	
	
	private static void rebuildClient() {
		try {
			Reader reader = Resources.getResourceAsReader(configFile);
			sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error initializing SQL Client. Cause: "+e);
		}		
		
	}
	
}
