package org.example.interfaces;


// Interface funcional para criar uma nova tarefa

import java.util.ArrayList;

@FunctionalInterface
public interface Criar {
        void salvarDados(String nome, String descricao, ArrayList<String> tarefas);
}
// A interface Criar é uma interface funcional que define um método abstrato salvarDados, que recebe o nome, a descrição e a lista de tarefas como parâmetros. Essa interface pode ser implementada por classes que desejam fornecer a funcionalidade de criação de tarefas, permitindo que os dados sejam salvos de acordo com a lógica específica de cada implementação.
