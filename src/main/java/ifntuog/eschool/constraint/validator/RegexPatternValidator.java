package ifntuog.eschool.constraint.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ifntuog.eschool.constraint.annotation.RegexPattern;

public class RegexPatternValidator implements ConstraintValidator<RegexPattern, String>{
    
    private String pattern;
    
    @Override
    public void initialize(RegexPattern constraintAnnotation) {
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(pattern);
    }

}
