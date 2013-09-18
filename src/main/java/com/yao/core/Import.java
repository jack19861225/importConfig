package com.yao.core;



import com.yao.dao.impl.BaseDaoImpl;
import com.yao.entity.AllConfig;
import com.yao.io.LoadConfig;

public class Import {
	public static void main(String[] args) {
		System.out.println("we are running...");
		AllConfig config=null;
		try {
			config=LoadConfig.load();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取配置文件错误");
		} 
		
		BaseDaoImpl baseDao=new BaseDaoImpl();
		baseDao.index(config);
		
		
	}
}
