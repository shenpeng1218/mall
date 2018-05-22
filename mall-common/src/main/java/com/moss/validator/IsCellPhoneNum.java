package com.moss.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {IsCellphoneNumValidator.class})
public @interface IsCellPhoneNum {

    //允许这个注解修饰的属性为空，但是默认不为空
    boolean require() default false;

    //如果校验不通过，提示什么信息
    String message() default "{手机号格式错误！}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
