package com.myshop.member.command.domain;


import com.myshop.member.command.application.NoMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void changePassword(MemberId memberId, String curPw, String newPw) {
        Member member = findExistingMember(memberId);

        member.changePassword(curPw, newPw);
    }

    public void initializePassword(String memberId) {
        Member member = findExistingMember(new MemberId(memberId));

        member.initializePassword();
    }
    public void leave(String memberId, String curPw) {
        Member member = findExistingMember(new MemberId(memberId));

        member.leave(curPw);
    }

    // 중복 코드 통합
    private Member findExistingMember(MemberId memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
    }
}
