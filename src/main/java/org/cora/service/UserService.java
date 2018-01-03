package org.cora.service;

import com.alibaba.fastjson.JSONObject;
import org.cora.form.UserForm;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    JSONObject addUser(UserForm userForm);

    JSONObject getUser(String mobile);

    JSONObject checkUser(String loginName, String password, HttpServletRequest request);
}
