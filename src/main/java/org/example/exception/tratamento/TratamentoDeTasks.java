package org.example.exception.tratamento;

import org.example.enem.ValuesCaracteres;
import org.example.exception.mensagem.SizeIfExceptionCharacters;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;

public class TratamentoDeTasks {

    public void verificarTituloAndDescription(String titulo, String description) {
        if (ValuesCaracteres.MAX_CHARACTERS_TITLE.getValue() < 1 || titulo == null || titulo.trim().isEmpty())
            throw new SizeIfExceptionCharacters("O título da tarefa não pode ser vazio.");
        if(ValuesCaracteres.MAX_CHARACTERS_TITLE.getValue() > 30)
            throw new SizeIfExceptionCharacters("O título da tarefa não pode exceder 30 caracteres.");
        if (ValuesCaracteres.MAX_CHARACTERS_DESCRIPTION.getValue() < 1 || description == null || description.trim().isEmpty())
            throw new SizeIfExceptionCharacters("A descrição da tarefa não pode ser vazia.");
        if (ValuesCaracteres.MAX_CHARACTERS_DESCRIPTION.getValue() > 100)
            throw new SizeIfExceptionCharacters("A descrição da tarefa não pode exceder 100 caracteres.");
    }







}
