package org.example.exception.tratamento;

import org.example.exception.mensagem.ExceptionValidationMessage;

public class ValidationMenu {

    public void validarResposta(int resposta) {
        if (resposta < 1 || resposta > 5)
            throw new ExceptionValidationMessage("Resposta inválida. Por favor, escolha uma opção entre 1 e 5.");

    }



}
