package com.gamemoim.demo.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SignUpValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpRequestDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpRequestDto singUpRequestDto = (SignUpRequestDto) target;

        if(accountRepository.existsByEmail((singUpRequestDto).getEmail())){
            errors.rejectValue("email","invalid.email", new Object[]{singUpRequestDto.getEmail()}, "이미 존재하는 이메일입니다");
        }
        if(accountRepository.existsByNickname(singUpRequestDto.getNickname())){
            errors.rejectValue("nickName","invalid.nickName", new Object[]{singUpRequestDto.getNickname()}, "이미 존재하는 닉네임입니다");
        }
    }
}
