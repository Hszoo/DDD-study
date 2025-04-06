package com.myshop.member.command.domain;

import com.myshop.common.event.Events;
import com.myshop.common.jpa.EmailSetConverter;
import com.myshop.common.model.Email;
import com.myshop.common.model.EmailSet;
import lombok.Getter;

import javax.persistence.*;
import java.util.Random;
import java.util.Set;

import static org.drools.util.StringUtils.isEmpty;

@Entity
@Getter
@Table(name = "member")
public class Member {

    ///

    @EmbeddedId
    private MemberId id;

    private String name;
    @Embedded
    private Password password;

    private boolean blocked;

    @Column(name = "emails")
    @Convert(converter = EmailSetConverter.class)
    private EmailSet emails;

    protected Member() {
    }

    public Member(MemberId id, String name) {
        this.id = id;
        this.name = name;
    }

    public MemberId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void initializePassword() {
        String newPassword = generateRandomPassword();
        this.password = new Password(newPassword);
        Events.raise(new PasswordChangedEvent(id.getId(), newPassword));
    }

    private String generateRandomPassword() {
        Random random = new Random();
        int number = random.nextInt();
        return Integer.toHexString(number);
    }

    public void changeEmails(Set<Email> emails) {
        this.emails = new EmailSet(emails);
    }

    public void block() {
        this.blocked = true;
        Events.raise(new MemberBlockedEvent(id.getId()));
    }

    public void unblock() {
        this.blocked = false;
        Events.raise(new MemberUnblockedEvent(id.getId()));
    }

//    public void changePassword(String oldPw, String newPw) {
//        if (!password.match(oldPw)) {
//            throw new IdPasswordNotMatchingException();
//        }
//        this.password = new Password(newPw);
//        Events.raise(new PasswordChangedEvent(id.getId(), newPw));
//    }


    public boolean isBlocked() {
        return blocked;
    }

    public EmailSet getEmails() {
        return emails;
    }

    /*
     * Chapter06
     */
    public void changePassword(String oldPw, String newPw) {
        if (!matchPassword(oldPw)) {
            throw new BadPasswordException();
        }
        setPassword(newPw);
    }

    public boolean matchPassword(String pw) {
        return this.password.match(pw);
    }

    public void setPassword(String newPw) {
        if(isEmpty(newPw)) throw new IllegalArgumentException("no new password");
        this.password  = new Password(newPw);
    }

    public void leave(String pw) {
        if (!matchPassword(pw)) {
            throw new BadPasswordException();
        }
        block();
    }

}
