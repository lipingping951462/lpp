package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import util.MD5;
import dao.AdmainDao;
import dao.UserDao;
import entity.Admin;
import entity.User;
import forms.LoginForm;

@Controller
@SessionAttributes("loginUser")
public class LoginControll {
	@Resource
	private AdmainDao adminDao;
	@Resource
	private UserDao userdao;
	
	private static String path="";

	// @RequestMapping("/login")
	// public String login(@RequestParam(value="name") String
	// name,@RequestParam("password") String password, Object command){
	//
	// return "index";
	// }
	// @RequestMapping("/login")
	// public String login(Object command, BindingResult bindingResult){
	// LoginForm loginForm = (LoginForm)command;
	// if(null==loginForm){
	// return "error";
	// }
	// String name=loginForm.getName();
	// String password=loginForm.getPassword();
	// String md5pass=MD5.GetMD5Code(password);
	// //校验用户名密码是否正确
	// boolean isuser=adminDao.validAdmin(name, md5pass);
	// if(isuser){
	// return "index";
	// }
	// return "login";
	// }

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginbegin(Model model,HttpServletRequest req) {
//		path=req.getContextPath();
		model.addAttribute(new Admin());
		return "login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login( @Valid Admin adminentiry, BindingResult bindingResult,Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		String name = adminentiry.getName();
		String password = adminentiry.getPassword();
		String md5pass = MD5.GetMD5Code(password);
		System.out.println(md5pass);
		
		// 校验用户名密码是否正确
		Admin admin = adminDao.getAdmin(name, md5pass);
		if (null!=admin) {
			model.addAttribute("loginUser",admin);
			
			return "redirect:"+path+"/user/searchuser";
		}
////		userdao.getAllUser(0, 10);
//		adminentiry.setMessage("密码错误");
		model.addAttribute("errormessage","密码错误");
		return "login";
	}
	
	@RequestMapping("/logout")
	public String login(Model model) {
		model.asMap().remove("loginUser");
		return "redirect:"+path+"/login";
	}
	
//	public ModelAndView onSubmit(Object cmd, BindException errors) {  
//        LoginForm loginForm = (LoginForm) cmd;  
//        if (loginForm.getUserName().equals("test")  
//                && loginForm.getPassword().equals("test")) {  
//            return new ModelAndView(getSuccessView(), "welcomeuser", loginForm  
//                    .getUserName());  
//        } else {  
//            errors.reject("ccc", "用户名或密码有误！");  
//            errors.rejectValue("userName", "nameErr", null, "用户名错误");  
//            errors.rejectValue("password", "passErr", null, "密码错误");  
//            return new ModelAndView(getFormView(), errors.getModel());  
//        }  
//    } 

}
