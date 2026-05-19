package org.example;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
import org.example.menus.Menu;
import org.example.menus.listagem.ListPerName;
import org.example.menus.listagem.TaskEncerradas;
import org.example.menus.listagem.TotalList;
import org.example.menus.listagem.nnseionome.MensagemAuxiliar;
import org.example.menus.serviceTasks.CriarTasks;
import org.example.menus.serviceTasks.EncerrarTasks;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static  void main() {

        // Criação de listas para armazenar as tarefas, tarefas encerradas e outras informações relevantes.
        ArrayList<String> tarefas = new ArrayList<>();
        ArrayList<String> endTarefas = new ArrayList<>();

        // Instanciação de objetos para as diferentes funcionalidades do programa, como criação de tarefas, listagem, encerramento, etc.
        ListPerName listPerName = new ListPerName();
        TaskEncerradas taskEncerradas = new TaskEncerradas();
        TotalList totalList = new TotalList();
        CriarTasks criarTasks = new CriarTasks();
        EncerrarTasks encerrarTasks = new EncerrarTasks();
        MensagemAuxiliar mensagem = new MensagemAuxiliar();
        Scanner scanner = new Scanner(System.in);



        // Loop infinito para manter o programa em execução até que o usuário decida encerrar.
        while (true) {
            Menu menu = new Menu();
            menu.menu(scanner, criarTasks, tarefas, totalList, listPerName, mensagem, encerrarTasks, endTarefas, taskEncerradas);
        }


    }
}
