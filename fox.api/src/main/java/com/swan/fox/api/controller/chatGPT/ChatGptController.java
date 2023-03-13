package com.swan.fox.api.controller.chatGPT;

import com.swan.fox.api.APIConstant;
import com.swan.fox.api.vo.chatGPT.CompletionsVO;
import com.swan.fox.common.response.FoxResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(value = APIConstant.URL_PREFIX)
@RestController
public interface ChatGptController {
    @PostMapping("/completions" )
    FoxResponse completions(@RequestBody @Valid CompletionsVO request);
}
