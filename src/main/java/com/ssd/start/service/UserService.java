package com.ssd.start.service;

import com.ssd.start.entity.RbacUser;

/**
 * @author WHD
 * @date 2020/7/23 14:06
 */
public interface UserService {

    RbacUser getUserById(Long id);

    int insertUser(RbacUser user);

    int updateUserById(RbacUser user);

    int deleteUserById(Long id);

    RbacUser selectByUserName(String username);

}
