package org.example.menus.serviceTasks;

import org.example.ui.armazem.XmlStorage;
import org.example.exception.mensagem.SizeIfExceptionCharacters;
import org.example.exception.tratamento.TratamentoDeTasks;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class CriarTasks {

    public void criarTask(ArrayList<Task> tarefas, Scanner vs, TratamentoDeTasks tratamento) {
        System.out.println("Digite o nome da tarefa: (30 caracteres no máximo)");
        String titulo = vs.nextLine();

        System.out.println("Digite a descrição da tarefa: (100 caracteres no máximo)");
        String descricao = vs.nextLine();

        try {
            tratamento.verificarTituloAndDescription(titulo, descricao);
            tarefas.add(new Task(titulo, descricao));
            XmlStorage.salvarAtivas("tarefas.xml", tarefas);
        } catch (SizeIfExceptionCharacters e) {
            System.out.println("Erro ao criar tarefa: " + e.getMessage());
            return;
        }
        System.out.println("Tarefa criada com sucesso!");
        System.out.println("============================================");
    }
}