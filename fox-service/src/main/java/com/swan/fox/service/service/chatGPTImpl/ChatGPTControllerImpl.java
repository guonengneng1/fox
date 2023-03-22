package com.swan.fox.service.service.chatGPTImpl;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.google.gson.Gson;
import com.swan.fox.api.controller.chatGPT.ChatGptController;
import com.swan.fox.api.vo.chatGPT.CompletionsVO;
import com.swan.fox.common.response.CompletionResponse;
import com.swan.fox.common.response.FoxResponse;
import com.swan.fox.service.constant.FoxConstant;
import com.swan.fox.service.enums.ChatModelVersionEnum;
import com.swan.fox.service.enums.ErrorCodeEnum;
import jdk.nashorn.internal.ir.annotations.Reference;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
@Service
public class ChatGPTControllerImpl implements ChatGptController {
    @NacosValue( "${model}")
    private String model;

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();


    @Override
    public FoxResponse completions(@RequestBody @Valid CompletionsVO request) {
        if(ChatModelVersionEnum.contains(model)){
            //3.5版本对话调用
            if(model.equals(ChatModelVersionEnum.THREE.getValue())){
                return chatVersionThree(request.getPrompt());
            }else {
                return new FoxResponse();
            }
        }else {
            return FoxResponse.getFailInstance(ErrorCodeEnum.CONFIG_ERROR.getCode(), ErrorCodeEnum.CONFIG_ERROR.getMsg());
        }
    }

    /**
     * chatGPT的3.5版本对话实现
     */
    public FoxResponse chatVersionThree(String prompt){
        Gson gson = new Gson();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(FoxConstant.JSON, gson.toJson(Collections.singletonMap("prompt", prompt)));
        Request re = new Request.Builder()
                .url(FoxConstant.API_URL + "completions")
                .addHeader("Authorization", "Bearer " + FoxConstant.API_KEY)
                .post(requestBody)
                .build();
        try (Response response = client.newCall(re).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response.code());
            }

            String responseBody = response.body().toString();
            CompletionResponse completionResponse = gson.fromJson(responseBody, CompletionResponse.class);
            return FoxResponse.getSuccessInstance(
                    completionResponse.getChoices().get(0).getText());
        } catch (IOException e) {
            FoxResponse.getFailInstance(1, "chatGPT请求失败");
            return FoxResponse.getFailInstance(1,e.getMessage());
        }
    }
}
