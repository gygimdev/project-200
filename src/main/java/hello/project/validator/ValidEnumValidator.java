package hello.project.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, String> {
    //<유효성검증에 사용되는 어노테이션 타입, 유효성을 검증해야하는 대상>

    private Class<? extends Enum<?>> enumClass; // enum 을 담을 수 있는 자료형

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // Enum 상수 값들을 가져와서 유효성 검사
        Enum<?>[] enumValues = enumClass.getEnumConstants();
        for (Enum<?> enumValue : enumValues) {
            if (enumValue.equals(value)) {
                return true; // 유효한 값이므로 true 반환
            }
        }

        return false; // 유효하지 않은 값이므로 false 반환
    }
}