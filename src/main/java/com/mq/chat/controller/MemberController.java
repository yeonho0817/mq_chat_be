package com.mq.chat.controller;

import com.mq.chat.data.vo.LoginReqVo;
import com.mq.chat.data.vo.SignUpReqVo;
import com.mq.chat.data.vo.resVo.LoginResVo;
import com.mq.chat.data.vo.resVo.MemberListResVo;
import com.mq.chat.response.Response;
import com.mq.chat.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/login")
    public Callable<Response<LoginResVo>> login(
        @Valid @RequestBody LoginReqVo reqVo
    ) {
        return () -> Response.of(memberService.login(reqVo));
    }

    @PostMapping("/sign-up")
    public Callable<Response<Void>> signUp(
        @Valid @RequestBody SignUpReqVo reqVo
    ) {
        memberService.signUp(reqVo);
        return () -> Response.OK;
    }

    @GetMapping("/list")
    public Callable<Response<MemberListResVo>> list(
        @RequestParam Long chatRoomId
    ) {
        return () -> Response.of(memberService.list(chatRoomId));
    }
}
