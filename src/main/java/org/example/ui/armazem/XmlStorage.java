package org.example.ui.armazem;

import org.example.menus.serviceTasks.task.Task;
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

public class XmlStorage {

    public static void salvarAtivas(String caminhoArquivo, ArrayList<Task> tarefasAtivas) {
        salvarLista(caminhoArquivo, "ativas", tarefasAtivas);
    }

    public static void salvarEncerradas(String caminhoArquivo, ArrayList<Task> tarefasEncerradas) {
        salvarLista(caminhoArquivo, "encerradas", tarefasEncerradas);
    }



    private static void salvarLista(String caminhoArquivo, String tagLista, ArrayList<Task> tarefas) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element raiz = doc.createElement("taskManager");
            doc.appendChild(raiz);

            Element lista = doc.createElement(tagLista);
            raiz.appendChild(lista);

            for (Task t : tarefas) {
                Element tarefa = doc.createElement("tarefa");

                Element titulo = doc.createElement("titulo");
                titulo.setTextContent(t.titulo());

                Element descricao = doc.createElement("descricao");
                descricao.setTextContent(t.descricao());

                tarefa.appendChild(titulo);
                tarefa.appendChild(descricao);
                lista.appendChild(tarefa);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            transformer.transform(new DOMSource(doc), new StreamResult(new File(caminhoArquivo)));
            System.out.println("Dados salvos em: " + caminhoArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao salvar XML: " + e.getMessage());
        }
    }

    public static void carregarAtivas(String caminhoArquivo, ArrayList<Task> tarefasAtivas) {
        carregarLista(caminhoArquivo, "ativas", tarefasAtivas);
    }

    public static void carregarEncerradas(String caminhoArquivo, ArrayList<Task> tarefasEncerradas) {
        carregarLista(caminhoArquivo, "encerradas", tarefasEncerradas);
    }

    private static void carregarLista(String caminhoArquivo, String tagLista, ArrayList<Task> destino) {
        try {
            File arquivo = new File(caminhoArquivo);
            if (!arquivo.exists()) {
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(arquivo);
            doc.getDocumentElement().normalize();

            destino.clear();

            NodeList listas = doc.getElementsByTagName(tagLista);
            if (listas.getLength() > 0) {
                Element lista = (Element) listas.item(0);
                NodeList tarefas = lista.getElementsByTagName("tarefa");

                for (int i = 0; i < tarefas.getLength(); i++) {
                    Element tarefa = (Element) tarefas.item(i);
                    String titulo = tarefa.getElementsByTagName("titulo").item(0).getTextContent();
                    String descricao = tarefa.getElementsByTagName("descricao").item(0).getTextContent();
                    destino.add(new Task(titulo, descricao));
                }
            }

            System.out.println("Dados carregados de: " + caminhoArquivo);
        } catch (Exception e) {
            System.out.println("Erro ao carregar XML: " + e.getMessage());
        }
    }
}