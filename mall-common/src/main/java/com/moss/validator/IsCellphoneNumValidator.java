package com.moss.validator;

import com.moss.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCellphoneNumValidator implements ConstraintValidator<IsCellPhoneNum, String> {

    boolean required = false;

    @Override
    public void initialize(IsCellPhoneNum isCellPhoneNum) {
        required = isCellPhoneNum.require();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if(required){
            return ValidatorUtil.isCellPhoneNum(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return ValidatorUtil.isCellPhoneNum(value);
            }
        }
    }
}
