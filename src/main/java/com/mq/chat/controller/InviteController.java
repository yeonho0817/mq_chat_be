package com.mq.chat.controller;

import com.mq.chat.data.vo.ChatInviteAcceptedReqVo;
import com.mq.chat.data.vo.ChatInviteReqVo;
import com.mq.chat.data.vo.resVo.InviteChatRoomListResVo;
import com.mq.chat.response.Response;
import com.mq.chat.service.InviteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invite")
public class InviteController {
    private final InviteService inviteService;

    @GetMapping("")
    public Callable<Response<InviteChatRoomListResVo>> getInviteList(
            @RequestHeader(value = "Authorization") Long id
    ) {
        return () -> Response.of(inviteService.getInviteList(id));
    }

    @PostMapping("")
    public Callable<Response<Void>> invite(
            @RequestHeader(value = "Authorization") Long id,
            @Valid @RequestBody ChatInviteReqVo reqVo
    ) {
        inviteService.invite(id, reqVo);
        return () -> Response.OK;
    }

    @PostMapping("/accepted")
    public Callable<Response<Void>> inviteAccepted(
            @RequestHeader(value = "Authorization") Long id,
//            @PathVariable(value = "id") Long inviteChatRoomId,
//            @RequestParam(value = "accepted", defaultValue = "true") Boolean accepted
            @RequestBody ChatInviteAcceptedReqVo reqVo
            ) {
        inviteService.inviteAccepted(id, reqVo);
        return () -> Response.OK;
    }

}
