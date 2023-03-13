package com.swan.fox.service.service.chatGPTImpl;

import com.google.gson.Gson;
import com.swan.fox.api.controller.chatGPT.ChatGptController;
import com.swan.fox.api.vo.chatGPT.CompletionsVO;
import com.swan.fox.common.response.CompletionResponse;
import com.swan.fox.common.response.FoxResponse;
import com.swan.fox.service.constant.FoxConstant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
@Service
public class ChatGPTControllerImpl implements ChatGptController {
    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();


    @Override
    public FoxResponse completions(@RequestBody @Valid CompletionsVO request) {
        Gson gson = new Gson();
        okhttp3.RequestBody requestBody = okhttp3.RequestBody.create(FoxConstant.JSON, gson.toJson(Collections.singletonMap("prompt", request.getPrompt())));
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
