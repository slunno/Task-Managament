package org.example;

import org.example.menus.creationUser.Manager;
import org.example.menus.creationUser.Users;
import org.example.ui.armazem.XmlStorage;
import org.example.exception.tratamento.TratamentoDeTasks;
import org.example.exception.tratamento.ValidarCadastro;
import org.example.menus.MenuTask;
import org.example.menus.listagem.ListPerName;
import org.example.menus.listagem.TaskEncerradas;
import org.example.menus.listagem.TotalList;
import org.example.menus.listagem.nnseionome.MensagemAuxiliar;
import org.example.menus.serviceTasks.CriarTasks;
import org.example.menus.serviceTasks.EncerrarTasks;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Armazem de tarefas;
        ArrayList<Task> tarefas = new ArrayList<>();
        ArrayList<Task> endTarefas = new ArrayList<>();
        ArrayList<Users> usuarios = new ArrayList<>();
        //Entrada de dados;
        Scanner scanner = new Scanner(System.in);
        //Instâncias;
        CriarTasks criarTasks = new CriarTasks();
        EncerrarTasks encerrarTasks = new EncerrarTasks();
        MensagemAuxiliar mensagem = new MensagemAuxiliar();
        TratamentoDeTasks tratamento = new TratamentoDeTasks();
        TaskEncerradas taskEncerradas = new TaskEncerradas();
        ValidarCadastro validationMenu = new ValidarCadastro();
        Manager manager = new Manager();
        //Listagens;
        ListPerName listPerName = new ListPerName();
        TotalList totalList = new TotalList();

        XmlStorage.carregarAtivas("tarefas.xml", tarefas);
        XmlStorage.carregarEncerradas("encerradas.xml", endTarefas);

        while (true) {
            MenuTask menu = new MenuTask();
            menu.menu(scanner, criarTasks, tarefas, totalList, listPerName, mensagem, encerrarTasks, endTarefas, taskEncerradas, tratamento, manager, usuarios);
        }
    }
}