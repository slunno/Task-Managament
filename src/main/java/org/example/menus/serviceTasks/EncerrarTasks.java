package org.example.menus.serviceTasks;

import org.example.armazem.XmlStorage;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class EncerrarTasks {

    public void encerrarTask(ArrayList<Task> tarefas, ArrayList<Task> endTarefas, Scanner vs) {
        System.out.println("Confirmar o nome da tarefa que deseja encerrar:");
        String nomeTarefa = vs.nextLine().trim();

        Task encontrada = null;
        for (Task tarefa : tarefas) {
            if (tarefa.titulo().equalsIgnoreCase(nomeTarefa)) {
                encontrada = tarefa;
                break;
            }
        }

        if (encontrada != null) {
            tarefas.remove(encontrada);
            endTarefas.add(encontrada);

            XmlStorage.salvarAtivas("tarefas.xml", tarefas);
            XmlStorage.salvarEncerradas("encerradas.xml", endTarefas);

            System.out.println("Tarefa '" + nomeTarefa + "' encerrada com sucesso!");
        } else {
            System.out.println("Tarefa '" + nomeTarefa + "' não encontrada.");
        }
    }
}