package org.cora.constant;

import com.alibaba.fastjson.JSONObject;

public enum ReturnEnum {
    INVALID("9501", "invalid"),
    SUCCESS("1000", "success"),
    ERROR("1001", "error"),
    EMPTY("2000", "empty"),
    DUPLICATED("2001", "duplicated");

    private String code;
    private String msg;

    ReturnEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(GlobalConstant.RESP_CODE, code);
        jsonObject.put(GlobalConstant.RESP_MSG, msg);
        return jsonObject;
    }
}
