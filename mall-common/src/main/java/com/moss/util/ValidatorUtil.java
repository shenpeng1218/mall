package com.moss.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {


    public static final Pattern cellPhoneNumPattern = Pattern.compile("1\\d{10}");

    public static boolean isCellPhoneNum(String cellphoneNum){
        if(StringUtils.isEmpty(cellphoneNum)){
            return false;
        }
        Matcher m = cellPhoneNumPattern.matcher(cellphoneNum);
        return m.matches();
    }
}
