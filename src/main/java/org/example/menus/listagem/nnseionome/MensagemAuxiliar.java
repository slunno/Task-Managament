package org.example.menus.listagem.nnseionome;

import org.example.interfaces.TrasicionList;
import org.example.menus.listagem.ListPerName;
import org.example.menus.listagem.TotalList;
import org.example.menus.serviceTasks.EncerrarTasks;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MensagemAuxiliar implements TrasicionList {

    @Override
    public void transicionList(String resposta, Scanner vs, ListPerName listPerName, EncerrarTasks encerrar,
                               TotalList totalList, ArrayList<Task> endTarefas, ArrayList<Task> tarefas) {
        // Método para transicionar entre as opções de voltar ao menu ou verificar uma tarefa específica, utilizando um switch para determinar a ação a ser tomada com base na resposta do usuário.
        switch (resposta.toLowerCase(Locale.ROOT)) {
            case "menu":
                System.out.println("Voltando ao Menu....");
                System.out.println("Pressione Enter para continuar...");
                vs.nextLine();
                break;
            case "verificar":
                listPerName.listPerName(tarefas, vs, this, encerrar, totalList, endTarefas);
                break;
            default:
                System.out.println("Resposta inválida");
        }
    }

}
