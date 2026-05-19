package org.example.menus.serviceTasks;

import org.example.interfaces.Encerrar;

import java.util.ArrayList;
import java.util.Scanner;

public class EncerrarTasks implements Encerrar {

    @Override
    public void encerrarTask(String nome, ArrayList<String> tarefas, ArrayList<String> endTarefas) {
        Scanner scanner = new Scanner(System.in);

        // Método para encerrar uma tarefa, solicitando ao usuário o nome da tarefa que deseja encerrar e confirmando a ação.
        System.out.println("Confirmar o nome da tarefa que deseja encerrar:");
        String nomeTarefa = scanner.nextLine();

        if (tarefas.contains(nomeTarefa)) {
            endTarefas.add(nomeTarefa);
            tarefas.remove(nomeTarefa);
            System.out.println("Tarefa '" + nomeTarefa + "' encerrada com sucesso!");
        } else {
            System.out.println("Tarefa '" + nomeTarefa + "' não encontrada.");
        }



    }



}
