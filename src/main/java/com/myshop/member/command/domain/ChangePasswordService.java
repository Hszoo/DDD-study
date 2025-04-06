package com.myshop.member.command.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

import static com.myshop.member.command.domain.MemberServiceHelper.findExistingMember;

@Service

@RequiredArgsConstructor
public class ChangePasswordService {

    private final MemberRepository memberRepository;

    // 필요한 값을 메서드의 파라미터로 전달받는 경우
    public void changePassword(MemberId memberId, String oldPw, String newPw) {

        Member member = findExistingMember(memberRepository, memberId);
        member.changePassword(oldPw, newPw);
    }

    // 필요한 값을 데이터 클래스 객체로 전달받는 경우
    @Transactional
    public void changePassword(ChangePasswordRequest request) {

        Member member = findExistingMember(memberRepository, request.getMemberId());
        member.changePassword(request.getCurrentPassword(), request.getNewPassword());
    }

    public Member changePassword(HttpServletRequest request) {

        Member member = findExistingMember(memberRepository, request.getMemberId());
        member.changePassword(request.getCurrentPassword(), request.getNewPassword());
    }


}