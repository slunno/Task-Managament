package org.example.menus.listagem;

import org.example.menus.listagem.nnseionome.MensagemAuxiliar;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Consumer;

public class TotalList {

        public void listarTotal(ArrayList<String> tarefas, Scanner vs, MensagemAuxiliar mensagem) {
            // Método para listar todas as tarefas, verificando se a lista de tarefas está vazia e, caso contrário, exibindo o total de tarefas e os detalhes de cada tarefa.
                if (tarefas.isEmpty()) {
                    // Verifica se a lista de tarefas está vazia e exibe uma mensagem informando que nenhuma tarefa foi encontrada.
                        System.out.println("Nenhuma tarefa encontrada.");
                        return;
                } else {
                    // Exibe o total de tarefas e os detalhes de cada tarefa, utilizando um Consumer para imprimir as informações de forma formatada.
                        System.out.println("Total de tarefas: " + tarefas.size());
                        Consumer<String> imprimitorTarefas = tarefa -> System.out.println("Status: Pendente\n- " + tarefa + "\n===============================");
                        System.out.println("=====Tarefas=====");
                        tarefas.forEach(imprimitorTarefas);
                }

                // Após listar as tarefas, oferece ao usuário a opção de voltar ao menu ou verificar uma tarefa específica, solicitando a resposta do usuário e chamando o método correspondente para prosseguir.
            System.out.println("Deseja voltar ao Menu ou verificar uma tarefa específica?\n" +
                    "(Digite 'Menu' para voltar ou 'Verificar' para verificar uma tarefa específica)");
            String resp = vs.nextLine();
            mensagem.transicionList(resp, vs);


        }








}
