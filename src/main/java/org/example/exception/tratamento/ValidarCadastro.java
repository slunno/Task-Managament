package org.example.exception.tratamento;

import org.example.exception.mensagem.ExceptionValidationMessage;

public class ValidarCadastro {

    public void validarResposta(String name, String email, String password) {
        if (name == null || name.trim().isEmpty())
            throw new ExceptionValidationMessage("O nome do usuário não pode ser vazio.");
        if (email == null || email.trim().isEmpty())
            throw new ExceptionValidationMessage("O email do usuário não pode ser vazio.");
        if (password == null || password.trim().isEmpty())
            throw new ExceptionValidationMessage("A senha do usuário não pode ser vazia.");
        // Acima verifica se as informações estão vazias ou nulas, e lança uma exceção personalizada com uma mensagem específica para cada caso.
        if (name.matches(".*[!@#$%^&*(),.?\":{}|<>].*") || email.matches(".*[!#$%^&*(),?\":{}|<>].*") || password.matches(".*[!#$%^&*(),?\":{}|<>].*"))
            throw new ExceptionValidationMessage("As informações do usuário não podem conter caracteres inválidos.");
        // Verifica se as informações do usuário contêm caracteres inválidos, e lança uma exce
        if (!email.contains("@") && !email.contains("."))
            throw new ExceptionValidationMessage("O email do usuário deve conter um formato válido.");

    }



}
