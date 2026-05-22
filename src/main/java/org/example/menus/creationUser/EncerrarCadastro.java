package org.example.menus.creationUser;

import org.example.ui.armazem.XmlStorageUsers;

import java.util.ArrayList;
import java.util.Scanner;

import static org.example.menus.creationUser.Users.encerrarCadastro;

public class EncerrarCadastro {

    public void encerrarCadastroDoUsuario(Scanner vs, ArrayList<Users> usersList) {

        System.out.println("Encerrando cadastro...");
        System.out.println("Digite o Id do seu usuário: ");
        int id = vs.nextInt();

        encerrarCadastro(usersList, id);



    }







}
