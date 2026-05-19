package org.example.menus.listagem;

import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskEncerradas {

    public void mostrarTarefasEncerradas(ArrayList<String> endTarefas) {
        // Método para mostrar as tarefas encerradas, verificando se a lista de tarefas encerradas está vazia e, caso contrário, exibindo o total de tarefas encerradas e os detalhes de cada tarefa.
        if (endTarefas.isEmpty()) {
            // Verifica se a lista de tarefas encerradas está vazia e exibe uma mensagem informando que nenhuma tarefa foi encerrada.
            System.out.println("Nenhuma tarefa encerrada.");
            System.out.println("================================");
            return;
        } else {
            // Exibe o total de tarefas encerradas e os detalhes de cada tarefa encerrada, utilizando um Consumer para imprimir as informações de forma formatada.
            System.out.println("Total de tarefas encerradas: " + endTarefas.size());
            System.out.println("================================");
            Consumer<String> imprimirEndTask = endTarefa -> System.out.println("Status: Pendente\n- " + endTarefa + "\n===============================");
            System.out.println("Tarefas Encerradas:");
            endTarefas.forEach(imprimirEndTask);
        }

    }





}
