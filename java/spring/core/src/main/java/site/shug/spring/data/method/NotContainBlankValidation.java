package site.shug.spring.data.method;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * NotContainBlank的具体规则
 */
public class NotContainBlankValidation implements ConstraintValidator<NotContainBlank, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.contains(" ")) {
            // 默认提示
            String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
            System.out.println("isValid: " + defaultConstraintMessageTemplate);
            // 禁用默认提示
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("自定义提示");
            return false;
        }
        return true;
    }
}
