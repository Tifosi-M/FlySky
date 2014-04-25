package com.flysky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flysky.domain.CampusMemo;
import com.flysky.service.CampusService;

@Controller
public class CampusMemoController {
	@Autowired
	private CampusService campusService;
	@RequestMapping(value="/publish.do",method=RequestMethod.POST)
	public ModelAndView publish_memo(CampusMemo campusMemo){
		System.out.println(campusMemo);
		campusService.saveCampus(campusMemo);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("activity_list");
		return mv;
	}
}
