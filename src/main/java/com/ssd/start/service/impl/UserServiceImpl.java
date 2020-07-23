package com.ssd.start.service.impl;

import com.ssd.start.dao.RbacUserMapper;
import com.ssd.start.entity.RbacUser;
import com.ssd.start.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author WHD
 * @date 2020/7/23 14:06
 */
@Data
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RbacUserMapper userMapper;

    @Override
    public RbacUser getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertUser(RbacUser user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUserById(RbacUser user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUserById(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public RbacUser selectByUserName(String username){
        return userMapper.selectByUserName(username);
    }
}
