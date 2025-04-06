package com.myshop.member.command.domain;

public interface ChangePassword {

    public void changePassword(String memberId, String oldPw, String newPw);

}
