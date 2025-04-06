package com.myshop.member.command.domain;

import lombok.Getter;

@Getter
public class JoinRequest {

    MemberId memberId;
    String name;
    String password;

    String confirmPassword;
}
