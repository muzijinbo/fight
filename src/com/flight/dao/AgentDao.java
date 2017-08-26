package com.flight.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.flight.bean.AgentBean;
import com.flight.bean.DataBean;
import com.flight.bean.PieDataBean;
import com.flight.util.DBUtil;


public class AgentDao {
	public DataBean listSale(String o_nbr) {
	 	// TODO Auto-generated method stub
		 	String sql = "select * from sale_o where o_nbr ='"+o_nbr+"'";
		 	Connection connection = DBUtil.getConn();
		 	Statement statement = null;
		 	ResultSet resultSet = null;
		 	DataBean dataBean = new DataBean();
		 	try {
		 		statement = connection.createStatement();
		 		resultSet = statement.executeQuery(sql);
			 	dataBean.setName("sale");
			 	int[] data = new int[91];
			 	while (resultSet.next()) {	 	
				 	int i=Integer.parseInt(resultSet.getString("day_id"));
				 	data[i-1]=Integer.parseInt(resultSet.getString("sum_cnt"));
			 	}
			 	dataBean.setData(data);
			 	
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 	} finally {
		 		DBUtil.close(resultSet, statement, connection);
		 	}
		 	return dataBean;
	 }
	public List<PieDataBean> listPie(String o_nbr) {
	 	// TODO Auto-generated method stub
		 	String sql = "SELECT * FROM sumday_sale WHERE sum_cnt >2000000";
		 	Connection connection = DBUtil.getConn();
		 	Statement statement = null;
		 	ResultSet resultSet = null;
		 	List<PieDataBean> pies=new ArrayList<PieDataBean>();
		 	List<AgentBean> agents=new ArrayList<AgentBean>();
		 	try {
		 		statement = connection.createStatement();
		 		resultSet = statement.executeQuery(sql);
			 	
		 		int sum=0;
			 	while (resultSet.next()) {	
			 		/*PieDataBean p = new PieDataBean();
			 		p.setName(resultSet.getString("o_nbr"));
			 		p.setY(Integer.parseInt(resultSet.getString("sum_cnt")));*/
			 		AgentBean ab = new AgentBean();
			 		ab.setId(resultSet.getString("o_nbr"));
			 		ab.setSum_cnt(Integer.parseInt(resultSet.getString("sum_cnt")));
			 		sum+=Integer.parseInt(resultSet.getString("sum_cnt"));
			 		agents.add(ab);
			 	}
			 	System.out.println(sum+"*******sum");
			 	for(Iterator<AgentBean> it=agents.iterator();it.hasNext();){
			 		AgentBean ab=it.next();
			 		
			 		PieDataBean p = new PieDataBean();
			 		p.setName(ab.getId());
			 		double y=(double)ab.getSum_cnt()/(double)sum*300;
			 		p.setY(y);
			 		System.out.print(y+"**");
			 		if(ab.getId().equals(o_nbr)){
			 			p.setSliced(true);
			 			p.setSelected(true);
			 		}
			 		pies.add(p);
			 	}
			 	
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 	} finally {
		 		DBUtil.close(resultSet, statement, connection);
		 	}
		 	return pies;
	 }
	
	
	public List<AgentBean> listAgent() {
	 	// TODO Auto-generated method stub
		 	String sql = "SELECT * FROM sumday_sale WHERE sum_cnt >20000";
		 	Connection connection = DBUtil.getConn();
		 	Statement statement = null;
		 	ResultSet resultSet = null;
		 	List<AgentBean> agents=new ArrayList<AgentBean>();
		 	try {
		 		statement = connection.createStatement();
		 		resultSet = statement.executeQuery(sql);
			 	
			 	while (resultSet.next()) {	
			 		AgentBean ab = new AgentBean();
			 		ab.setId(resultSet.getString("o_nbr"));
			 		ab.setSum_cnt(Integer.parseInt(resultSet.getString("sum_cnt")));
			 		agents.add(ab);
			 	}
			 	
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 	} finally {
		 		DBUtil.close(resultSet, statement, connection);
		 	}
		 	return agents;
	 }
	public int cnt(String o_nbr,int i) {
	 	// TODO Auto-generated method stub
		 	String sql = "SELECT * FROM sale_o WHERE day_id="+i+" AND o_nbr='"+o_nbr+"'";
		 	Connection connection = DBUtil.getConn();
		 	Statement statement = null;
		 	ResultSet resultSet = null;
		 	try {
		 		statement = connection.createStatement();
		 		resultSet = statement.executeQuery(sql);
		
			 	while (resultSet.next()) {	
		           return Integer.parseInt(resultSet.getString("sum_cnt"));
			 	}
			 	
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 	} finally {
		 		DBUtil.close(resultSet, statement, connection);
		 	}
		 	return 0;
	 }
	public DataBean listBuy(String o_nbr) {
	 	// TODO Auto-generated method stub
		 	String sql = "select * from buy_o where o_nbr ='"+o_nbr+"'";
		 	Connection connection = DBUtil.getConn();
		 	Statement statement = null;
		 	ResultSet resultSet = null;
		 	DataBean dataBean = new DataBean();
		 	try {
		 		statement = connection.createStatement();
		 		resultSet = statement.executeQuery(sql);
			 	dataBean.setName("buy");
			 	int[] data = new int[91];
			 	while (resultSet.next()) {	 	
				 	int i=Integer.parseInt(resultSet.getString("day_id"));
				 	data[i-1]=Integer.parseInt(resultSet.getString("sum_cnt"));
			 	}
			 	dataBean.setData(data);
			 	
		 	} catch (Exception e) {
		 		e.printStackTrace();
		 	} finally {
		 		DBUtil.close(resultSet, statement, connection);
		 	}
		 	return dataBean;
	 	}
	public List<DataBean> list(String a){
		List<DataBean> dataBeans = new ArrayList<DataBean>();
		dataBeans.add(listSale(a));
		dataBeans.add(listBuy(a));
		return dataBeans;
	}
	
}
