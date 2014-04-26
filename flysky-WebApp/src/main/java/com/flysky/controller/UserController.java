package com.flysky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.flysky.domain.User;

@Controller
public class UserController {
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(User user){
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		if(user.getTel().equals("admin")&&user.getPassword().equals("admin")){
			String url = "redirect:showMemoList.do";
			mv.setViewName(url);
		}
		else {
			mv.setViewName("login_error");
		}
		return mv;
			
	}

}
