package com.myshop.member.ui;

import com.myshop.lock.EmptyPropertyException;
import com.myshop.lock.InvalidPropertyException;
import com.myshop.member.DuplicateIdException;
import com.myshop.member.command.domain.JoinRequest;
import com.myshop.member.command.domain.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;
//    @PostMapping("/member/join")
//    public String join(JoinRequest joinRequest, Errors errors) {
//        try {
//            joinService.join(joinRequest);
//            return successView();
//        } catch(EmptyPropertyException ex) {
//            errors.rejectValue(ex.getPropertyName(), "empty");
//            return formView();
//        } catch(InvalidPropertyException ex) {
//            errors.rejectValue(ex.getPropertyName(), "invalid");
//            return formView();
//        } catch(DuplicateIdException ex) {
//            errors.rejectValue(ex.getPropertyName(), "duplicated");
//            return formView();
//        }
//    }

    @PostMapping("/member/join")
    public String join(JoinRequest joinRequest, Errors errors) {
        new JoinRequestValidator().validate(joinRequest, errors);
        if(errors.hasErrors()) return formView();

        try {
            joinService.join(joinRequest);
            return successView();
        } catch(DuplicateIdException ex) {
            errors.rejectValue(ex.getPropertyName(), "duplicated");
            return formView();
        }
    }

    private String successView() {
        return "change-password-success";
    }

    private String formView() {
        return "change-password-form";
    }
}
