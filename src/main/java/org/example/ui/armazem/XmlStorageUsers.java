package org.example.ui.armazem;

import org.example.menus.creationUser.Users;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XmlStorageUsers {

    public static void salvarUsuarios(String caminhoArquivo, ArrayList<Users> usuarios) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("users");
            doc.appendChild(raiz);

            for (Users u : usuarios) {
                Element usuario = doc.createElement("usuario");

                Element nome = doc.createElement("nome");
                nome.setTextContent(u.name());

                Element email = doc.createElement("email");
                email.setTextContent(u.email());

                Element senha = doc.createElement("senha");
                senha.setTextContent(u.password());

                Element id = doc.createElement("id");
                id.setTextContent(String.valueOf(u.id()));

                usuario.appendChild(nome);
                usuario.appendChild(email);
                usuario.appendChild(senha);
                usuario.appendChild(id);
                raiz.appendChild(usuario);
            }


            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(new DOMSource(doc), new StreamResult(new File(caminhoArquivo)));
            System.out.println("Dados de usuários salvos em: " + caminhoArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar XML de usuários: " + e.getMessage());
        }
    }





    public void carregarUsuarios(String caminhoArquivo, ArrayList<Users> usuarios) {
        try {
            File file = new File(caminhoArquivo);
            if (!file.exists()) {
                System.out.println("Arquivo de usuários não encontrado: " + caminhoArquivo);
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element raiz = doc.getDocumentElement();
            NodeList listaUsuarios = raiz.getElementsByTagName("usuario");

            for (int i = 0; i < listaUsuarios.getLength(); i++) {
                Element usuario = (Element) listaUsuarios.item(i);
                String name = usuario.getElementsByTagName("nome").item(0).getTextContent();
                String email = usuario.getElementsByTagName("email").item(0).getTextContent();
                String password = usuario.getElementsByTagName("senha").item(0).getTextContent();
                int id = i + 1; // Gerar ID sequencial com base na posição

                usuarios.add(new Users(name, email, password, id));
            }
            System.out.println("Dados de usuários carregados de: " + caminhoArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao carregar XML de usuários: " + e.getMessage());
        }
    }





}
