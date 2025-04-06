package com.myshop.member.command.domain;

import com.myshop.member.command.application.NoMemberException;

public final class MemberServiceHelper { // 중복 로직 관리

    public static Member findExistingMember(MemberRepository repo, MemberId memberId) {
        return repo.findById(memberId)
                .orElseThrow(NoMemberException::new);
    }
}
