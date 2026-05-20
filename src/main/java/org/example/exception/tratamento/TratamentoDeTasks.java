package org.example.exception.tratamento;

import org.example.exception.mensagem.SizeIfExceptionCharacters;

public class TratamentoDeTasks {

    public void verificarTituloAndDescription(String titulo, String description) {
        if (titulo == null || titulo.trim().isEmpty())
            throw new SizeIfExceptionCharacters("O título da tarefa não pode ser vazio.");
        if (titulo .length() > 30)
            throw new SizeIfExceptionCharacters("O título da tarefa não pode exceder 30 caracteres.");
        if (description == null || description.trim().isEmpty())
            throw new SizeIfExceptionCharacters("A descrição da tarefa não pode ser vazia.");
        if (description.length() > 100)
            throw new SizeIfExceptionCharacters("A descrição da tarefa não pode exceder 100 caracteres.");
    }







}
