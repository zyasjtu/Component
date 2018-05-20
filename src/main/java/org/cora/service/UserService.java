package org.cora.service;

import com.alibaba.fastjson.JSONObject;
import org.cora.form.UserForm;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Colin
 */
public interface UserService {

    /**
     * add user
     *
     * @param userForm form
     * @return jsonObject
     */
    JSONObject addUser(UserForm userForm);

    /**
     * get user
     * @param mobile mobile
     * @return jsonObject
     */
    JSONObject getUser(String mobile);

    /**
     * check user
     * @param loginName loginName
     * @param password password
     * @param request request
     * @return jsonObject
     */
    JSONObject checkUser(String loginName, String password, HttpServletRequest request);
}
