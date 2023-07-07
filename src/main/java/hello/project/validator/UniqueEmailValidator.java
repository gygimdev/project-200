package hello.project.validator;

import hello.project.repository.MemberRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final MemberRepository memberRepository;

    public UniqueEmailValidator(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /** 이메일이 유일한지 검사
     *  email 이 null 이 아니고 memberRepo 조회 값이 false 일 경우 true 를 리턴
     * @param email 검사할 유저의 의메일
     * @param context context in which the constraint is evaluated
     * @return true(유일)/false(중복)
     */
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !memberRepository.existsByEmail(email);
    }
}
