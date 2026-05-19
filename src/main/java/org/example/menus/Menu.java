package org.example.menus;

import org.example.menus.listagem.ListPerName;
import org.example.menus.listagem.TaskEncerradas;
import org.example.menus.listagem.TotalList;
import org.example.menus.listagem.nnseionome.MensagemAuxiliar;
import org.example.menus.listagem.nnseionome.Teste;
import org.example.menus.serviceTasks.CriarTasks;
import org.example.menus.serviceTasks.EncerrarTasks;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {

    public void menu (Scanner vs, CriarTasks ct, ArrayList<String> tarefas, TotalList tl,
                      ListPerName lpn, MensagemAuxiliar mensagem, EncerrarTasks encerrar, ArrayList<String> endTarefas, TaskEncerradas taskEncerradas) {

        // Exibição do menu principal para o usuário, onde ele pode escolher entre criar uma tarefa, listar as tarefas ou verificar as tarefas encerradas.
        System.out.println("Bem-vindo ao Gerenciador de Tarefas!");
        System.out.println("1. Criar nova tarefa:");
        System.out.println("2. Listar tarefas:");
        System.out.println("3. Verificar tarefas encerradas:");
        System.out.println("4. Sair:");
        int opcao = vs.nextInt();
        vs.nextLine(); // Consumir a quebra de linha após a leitura do número

        switch (opcao) {
            case 1 -> ct.criarTask(tarefas, vs); // Aqui você pode passar a lista de tarefas e o scanner para criar uma tarefa
            case 2 -> tiposDeListagem(vs, tarefas, tl, lpn, mensagem, encerrar, endTarefas); // Aqui você pode chamar o método para listar as tarefas
            case 3 -> taskEncerradas.mostrarTarefasEncerradas(endTarefas); // Aqui você pode chamar o método para mostrar as tarefas encerradas
            default -> System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
        }

    }
    void tiposDeListagem(Scanner vs, ArrayList<String> tarefas, TotalList tl, ListPerName lpn,
                         MensagemAuxiliar mensagem, EncerrarTasks encerrar, ArrayList<String> endTarefas) {

        // Exibição do menu de opções para listar as tarefas, onde o usuário pode escolher entre listar todas as tarefas ou listar por nome.
        System.out.println("Como deseja listar as tarefas?" +
                "\nDigite 'Total' para listar todas as tarefas!" +
                "\nDigite 'Nome' para listar por nome!");
        String resposta = vs.nextLine();
        switch (resposta.toLowerCase(Locale.ROOT)) {
            case "total" -> {
                    tl.listarTotal(tarefas, vs, mensagem);
            }
            case "nome" -> {
                    lpn.listPerName(tarefas, vs, mensagem, encerrar, tl, endTarefas);
            }
            default -> System.out.println("Resposta inválida");
        }
    }















}
