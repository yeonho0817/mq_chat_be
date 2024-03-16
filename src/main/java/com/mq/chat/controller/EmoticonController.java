package com.mq.chat.controller;

import com.mq.chat.data.vo.resVo.EmoticonListResVo;
import com.mq.chat.response.Response;
import com.mq.chat.service.EmoticonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emoticon")
public class EmoticonController {
    private final EmoticonService emoticonService;

    @GetMapping("")
    public Callable<Response<EmoticonListResVo>> get(
    ) {
        return () -> Response.of(emoticonService.get());
    }
}
