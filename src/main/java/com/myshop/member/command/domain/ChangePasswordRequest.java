package com.myshop.member.command.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChangePasswordRequest {
    private String memberId;
    private String currentPassword;
    private String newPassword;
}
