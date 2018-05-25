package com.moss.impl;

import com.moss.mapper.MallGoodsDao;
import com.moss.service.MallGoodsService;
import com.moss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallGoodsServiceImpl implements MallGoodsService{

    @Autowired
    private MallGoodsDao mallGoodsDao;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return mallGoodsDao.listGoodsVo();
    }
}
