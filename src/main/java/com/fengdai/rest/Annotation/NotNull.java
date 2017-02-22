package com.fengdai.rest.Annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNull.ValidStringChecker.class)
@Documented
public @interface NotNull 
{
    String message() default "字符串不能为空";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default{};
     
    class ValidStringChecker implements ConstraintValidator<NotNull,String>
    {

        @Override
        public void initialize(NotNull arg0)
        {    
        }

        @Override
        public boolean isValid(String strValue, ConstraintValidatorContext context)
        {
            if(strValue==null)
            {
                return false;
            }
            if(strValue.contains("<"))
            {
                return false;
            }
            return true;
        }
        
    }
}