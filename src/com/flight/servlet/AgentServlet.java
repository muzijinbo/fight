package com.flight.servlet;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.bean.AgentBean;
import com.flight.bean.DataBean;
import com.flight.bean.DoubleDataBean;
import com.flight.bean.PieDataBean;
import com.flight.dao.AgentDao;
import com.flight.util.DayNum;
import com.flight.util.LinearRegression;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import net.sf.json.JSONArray;

/**
 * Servlet implementation class AgentServlet
 */
@WebServlet("/AgentServlet")
public class AgentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
@Override
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	request.setCharacterEncoding("utf-8");
	String method = request.getParameter("method");
	if("getAgentSale".equals(method)){
		getAgentSale(request,response);
	}
	if("getAgentChazhi".equals(method)){
		getAgentChazhi(request,response);
	}
	if("yuce".equals(method)){
		yuce(request,response);
	}
	if("listAgents".equals(method)){
		listAgents(request,response);
	}
	if("getPies".equals(method)){
		getPies(request,response);
	}
}
private void listAgents(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	// TODO Auto-generated method stub
	
		req.setCharacterEncoding("utf-8");
		//String status = req.getParameter("status");
		
		AgentDao ad = new AgentDao();
		List<AgentBean> AgentBeans=ad.listAgent();
		String json = getJsonString(AgentBeans);
		resp.getWriter().write(json);
	

		
}
	private void getAgentSale(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			req.setCharacterEncoding("utf-8");
			String status = req.getParameter("status");
			String o_nbr=req.getParameter("o_nbr");
			System.out.println(o_nbr);
			AgentDao AgentDao = new AgentDao();
			List<DataBean> AgentBeans=AgentDao.list(o_nbr);
			
			//resp.setContentType("text/html;charset=utf-8");
			String json = getJsonString(AgentBeans);
			resp.getWriter().write(json);
		
	
 		
	}
	private void yuce(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			req.setCharacterEncoding("utf-8");
			String status = req.getParameter("status");
			String o_nbr=req.getParameter("o_nbr");
			System.out.println(o_nbr);
			LinearRegression lr=new LinearRegression();
			AgentDao ad=new AgentDao();
			List<AgentBean> agents=ad.listAgent();	
			lr.computeLine(ad.listSale(o_nbr).getData());//代理人a的一元线性回归方程
			
			//double ideal=lr.compute(91)*DayNum.day[day];//代理人a在第i天应该卖出的票
			int data[]=ad.listSale(o_nbr).getData();
			double dataNew[]=new double[200];
			for(int i=0;i<data.length;i++){
				dataNew[i]=(double)data[i];
			}
			for(int i=data.length;i<200;i++){
				dataNew[i]=lr.compute(i)*DayNum.day[i];//代理人a在第i天应该卖出的票	
				//System.out.println(dataNew[i]);
			}
			
			List<DoubleDataBean> ddBeans=new ArrayList<DoubleDataBean>();
			
			DoubleDataBean ddb=new DoubleDataBean();
			ddb.setName("ideal");
			ddb.setData(dataNew);
			ddBeans.add(ddb);
			String json = getJsonString(ddBeans);
			resp.getWriter().write(json);
		
	
 		
	}
	private void getAgentChazhi(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			req.setCharacterEncoding("utf-8");
			String status = req.getParameter("status");
			String o_nbr=req.getParameter("o_nbr");
			System.out.println(o_nbr);
			List<DataBean> AgentBeans=new ArrayList<DataBean>();
			AgentDao agentDao = new AgentDao();
			DataBean dataBean1=agentDao.listSale(o_nbr);
			int sdata1[]=dataBean1.getData();
			
			DataBean dataBean2=agentDao.listBuy(o_nbr);
			int sdata2[]=dataBean2.getData();
			int data3[]=new int[91];
			for(int i=0;i<91;i++){
				data3[i]=sdata1[i]-sdata2[i];
				//System.out.println(sdata1[i]+"***"+sdata2[i]);
			}
			DataBean db=new DataBean();
			db.setName("short");
			db.setData(data3);
			AgentBeans.add(db);
			//resp.setContentType("text/html;charset=utf-8");
			String json = getJsonString(AgentBeans);
			resp.getWriter().write(json);
		
	
 		
	}
	private void getPies(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			req.setCharacterEncoding("utf-8");
			//String status = req.getParameter("status");
			String o_nbr=req.getParameter("o_nbr");
			System.out.println(o_nbr);
			List<PieDataBean> pdbs=new ArrayList<PieDataBean>();
			AgentDao agentDao = new AgentDao();
			pdbs=agentDao.listPie(o_nbr);
				
			String json = getJsonString(pdbs);
			resp.getWriter().write(json);
		
	
 		
	}
	public String getJsonString(Object o){
		ObjectMapper om = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator jg = new JsonFactory().createJsonGenerator(sw);
			om.writeValue(jg, o);
			jg.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return sw.toString();
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if("getAgentSale".equals(method)){
			getAgentSale(request,response);
		}
		if("getAgentChazhi".equals(method)){
			getAgentChazhi(request,response);
		}
		if("yuce".equals(method)){
			yuce(request,response);
		}
		if("listAgents".equals(method)){
			listAgents(request,response);
		}
		if("getPies".equals(method)){
			getPies(request,response);
		}
	}

}
