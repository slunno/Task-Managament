package org.example.menus.serviceTasks.task;

import java.util.ArrayList;
import java.util.List;

public record Task(String titulo, String descricao) {

    public String formatada(String status) {
        int larguraInterna = 42; // ajuste como preferir

        List<String> linhas = new ArrayList<>();
        linhas.addAll(wrapCampo("Status", status, larguraInterna));
        linhas.addAll(wrapCampo("Titulo", titulo, larguraInterna));
        linhas.addAll(wrapCampo("Descricao", descricao, larguraInterna));

        StringBuilder sb = new StringBuilder();
        sb.append("┌").append("─".repeat(larguraInterna + 2)).append("┐\n");
        for (String linha : linhas) {
            sb.append("│ ").append(padRight(linha, larguraInterna)).append(" │\n");
        }
        sb.append("└").append("─".repeat(larguraInterna + 2)).append("┘");
        return sb.toString();
    }

    private static List<String> wrapCampo(String rotulo, String valor, int largura) {
        if (valor == null) valor = "";

        String prefixoPrimeiraLinha = rotulo + " : ";
        String prefixoDemaisLinhas = " ".repeat(prefixoPrimeiraLinha.length());

        List<String> partesValor = wrapTexto(valor, largura - prefixoPrimeiraLinha.length());
        List<String> resultado = new ArrayList<>();

        if (partesValor.isEmpty()) {
            resultado.add(prefixoPrimeiraLinha);
            return resultado;
        }

        resultado.add(prefixoPrimeiraLinha + partesValor.get(0));
        for (int i = 1; i < partesValor.size(); i++) {
            resultado.add(prefixoDemaisLinhas + partesValor.get(i));
        }
        return resultado;
    }

    private static List<String> wrapTexto(String texto, int larguraMax) {
        List<String> linhas = new ArrayList<>();
        if (texto.isBlank()) {
            linhas.add("");
            return linhas;
        }

        String[] palavras = texto.split("\\s+");
        StringBuilder atual = new StringBuilder();

        for (String palavra : palavras) {
            if (palavra.length() > larguraMax) {
                if (atual.length() > 0) {
                    linhas.add(atual.toString());
                    atual.setLength(0);
                }
                int i = 0;
                while (i < palavra.length()) {
                    int fim = Math.min(i + larguraMax, palavra.length());
                    linhas.add(palavra.substring(i, fim));
                    i = fim;
                }
                continue;
            }

            if (atual.length() == 0) {
                atual.append(palavra);
            } else if (atual.length() + 1 + palavra.length() <= larguraMax) {
                atual.append(" ").append(palavra);
            } else {
                linhas.add(atual.toString());
                atual.setLength(0);
                atual.append(palavra);
            }
        }

        if (atual.length() > 0) {
            linhas.add(atual.toString());
        }

        return linhas;
    }

    private static String padRight(String texto, int tamanho) {
        if (texto.length() >= tamanho) return texto;
        return texto + " ".repeat(tamanho - texto.length());
    }
}