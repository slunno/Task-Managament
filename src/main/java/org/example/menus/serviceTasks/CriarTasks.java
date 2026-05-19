package org.example.menus.serviceTasks;

import org.example.interfaces.Criar;

import java.util.ArrayList;
import java.util.Scanner;

public class CriarTasks implements Criar {

    public void criarTask(ArrayList<String> tarefas, Scanner vs) {
        // Método para criar uma nova tarefa, solicitando ao usuário o nome e a descrição da tarefa.
        System.out.println("Digite o nome da tarefa:");
        String nome = vs.nextLine();
        System.out.println("Digite a descrição da tarefa:");
        String descricao = vs.nextLine();
        salvarDados(nome, descricao, tarefas);
        System.out.println("============================================");
    }

    @Override
    public void salvarDados(String nome, String descricao, ArrayList<String> tarefas) {
        // Método para a salvar os dados da nova tarefa. (pode ser adaptado para salvar em um banco de dados ou arquivo)
        System.out.println("Criando nova tarefa...");
        System.out.println("Nome: " + nome);
        System.out.println("Descrição: " + descricao);
        // Adicionado a task a lista de tarefas (simulação)
        tarefas.add(nome + ": " + descricao);
    }


}




