package com.myshop.member.command.domain;

import com.myshop.lock.EmptyPropertyException;
import com.myshop.lock.InvalidPropertyException;
import com.myshop.member.DuplicateIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final MemberRepository memberRepository;
    @Transactional
    public void join(JoinRequest joinRequest) {
        
        checkEmpty(joinRequest.getMemberId().getId(), "id");
        checkEmpty(joinRequest.getName(), "name");
        checkEmpty(joinRequest.getPassword(), "password");

        if(!joinRequest.getPassword().equals(joinRequest.getConfirmPassword())) {
            throw new InvalidPropertyException("confirmPassword");
        }
    }

    private void checkEmpty(String value, String propertyName) {

        if (value == null || value.isEmpty()) {
            throw new EmptyPropertyException(propertyName + " is empty");
        }
    }

    private void checkDuplicatedId(MemberId id) {
        int count = memberRepository.findById(id);
        if(count > 0) throw new DuplicateIdException();
    }
}
