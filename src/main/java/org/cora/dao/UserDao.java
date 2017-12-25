package org.cora.dao;

import org.apache.ibatis.annotations.Param;
import org.cora.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    Integer addUser(User user);

    User getUser(String mobile);

    User checkUser(@Param("loginName") String loginName, @Param("password") String password);
}
