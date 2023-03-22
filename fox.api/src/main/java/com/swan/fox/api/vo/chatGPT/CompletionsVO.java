package com.swan.fox.api.vo.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompletionsVO {
    @NotEmpty(message = "消息不能为空")
    private String prompt;
}
