package com.swan.fox.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 错误码枚举
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {
    CONFIG_ERROR(1000, "nacos配置错误");
    private final Integer code;
    private final String msg;

    public static String getMsg(String code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.getCode().equals(code)) {
                return errorCodeEnum.getMsg();
            }
        }
        return "";
    }
}
