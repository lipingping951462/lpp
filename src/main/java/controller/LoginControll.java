package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import forms.LoginForm;

@Controller
@RequestMapping("/user")
public class LoginControll {

//	@RequestMapping("/login")
//	public String login(@RequestParam(value="name") String name,@RequestParam("password") String password, Object command){
//		
//		return "index";
//	}
	@RequestMapping("/login")
	public String login(Object command){
		  LoginForm loginForm = (LoginForm)command;  
		  if(null==loginForm){
			  return "error";
		  }
		  String name=loginForm.getName();
		  String password=loginForm.getPassword();
		  
		return "index";
	}
	
}
