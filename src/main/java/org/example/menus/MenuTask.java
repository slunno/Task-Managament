package org.example.menus;

import org.example.menus.creationUser.Manager;
import org.example.menus.creationUser.Users;
import org.example.exception.tratamento.TratamentoDeTasks;
import org.example.menus.listagem.ListPerName;
import org.example.menus.listagem.TaskEncerradas;
import org.example.menus.listagem.TotalList;
import org.example.menus.listagem.nnseionome.MensagemAuxiliar;
import org.example.menus.serviceTasks.CriarTasks;
import org.example.menus.serviceTasks.EncerrarTasks;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MenuTask {

    public void menu(Scanner vs, CriarTasks ct, ArrayList<Task> tarefas, TotalList tl,
                     ListPerName lpn, MensagemAuxiliar mensagem, EncerrarTasks encerrar,
                     ArrayList<Task> endTarefas, TaskEncerradas taskEncerradas,
                     TratamentoDeTasks tratamento, Manager manager, ArrayList<Users> usuarios) {

        System.out.println("Bem-vindo ao Gerenciador de Tarefas!");
        System.out.println("1. Criar nova tarefa:");
        System.out.println("2. Listar tarefas:");
        System.out.println("3. Verificar tarefas encerradas:");
        System.out.println("4. Sair:");
        String opcao1 = vs.nextLine();

        try {
            if (!opcao1.matches("\\d+")) {
                throw new IllegalArgumentException("Resposta inválida. Digite apenas números.");
            }

            int opcao = Integer.parseInt(opcao1);

            switch (opcao) {
                case 1 -> ct.criarTask(tarefas, vs, tratamento);
                case 2 -> tiposDeListagem(vs, tarefas, tl, lpn, mensagem, encerrar, endTarefas);
                case 3 -> taskEncerradas.mostrarTarefasEncerradas(endTarefas);
                case 4 -> {
                    manager.createUser(vs, usuarios);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
        void tiposDeListagem (Scanner vs, ArrayList < Task > tarefas, TotalList tl, ListPerName lpn,
                MensagemAuxiliar mensagem, EncerrarTasks encerrar, ArrayList < Task > endTarefas){

            System.out.println("Como deseja listar as tarefas?" +
                    "\nDigite 'Total' para listar todas as tarefas!" +
                    "\nDigite 'Nome' para listar por nome!");
            String resposta = vs.nextLine();

            switch (resposta.toLowerCase(Locale.ROOT)) {
                case "total" -> tl.listarTotal(tarefas, vs, mensagem, endTarefas, lpn, encerrar, tl);
                case "nome" -> lpn.listPerName(tarefas, vs, mensagem, encerrar, tl, endTarefas);
                default -> System.out.println("Resposta inválida");
            }
        }

}
