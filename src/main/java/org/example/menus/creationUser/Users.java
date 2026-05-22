package org.example.menus.creationUser;

import org.example.ui.armazem.XmlStorageUsers;

import java.util.ArrayList;
import java.util.Scanner;

public record Users(
        String name,
        String email,
        String password,
        int id)
         {



    void creationUser(Users user, ArrayList<Users> usersList) {
        usersList.add(user);
        XmlStorageUsers.salvarUsuarios("users.xml", usersList);
        System.out.println("Usuário criado com sucesso!");
    }
    static void encerrarCadastro(ArrayList<Users> usersList, int id) {

        Users encontrada = null;
        for (Users user : usersList) {
            if (user.id() == id) {
                encontrada = user;
                break;
            }
        }

        if (encontrada == null) {
            System.out.println("Usuário com Id " + id + " não encontrado.");
        } else {
            usersList.remove(encontrada);
            XmlStorageUsers.salvarUsuarios("users.xml", usersList);
            System.out.println("Cadastro do usuário '" + encontrada.name() + "' encerrado com sucesso!");
        }
    }


}
