package br.com.tabacaria.sistema.validators.annotations;

import br.com.tabacaria.sistema.validators.BirthDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateBirthDate {

    String message() default "Usuário precisa ter 18 anos completos.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
