package com.moss.service;

import com.moss.bean.MallUser;
import com.moss.result.CodeMessage;
import com.moss.vo.LoginVo;

public interface MallUserService {

    public MallUser getById(long id);

    public CodeMessage login(LoginVo loginVo);
}
