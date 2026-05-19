package org.example.interfaces;

// Interface funcional para encerrar uma tarefa

import java.util.ArrayList;

@FunctionalInterface
public interface Encerrar {
        void encerrarTask(String nome, ArrayList<String> tarefas, ArrayList<String> endTarefas);
}
// A interface Encerrar é uma interface funcional que define um método abstrato encerrarTask, que recebe o nome da tarefa a ser encerrada, a lista de tarefas ativas e a lista de tarefas encerradas como parâmetros. Essa interface pode ser implementada por classes que desejam fornecer a funcionalidade de encerramento de tarefas, permitindo que as tarefas sejam movidas da lista de tarefas ativas para a lista de tarefas encerradas de acordo com a lógica específica de cada implementação.