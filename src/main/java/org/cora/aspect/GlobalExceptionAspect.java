package org.cora.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.cora.constant.GlobalConstant;
import org.cora.constant.ReturnEnum;
import org.cora.exception.ValidatingException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionAspect {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionAspect.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public String handleServletRequestBindingException(MissingServletRequestParameterException e) {
        JSONObject returnJo = ReturnEnum.INVALID.toJSONObject();
        returnJo.put(GlobalConstant.RESP_MSG, e.getMessage());
        LOGGER.error(e.getMessage(), e);
        return JSON.toJSONString(returnJo);
    }

    @ExceptionHandler(ValidatingException.class)
    @ResponseBody
    public String handleValidatingException(ValidatingException e) {
        return this.handleBindingResult(e.getBindingResult(), e);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String handleBindException(BindException e) {
        return this.handleBindingResult(e.getBindingResult(), e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        JSONObject returnJo = ReturnEnum.ERROR.toJSONObject();
        LOGGER.error(e.getMessage(), e);
        return JSON.toJSONString(returnJo);
    }

    private String handleBindingResult(BindingResult bindingResult, Exception e) {
        List<String> respMsgList = new ArrayList<String>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            respMsgList.add(fieldError.getDefaultMessage());
        }
        String respMsg = StringUtils.join(respMsgList,"\n");

        JSONObject returnJo = ReturnEnum.INVALID.toJSONObject();
        returnJo.put(GlobalConstant.RESP_MSG, respMsg);
        LOGGER.error(respMsg, e);
        return JSON.toJSONString(returnJo);
    }
}
