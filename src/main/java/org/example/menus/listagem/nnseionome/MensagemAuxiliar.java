package org.example.menus.listagem.nnseionome;

import org.example.interfaces.TrasicionList;

import java.util.Locale;
import java.util.Scanner;

public class MensagemAuxiliar implements TrasicionList {

    @Override
    public void transicionList(String resposta, Scanner vs) {
        // Método para transicionar entre as opções de voltar ao menu ou verificar uma tarefa específica, utilizando um switch para determinar a ação a ser tomada com base na resposta do usuário.
        switch (resposta.toLowerCase(Locale.ROOT)) {
            case "menu":
                System.out.println("Voltando ao Menu....");
                System.out.println("Pressione Enter para continuar...");
                vs.nextLine();
                break;
            case "verificar":

                break;
            default:
                System.out.println("Resposta inválida");
        }
    }

}
