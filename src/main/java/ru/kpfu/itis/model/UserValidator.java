package ru.kpfu.itis.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.kpfu.itis.model.forms.LoginForm;
import ru.kpfu.itis.repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {

    private UserRepository userRepository;

    @Autowired
    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass) || LoginForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "email", "empty");
        ValidationUtils.rejectIfEmpty(e, "password", "empty");
        if (o.getClass().equals(User.class)) {
            ValidationUtils.rejectIfEmpty(e, "passwordRepeat", "empty");
            ValidationUtils.rejectIfEmpty(e, "first_name", "empty");
            ValidationUtils.rejectIfEmpty(e, "second_name", "empty");

            User u = (User) o;

            if (userRepository.findByEmail(u.getEmail()) != null) {
                e.rejectValue("email", "email.Duplicate");
            }

            String regex = "\\S+@\\S+.\\S+";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(u.getEmail());
            if (!m.find()) {
                e.rejectValue("email", "email.notValid");
            }
            if (!u.getPassword().equals(u.getPasswordRepeat())) {
                e.rejectValue("passwordRepeat", "password.notEqual");
            }
            if (u.getPassword().length() < 8) {
                e.rejectValue("password", "password.notValid");
            }
            if (u.getCountry() == null) {
                e.rejectValue("country", "empty");
            }
            if (u.getCity() == null) {
                e.rejectValue("city", "empty");
            }
        }
    }
}
