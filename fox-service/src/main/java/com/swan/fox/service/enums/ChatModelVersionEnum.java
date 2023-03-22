package com.swan.fox.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
/**
 * chatGPT版本枚举
 */
public enum ChatModelVersionEnum {
    THREE("3","chatGPT版本3"),
    THREE_POINT_FIVE("3.5","chatGPT版本3.5"),
    FOUR("4","chatGPT版本4");

    private final String value;
    private final String msg;

    public static boolean contains(String code) {
        for (ChatModelVersionEnum chatModelVersionEnum : ChatModelVersionEnum.values()) {
            if (chatModelVersionEnum.getValue().equals(code)) {
                return true;
            }
        }
        return false;
    }
    public static String getMsg(String code) {
        for (ChatModelVersionEnum chatModelVersionEnum : ChatModelVersionEnum.values()) {
            if (chatModelVersionEnum.getValue().equals(code)) {
                return chatModelVersionEnum.getMsg();
            }
        }
        return "";
    }
}
