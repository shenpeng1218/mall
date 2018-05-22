package com.moss.vo;

import com.moss.validator.IsCellPhoneNum;

import javax.validation.constraints.NotNull;

public class LoginVo {

    @NotNull
    @IsCellPhoneNum
    private String cellphoneNum;

    @NotNull
    private String password;

    public String getCellphoneNum() {
        return cellphoneNum;
    }

    public void setCellphoneNum(String cellphoneNum) {
        this.cellphoneNum = cellphoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
