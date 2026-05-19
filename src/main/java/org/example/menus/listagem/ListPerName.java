package org.example.menus.listagem;

import org.example.menus.listagem.nnseionome.MensagemAuxiliar;
import org.example.menus.serviceTasks.EncerrarTasks;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ListPerName {

    public void listPerName(ArrayList<String> tarefas, Scanner vs, MensagemAuxiliar mensagem, EncerrarTasks encerrar, TotalList totalList, ArrayList<String> endTarefas) {
        boolean confirmation = false;

        // Método para listar as tarefas por nome, solicitando ao usuário o nome da tarefa que deseja listar e exibindo as tarefas correspondentes.
        System.out.println("Informe o nome da tarefa que deseja listar:");
        String nome = vs.nextLine();

        for (String tarefa : tarefas) {
            // Verifica se o nome da tarefa começa com o nome informado pelo usuário, ignorando diferenças de maiúsculas e minúsculas.
            if (tarefa.toLowerCase(Locale.ROOT).startsWith(nome.toLowerCase(Locale.ROOT))) {
                // Se a tarefa corresponder, exibe o nome da tarefa encontrada.
                for (String y : tarefas) {
                    String nomeTask = tarefa.split(":")[0];
                    System.out.println("Tarafe encontrada: " + nomeTask);
                }
                confirmation = true;
            }
        }


        if (confirmation) {
            // Se a tarefa for encontrada, exibe opções para o usuário escolher como prosseguir, como verificar a lista geral, voltar ao menu ou encerrar uma tarefa.
            System.out.println("Como deseja prosseguir?" +
                    "'Verificar' para voltar a lista geral!" +
                    "'Menu' para voltar ao menu!" +
                    "'Encerrar' para encerrar uma tarefa!");
            String resposta = vs.nextLine();

            switch (resposta.toLowerCase(Locale.ROOT)){
                // Dependendo da resposta do usuário, chama o método correspondente para listar todas as tarefas, voltar ao menu ou encerrar uma tarefa.
                case "verificar" -> totalList.listarTotal(tarefas, vs, mensagem);
                case "menu" -> mensagem.transicionList(resposta, vs);
                case "encerrar" -> encerrar.encerrarTask(nome, tarefas, endTarefas);
                default -> System.out.println("Resposta inválida");
            }

        }




    }








}
