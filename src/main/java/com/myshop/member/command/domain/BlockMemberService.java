package com.myshop.member.command.domain;

import com.myshop.member.command.application.NoMemberException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlockMemberService {

    private MemberRepository memberRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void block(MemberId memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        if(member == null) throw new NoMemberException();
        member.block();
    }
}
