package com.ssd.start.dao;

import com.ssd.start.entity.RbacUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RbacUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RbacUser record);

    int insertSelective(RbacUser record);

    RbacUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RbacUser record);

    int updateByPrimaryKey(RbacUser record);

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "password", column = "password")
    })
    @Select("select * from rbac_user where user_name=#{username}")
    RbacUser selectByUserName(@Param("username") String username);
}