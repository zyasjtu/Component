package org.cora.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.cora.constant.ReturnEnum;
import org.cora.dao.UserDao;
import org.cora.form.UserForm;
import org.cora.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Colin
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public JSONObject addUser(UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        try {
            Integer updateRows = userDao.addUser(user);
            return updateRows >= 0 ? ReturnEnum.SUCCESS.toJSONObject() : ReturnEnum.ERROR.toJSONObject();
        } catch (DuplicateKeyException e) {
            LOGGER.warn("duplicate mobile or loginName", e);
            return ReturnEnum.DUPLICATED.toJSONObject();
        }
    }

    @Override
    public JSONObject getUser(String mobile) {
        User user = userDao.getUser(mobile);
        if (null == user) {
            LOGGER.warn("empty user! + mobile:" + mobile);
            return ReturnEnum.EMPTY.toJSONObject();
        }
        JSONObject returnJo = ReturnEnum.SUCCESS.toJSONObject();
        returnJo.put("user", user);
        return returnJo;
    }

    @Override
    public JSONObject checkUser(String loginName, String password, HttpServletRequest request) {
        User user = userDao.checkUser(loginName, password);
        if (null == user) {
            LOGGER.warn("empty user! + loginName:" + loginName + " password:" + password);
            return ReturnEnum.EMPTY.toJSONObject();
        } else {
            request.getSession().setAttribute("user", user);
            return ReturnEnum.SUCCESS.toJSONObject();
        }
    }
}
