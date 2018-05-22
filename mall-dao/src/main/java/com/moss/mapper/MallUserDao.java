package com.moss.mapper;

import com.moss.bean.MallUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MallUserDao {

    @Select("select * from mall_user where id = #{id}")
    public MallUser getById(long id);
}
