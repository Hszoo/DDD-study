package com.myshop.member.command.domain;

import com.myshop.member.command.application.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WrongChangePasswordService {

    private final MemberRepository memberRepository;

    public void changePassword(MemberId memberId, String oldPw, String newPw) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);

        // 기존 암호를 올바르게 입력했는지 체크
        if(!matches(oldPw, member.getPassword().getValue())) {
            throw new BadPasswordException();
        }
        member.changePassword(oldPw, newPw);
    }

    public boolean matches(String oldPw, String pw) {
        return oldPw.equals(pw);
    }
}