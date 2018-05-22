package com.moss.impl;

import com.moss.bean.MallUser;
import com.moss.exception.GlobalException;
import com.moss.mapper.MallUserDao;
import com.moss.result.CodeMessage;
import com.moss.service.MallUserService;
import com.moss.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MallUserServiceImpl implements MallUserService{

    @Autowired
    private MallUserDao mallUserDao;

    @Override
    public MallUser getById(long id) {
        return mallUserDao.getById(id);
    }

    @Override
    public boolean login(LoginVo loginVo) {
        MallUser mallUser = mallUserDao.getById(Long.parseLong(loginVo.getCellphoneNum()));
        if(mallUser == null){
            throw new GlobalException(CodeMessage.CELLPHONE_NOT_EXITE);
        }
        if(!mallUser.getPassword().equals(loginVo.getPassword())){
            throw new GlobalException(CodeMessage.PASSWORD_ERROR);
        }
        return true;
    }
}
