package com.tinhvan.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tinhvan.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		ValidationUtils.rejectIfEmpty(errors, "user_fullName", "user.user_fullName.emplty");
		ValidationUtils.rejectIfEmpty(errors, "user_mail", "user.user_mail.emplty");
		ValidationUtils.rejectIfEmpty(errors, "user_passWord", "user.user_passWord.emplty");
		ValidationUtils.rejectIfEmpty(errors, "role_id", "user.role_id.emplty");
		User user = (User) obj;

		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(user.getUser_mail()).matches())) {
			errors.rejectValue("user_mail", "user.user_mail.invalid");
		}
	}
}
