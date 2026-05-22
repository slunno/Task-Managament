package org.example.menus.creationUser;

import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    public void login(Scanner vs, ArrayList<Users> usersList) {

        System.out.println("Faça o login para acessar suas tarefas.");
        System.out.println("Digite o Id do seu usuário: ");
        int id = vs.nextInt();
        vs.nextLine(); // Consumir a quebra de linha após nextInt()

        Users usuarioEncontrado = null;
        for (Users user : usersList) {
            if (user.id() == id) {
                usuarioEncontrado = user;
                break;
            }
        }

        if (usuarioEncontrado != null) {
            System.out.println("Login bem-sucedido! Bem-vindo, " + usuarioEncontrado.name() + "!");
            // Aqui você pode adicionar a lógica para acessar as tarefas do usuário
        } else {
            System.out.println("Usuário não encontrado. Por favor, verifique o Id e tente novamente.");
        }

    }



}
