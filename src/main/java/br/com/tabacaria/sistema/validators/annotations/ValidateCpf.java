package br.com.tabacaria.sistema.validators.annotations;

import br.com.tabacaria.sistema.validators.CpfValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CpfValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateCpf {

    String message() default "Informe um CPF válido.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
