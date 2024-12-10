package br.com.tabacaria.sistema.validators;

import br.com.tabacaria.sistema.validators.annotations.ValidateCpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<ValidateCpf, String> {

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        if (cpf == null) return true;

        if (cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        if (cpf.chars().distinct().count() == 1) {
            return false;
        }

        int[] digitos = cpf.chars().map(c -> c - '0').toArray();

        int primeiroDigito = calculateVerifyDigit(digitos, 10);
        int segundoDigito = calculateVerifyDigit(digitos, 11);

        return primeiroDigito == digitos[9] && segundoDigito == digitos[10];
    }

    private int calculateVerifyDigit(int[] digits, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += digits[i] * (pesoInicial - i);
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}
