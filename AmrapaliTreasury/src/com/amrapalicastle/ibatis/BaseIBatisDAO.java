package com.amrapalicastle.ibatis;


import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseIBatisDAO implements IBaseIBatisDAO {

	private final static String IBATIS_CONFIG_FILE = ""; 
	
	static {
		IBatisClientFactory.setConfigFile(IBATIS_CONFIG_FILE);
	}
	
	//@Override
	public SqlMapClient getSqlMapClient() {
		return IBatisClientFactory.getIBatisClient();
	}
	
}
