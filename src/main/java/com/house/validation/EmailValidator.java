package com.house.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail,String> {
    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9]{6,20}+@[A-Za-z0-9]{4,8}+\\.[A-Za-z]{2,6}$";
    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return (validateEmail(s));
    }
    private boolean validateEmail(final String email){
        this.pattern = Pattern.compile(EMAIL_PATTERN);
        this.matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
