package controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.MD5;
import dao.AdmainDao;
import forms.LoginForm;

@Controller
@RequestMapping("/user")
public class LoginControll {
	@Resource
	private AdmainDao adminDao;

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
		  String md5pass=MD5.GetMD5Code(password);
		  //校验用户名密码是否正确
		  boolean isuser=adminDao.validAdmin(name, md5pass);
		  if(isuser){
			  return "index";
		  }
		return "login";
	}
	
}
