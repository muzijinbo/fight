package com.flight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.flight.bean.DataBean;
import com.flight.util.DBUtil;


public class CompanyDao {
	public DataBean list(String c_nbr) {
	 	// TODO Auto-generated method stub
		 	String sql = "select * from sale_c where c_nbr ='"+c_nbr+"'";
		 	Connection connection = DBUtil.getConn();
		 	Statement statement = null;
		 	ResultSet resultSet = null;
		 	DataBean companyBean = new DataBean();
		 	try {
		 		statement = connection.createStatement();
		 		resultSet = statement.executeQuery(sql);
			 	companyBean.setName(c_nbr);
			 	int[] data = new int[91];
			 	while (resultSet.next()) {	 	
				 	int i=Integer.parseInt(resultSet.getString("day_id"));
				 	data[i-1]=Integer.parseInt(resultSet.getString("sum_cnt"));
			 	}
			 	companyBean.setData(data);
			 	
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 	} finally {
		 		DBUtil.close(resultSet, statement, connection);
		 	}
		 	return companyBean;
	 	}
	
	public List<DataBean> listt(String a,String b){
		List<DataBean> companyBeans = new ArrayList<DataBean>();
		companyBeans.add(list(a));
		//companyBeans.add(list(b));
		return companyBeans;
	}
	
}
