package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import util.FileManager;
import util.MD5;
import util.SystemContext;
import dao.UserDao;
import entity.User;
import exception.MyException;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	public UserDao userdao;

	// static {
	// ApplicationContext act = new ClassPathXmlApplicationContext(
	// "applicationContext.xml");
	// userdao = (UserDao) act.getBean("userDao");
	// userdao.setJdbcTemplate(new JdbcTemplate(userdao.getDataSource()));
	// }

	@RequestMapping("/searchuser")
	@Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class, readOnly = true)
	public String getUsers(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "id", required = false) String id,
			Map<String, Object> model) {

		int start = SystemContext.getPagenoLocal();
		int pagesize = SystemContext.getPagesizeLocal();
		// int end = start + pagesize;
		System.out.println("start:" + start + "end:" + pagesize);
		if (null != name && !"".equals(name)) {

			if (null != id && !"".equals(id)) {
				int count = userdao.getUserByNameIdCount(name, id);
				model.put("count", count);
				List<User> users = userdao.getUserByNameId(name, id, start,
						pagesize);
				model.put("searchuser", users);

			} else {
				int count = userdao.getUserByNameCount(name);
				model.put("count", count);
				List<User> users = userdao.getUserByName(name, start, pagesize);
				model.put("searchuser", users);
			}

		} else {
			if (null != id && !"".equals(id)) {
				// 获得总数
				int count = userdao.getUserByIdCount(id);
				model.put("count", count);
				List<User> users = userdao.getUserById(id, start, pagesize);
				model.put("searchuser", users);
			} else {
				// 获得总数
				int count = userdao.getUserCount();
				model.put("count", count);
				List<User> users = userdao.getAllUser(start, pagesize);
				model.put("searchuser", users);
			}
		}

		return "index";

	}

	// public String getUserById(@PathVariable("id") String id,
	// Map<String, Object> model) {
	//
	// user = userdao.getUserById(id);
	// // Map modelmap = new HashMap();
	// model.put("userbyid", user);
	// return "index";
	// // return new M odelAndView("/jsp/welcom.jsp",modelmap);
	// }
	@RequestMapping("/userByName")
	public String getUserByName(@RequestParam("name") String name,
			Map<String, Object> model) {
		int start = SystemContext.getPagenoLocal();
		int pagesize = SystemContext.getPagesizeLocal();
		int end = start + pagesize;
		List<User> userbynames = userdao.getUserByName(name, start, end);
		model.put("userbynames", userbynames);
		return "index?name=" + name;
	}

	@RequestMapping(value = { "allusers", "/" })
	public String getUsers(Map<String, Object> model) {
		int start = SystemContext.getPagenoLocal();
		int pagesize = SystemContext.getPagesizeLocal();
		int end = start + pagesize;
		List<User> allusers = userdao.getAllUser(start, end);
		model.put("searchuser", allusers);
		return "index";
	}

	@RequestMapping("/updateUser")
	public String updateUser(@RequestParam("name") String name,
			@RequestParam("id") String id,
			@RequestParam("password") String password, Map<String, Object> model) {
		String pass = "";
		Boolean isupdate = false;
		if (null != password & !"".equals(password)) {
			if (password.length() <20) {
				pass = MD5.GetMD5Code(password);
			}
		}

		String oldname = userdao.getUserNameById(id);
		User user = new User();
		user.setId(id);
		user.setName(name);
		if ("".equals(pass)) {
			isupdate = userdao.updateUserName(user);
		} else {
			user.setPassword(pass);
			isupdate = userdao.updateUser(user);
		}
		if (isupdate) {

			// 修改文件
			try {
				FileManager.updateFile(oldname, name);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		model.put("isupdate", isupdate);
		model.put("id", id);
		model.put("name", name);
		if (!"".equals(pass)) {
			model.put("password", password);
		}
		return "result";
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(@RequestParam("id") String id, String name,
			Map<String, Object> model) {
		Boolean isdelete = userdao.deleteUser(id);
		if (isdelete) {
			try {
				FileManager.deleteFile(name);
			} catch (MyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		model.put("isdelete", isdelete);
		return "result";
	}

	@RequestMapping("/saveUser")
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	public String saveUser(@RequestParam("name") String name,
			@RequestParam("password") String password, Map<String, Object> model)
			throws MyException {
		String pass = MD5.GetMD5Code(password);
		User user = new User();
//		user.setId(id);
		user.setName(name);
		user.setPassword(pass);
		Boolean issave = userdao.saveUser(user);
		if (issave) {
			FileManager.addFile(name);
		}
		model.put("issave", issave);
		return "result";
	}

	@RequestMapping("/id")
	public User getUser() {
		User user = new User();
		user.setId("1");
		user.setName("zhang");
		return user;
	}
}
