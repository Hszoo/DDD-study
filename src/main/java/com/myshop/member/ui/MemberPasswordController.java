package com.myshop.member.ui;

import com.myshop.member.command.application.NoMemberException;
import com.myshop.member.command.domain.BadPasswordException;
import com.myshop.member.command.domain.ChangePasswordRequest;
import com.myshop.member.command.domain.ChangePasswordService;
import com.myshop.member.command.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static jdk.vm.ci.hotspot.HotSpotCompilationRequestResult.success;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/changePassword")
public class MemberPasswordController {

    private final ChangePasswordService changePasswordService;

    @PostMapping("/submit")
    public Member submit(HttpServletRequest request) {

        Member member = null;
        try {
            member = changePasswordService.changePassword(request);
        } catch (NoMemberException ex) {

        }
        return member;
    }

    @PostMapping
    public String changePassword(ChangePasswordRequest chPwdReq, Errors errors) {

        String memberId = String.valueOf(SecurityContextHolder.getContext().getAuthentication());
        chPwdReq.setMemberId(memberId);
        try {
            changePasswordService.changePassword(chPwdReq);
            return successView();
        } catch (BadPasswordException | NoMemberException ex) {
            errors.reject("idPasswordNotMatch");
            return formView();
        }
    }

    private String successView() {
        return "change-password-success";
    }

    private String formView() {
        return "change-password-form";
    }
}
