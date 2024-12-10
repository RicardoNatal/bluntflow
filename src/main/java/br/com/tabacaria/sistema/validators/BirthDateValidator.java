package br.com.tabacaria.sistema.validators;

import br.com.tabacaria.sistema.validators.annotations.ValidateBirthDate;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class BirthDateValidator implements ConstraintValidator<ValidateBirthDate, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        if (birthDate == null) return true;

        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(birthDate, dataAtual);

        return periodo.getYears() >= 18;
    }

}
