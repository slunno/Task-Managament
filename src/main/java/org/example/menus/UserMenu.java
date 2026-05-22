package org.example.menus;

import org.example.interfaces.Encerrar;
import org.example.menus.creationUser.EncerrarCadastro;
import org.example.menus.creationUser.Manager;
import org.example.menus.creationUser.Users;

import java.util.ArrayList;
import java.util.Scanner;

public class UserMenu {

    public void menuDeUsuarios(Scanner vs, Manager manager, ArrayList<Users> usuarios, EncerrarCadastro encerrarCadastro
                                ) {

        System.out.println("============Menu de Usuários============");
        System.out.println("1. Criar usuário");
        System.out.println("2. Encerrar cadastro");
        System.out.println("3. Acessar sua conta");
        System.out.println("4. Listar usuários");
        System.out.println("5. Voltar ao menu principal");
        String resposta = vs.nextLine();

        try {
            if (!resposta.matches("\\d+")) {
                throw new IllegalArgumentException("Resposta inválida. Digite apenas números.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
            int opcao = Integer.parseInt(resposta);


            switch (opcao) {
                case 1 -> manager.createUser(vs, usuarios);
                case 2 -> encerrarCadastro.encerrarCadastroDoUsuario(vs, usuarios);
                case 3 -> ;
                case 4 -> System.out.println("Opção para listar usuários selecionada.");
                case 5 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 5.");
            }








    }














}
