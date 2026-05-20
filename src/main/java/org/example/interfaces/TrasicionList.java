package org.example.interfaces;

import org.example.menus.listagem.ListPerName;
import org.example.menus.listagem.TotalList;
import org.example.menus.serviceTasks.EncerrarTasks;
import org.example.menus.serviceTasks.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

@FunctionalInterface
public interface TrasicionList {
    void transicionList(String resposta, Scanner vs, ListPerName listPerName, EncerrarTasks encerrar,
                        TotalList totalList, ArrayList<Task> endTarefas, ArrayList<Task> tarefas);
}
// A interface TrasicionList é uma interface funcional que define
