package com.swan.fox.common.response;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//        "code",
//        "msg",
//        "data"
//})
@AllArgsConstructor
@NoArgsConstructor
public class FoxResponse implements Serializable {
    @JsonProperty("code")
    private Integer code;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("data")
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static FoxResponse getSuccessInstance(Object body) {
        return new FoxResponse(0, "成功", body);
    }
    public static FoxResponse getFailInstanceWithNoAnyAuth(){
        return new FoxResponse(98,"该用户没有任何权限",null);
    }
    public static FoxResponse getFailInstanceWithOnlyViewAuth(){
        return new FoxResponse(99,"该用户仅有查看权限，不能进行管理操作",null);
    }
    public static FoxResponse getFailInstanceWithException(String msg){
        return new FoxResponse(100,msg,null);
    }
    public static FoxResponse getFailInstance(int code, Object body) {
        return new FoxResponse(code, "失败", body);
    }
    public static FoxResponse getFailInstance(int code, String body) {
        return new FoxResponse(code, "失败："+body, null);
    }

    @Override
    public String toString() {
        return "ResultResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
