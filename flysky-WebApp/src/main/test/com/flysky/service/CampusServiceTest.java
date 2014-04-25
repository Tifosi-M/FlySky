package com.flysky.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.flysky.domain.CampusMemo;
import com.sun.org.apache.xml.internal.security.Init;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class CampusServiceTest extends TestCase {
	@Resource
	private CampusService campusService;
	private CampusMemo campus;
	@Before
	public void init(){
		campus = new CampusMemo();
		campus.setCampusName("英语角");
		campus.setCampusBy("教务处");
		campus.setCampusContent("周五进行英语角活动");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = sdf.format(new Date());
		campus.setCampusTime(date);
		campus.setCampusAddress("软件学院");
	}
	@Test
	public void saveTest(){
		campusService.saveCampus(campus);
	}
}
