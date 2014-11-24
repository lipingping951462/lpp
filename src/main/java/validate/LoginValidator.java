package validate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import forms.LoginForm;

public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// return TestForm.class.isAssignableFrom(clazz);
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginForm loginForm = (LoginForm) target;
		if (loginForm.getName().length() == 0) {
			errors.rejectValue("name", "login.error.username", null, "用户名不能为空！");
		}
		if (loginForm.getPassword().length() == 0)
			errors.rejectValue("password", "login.error.password", null,
					"密码不能为空！");

	}

}
