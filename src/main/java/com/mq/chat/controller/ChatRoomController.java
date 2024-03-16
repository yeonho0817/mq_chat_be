package com.mq.chat.controller;

import com.mq.chat.data.vo.ChatRoomAddReqVo;
import com.mq.chat.data.vo.resVo.ChatRoomListResVo;
import com.mq.chat.data.vo.resVo.ChatRoomMessageListResVo;
import com.mq.chat.response.Response;
import com.mq.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @GetMapping("/list")
    public Callable<Response<ChatRoomListResVo>> getList(
        @RequestHeader(value = "Authorization") Long id
    ) {
        return () -> Response.of(chatRoomService.getList(id));
    }

    @GetMapping("/{id}")
    public Callable<Response<ChatRoomMessageListResVo>> getMessages(
            @RequestHeader(value = "Authorization") Long id,
            @PathVariable(value = "id") Long chatRoomId
    ) {
        return () -> Response.of(chatRoomService.getMessages(id, chatRoomId));
    }

    @PostMapping("")
    public Callable<Response<ChatRoomListResVo>> add(
            @RequestHeader(value = "Authorization") Long id,
            @Valid @RequestBody ChatRoomAddReqVo reqVo
    ) {
        chatRoomService.add(id, reqVo);
        return () -> Response.of(chatRoomService.getList(id));
    }

}
