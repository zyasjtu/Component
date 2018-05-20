package org.cora.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.binding.BindingException;
import org.cora.exception.ValidatingException;
import org.cora.form.UserForm;
import org.cora.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;
import static org.cora.constant.GlobalConstant.DATE_FORMAT;

/**
 * @author Colin
 */
@RestController
@RequestMapping(method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addUser")
    public String addUser(@Validated(UserForm.AddUser.class) UserForm userForm, BindingResult bindingResult, BindingException e) {
        if (bindingResult.hasErrors()) {
            throw new ValidatingException(e, bindingResult);
        }
        JSONObject returnJo = userService.addUser(userForm);
        return JSON.toJSONString(returnJo);
    }

    @RequestMapping(value = "/getUserInfoByMobile")
    public String getUserInfoByMobile(@RequestParam("mobile") String mobile) {
        JSONObject returnJo = userService.getUser(mobile);
        return JSON.toJSONStringWithDateFormat(returnJo, DATE_FORMAT, WriteMapNullValue);
    }

    @RequestMapping(value = "/checkUser")
    public String checkUser(@Validated (UserForm.CheckUser.class) UserForm userForm, HttpServletRequest request,
                            BindingResult bindingResult, BindingException e) {
        if (bindingResult.hasErrors()) {
            throw new ValidatingException(e, bindingResult);
        }
        JSONObject returnJo = userService.checkUser(userForm.getLoginName(), userForm.getPassword(),request);
        return JSON.toJSONString(returnJo);
    }
}
