package org.example.menus.creationUser;

import org.example.exception.mensagem.ExceptionValidationMessage;
import org.example.exception.tratamento.ValidarCadastro;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager {

   public void createUser(Scanner vs, ArrayList<Users> usersList) {
       System.out.println("Digite o seu nome: ");
       String name = vs.nextLine();
       System.out.println("Digite o seu); email: ");
       String email = vs.nextLine();
       String password = verificarSenha(vs);
       int id = usersList.size() + 1;



       try {
           ValidarCadastro vl = new ValidarCadastro();
           Users user = new Users(name, email, password, id);
           vl.validarResposta(name, email, password);
           user.creationUser(user, usersList);
       } catch (ExceptionValidationMessage e) {
           System.out.println("Erro ao criar usuário: " + e.getMessage());
       }






   }

   String verificarSenha (Scanner vs) {
       boolean confirmation = true;
       String password = "";
       while (confirmation) {
           System.out.println("Digite a sua senha: ");
           password = vs.nextLine();
           System.out.println("Confirme a sua senha: ");
           String confirmPassword = vs.nextLine();

           if (password.equalsIgnoreCase(confirmPassword)) {
               System.out.println("Senha confirmada com sucesso!");
               confirmation = false;
           } else {
               System.out.println("As senhas não coincidem. Por favor, tente novamente.");
           }
       }
       return password;
   }



}



