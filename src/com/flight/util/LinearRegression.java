package com.flight.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.bean.AgentBean;
import com.flight.bean.PointBean;
import com.flight.dao.AgentDao;

import sun.management.Agent;

public class LinearRegression {
	
	private float a;
	private float b;
	public void computeLine(int num[]){
		
		List<PointBean> points=new ArrayList<PointBean>();
		
		for(int i=0;i<num.length;i++){
			PointBean pointBean= new PointBean();
			pointBean.setX(i+1);
			pointBean.setY(num[i]);
			points.add(pointBean);
		}
		float avgX;//x平均值
		float avgY;//x平均值
		float sumX=0;//y和值
		float sumY=0;//y和值
		float sumXY=0;//x*y的和
		float lxx=0;
		float lyy=0;
		float lxy=0;
		for(Iterator<PointBean> it = points.iterator(); it.hasNext();){
			PointBean pb=((PointBean)it.next());
			sumX+=pb.getX();
			sumY+=pb.getY();
			sumXY+=pb.getX()*pb.getY();
			
		}
		avgX=sumX/num.length;
		avgY=sumY/num.length;

		for(Iterator<PointBean> it = points.iterator(); it.hasNext();){
			PointBean pb=((PointBean)it.next());
			lxx+=Math.pow((pb.getX()-avgX), 2);
			lyy+=Math.pow((pb.getY()-avgY), 2);
		}
		lxy=sumXY-num.length*avgX*avgY;
		//float a=0;
		//float b=0;
		a=lxy/lxx;
		//System.out.println(a+"***"+lxy/lxx);
		b=avgY-a*avgX;
		String s = a+";"+b;
	}
	
	public  float compute(int i){
		return a*i+b;
	}
	
	//private static float[] day;//每天的系数
	/*public float[] getDay() {
		return day;
	}

	public void setDay(float[] day) {
		this.day = day;
	}*/

	public void countDay(){
		//LinearRegression lr=new LinearRegression();
		AgentDao ad=new AgentDao();
		List<AgentBean> agents=ad.listAgent();	
		float dayNum[]=new float[91];
		for(int i=1;i<92;i++){
			float sum=0;
			for(Iterator it =agents.iterator();it.hasNext();){
				/*AgentBean a=  (AgentBean) it.next();
				String o_nbr=a.getid();
				lr.computeLine(ad.listSale(o_nbr).getData());//代理人a的一元线性回归方程
				float ideal=lr.compute(1);//代理人a在第i天应该卖出的票
				int real=ad.cnt(o_nbr, 1);//
				float val=(float)real/ideal;
				System.out.println(val);*****
				*****************************/			
				AgentBean a=  (AgentBean) it.next();
				String o_nbr=a.getId();	
				float ideal=(float)a.getSum_cnt()/91; //代理人a在第i天应该卖出的票
				int real=ad.cnt(o_nbr, i);//
				float val=(float)real/ideal;
				sum+=val;
			}
			float avg=sum/agents.size();
			dayNum[i]=avg;
			System.out.print(avg+",");
			//lr.setDay(dayNum);
		}
	}
	public double estimate(String o_nbr,int day){//
		LinearRegression lr=new LinearRegression();
		AgentDao ad=new AgentDao();
		List<AgentBean> agents=ad.listAgent();	
		lr.computeLine(ad.listSale(o_nbr).getData());//代理人a的一元线性回归方程
		double ideal=lr.compute(91)*DayNum.day[day];//代理人a在第i天应该卖出的票
		System.out.println(ideal);
		return ideal;
		
	}
	public static String gnerateJsonFromObject(Object object) throws Exception {  
        ObjectMapper mapper = new ObjectMapper();  
        return mapper.writeValueAsString(object);  
    }  
	public static void main(String args[]){
		/*LinearRegression lr=new LinearRegression();
		AgentDao ad=new AgentDao();
		List<AgentBean> agents=ad.listAgent();	
		System.out.println(agents);*/
		/*lr.computeLine(ad.listSale("o1147").getData());//代理人a的一元线性回归方程
		
		//double ideal=lr.compute(91)*DayNum.day[day];//代理人a在第i天应该卖出的票
		for(int i=30;i<100;i++){
			double ideal=lr.compute(91)*DayNum.day[i];//代理人a在第i天应该卖出的票
			System.out.println(ideal);
		}*/
		//System.out.println(ideal);
		/*LinearRegression lr=new LinearRegression();
		AgentDao ad = new AgentDao();
		List<AgentBean> AgentBeans=ad.listAgent();
		String json = lr.getJsonString(AgentBeans);
		System.out.println(json);*/
		LinearRegression lr=new LinearRegression();
		AgentDao ad=new AgentDao();
		List<AgentBean> agents=ad.listAgent();
		
		String json;
		try {
			json = gnerateJsonFromObject(agents);
			System.out.println(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}
}
