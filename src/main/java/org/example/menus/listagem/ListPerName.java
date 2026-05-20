package org.example.menus.listagem;

import org.example.menus.listagem.nnseionome.MensagemAuxiliar;
import org.example.menus.serviceTasks.EncerrarTasks;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ListPerName {

    public void listPerName(ArrayList<Task> tarefas, Scanner vs, MensagemAuxiliar mensagem,
                            EncerrarTasks encerrar, TotalList totalList, ArrayList<Task> endTarefas) {
        boolean confirmation = false;

        System.out.println("Informe o nome da tarefa que deseja listar:");
        String nome = vs.nextLine().trim();

        for (Task tarefa : tarefas) {
            if (tarefa.titulo().toLowerCase(Locale.ROOT).startsWith(nome.toLowerCase(Locale.ROOT))) {
                System.out.println("Tarefa encontrada: ");
                System.out.println(tarefa.formatada("pendente"));
                confirmation = true;
            }
        }

        if (!confirmation) {
            System.out.println("Tarefa '" + nome + "' não encontrada.");
            return;
        }

        System.out.println("""
                Como deseja prosseguir?
                'Verificar' para voltar a lista geral!
                'Menu' para voltar ao menu!
                'Encerrar' para encerrar uma tarefa!""");
        String resposta = vs.nextLine();

        switch (resposta.toLowerCase(Locale.ROOT)) {
            case "verificar" -> totalList.listarTotal(tarefas, vs, mensagem, endTarefas, this, encerrar, totalList);
            case "menu" -> mensagem.transicionList(resposta, vs, this, encerrar, totalList, endTarefas, tarefas);
            case "encerrar" -> encerrar.encerrarTask(tarefas, endTarefas, vs);
            default -> System.out.println("Resposta inválida");
        }
    }
}