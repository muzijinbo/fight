package com.flight.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.flight.bean.DataBean;

import net.sf.json.JSONArray;

public class test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		CompanyDao companyDao = new CompanyDao();
		DataBean companyBean =companyDao.list("c1");
		System.out.println(companyBean.getName()+companyBean.getData().length);
		int a[]=companyBean.getData();
		String aa=JSONArray.fromObject(a).toString();
		System.out.println(aa);
		
	}

}
