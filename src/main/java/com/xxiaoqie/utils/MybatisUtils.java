package com.xxiaoqie.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtils {
	private static SqlSessionFactory sqlSessionFactory = null;
	
	public static SqlSessionFactory createFactory() throws IOException  {
		if(sqlSessionFactory == null) {
			Reader reader;
		    reader = Resources.getResourceAsReader("Configuration.xml");
		    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		}
		return sqlSessionFactory;
	}
}
