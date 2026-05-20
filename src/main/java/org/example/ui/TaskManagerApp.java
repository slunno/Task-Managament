package org.example.ui;

import org.example.ui.armazem.XmlStorage;
import org.example.exception.mensagem.SizeIfExceptionCharacters;
import org.example.exception.tratamento.TratamentoDeTasks;
import org.example.menus.serviceTasks.task.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * GUI simples em Swing para o TaskManager.
 * - Usa XmlStorage.carregarAtivas / carregarEncerradas
 * - Usa XmlStorage.salvarAtivas / salvarEncerradas
 * - Usa TratamentoDeTasks para validar tamanho de título/descrição
 */
public class TaskManagerApp extends JFrame {

    private final DefaultListModel<Task> activeModel = new DefaultListModel<>();
    private final DefaultListModel<Task> closedModel = new DefaultListModel<>();
    private final JList<Task> activeList = new JList<>(activeModel);
    private final JList<Task> closedList = new JList<>(closedModel);
    private final String arquivoAtivas = "tarefas.xml";
    private final String arquivoEncerradas = "encerradas.xml";
    private final TratamentoDeTasks tratamento = new TratamentoDeTasks();

    public TaskManagerApp() {
        super("TaskManager - Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 560);
        setLocationRelativeTo(null);
        initComponents();
        loadFromXml();
    }

    private void initComponents() {

        // Renderers para mostrar título e descrição claramente
        activeList.setCellRenderer(new TaskCellRenderer());
        closedList.setCellRenderer(new TaskCellRenderer());

        // ===== TEMA ESCURO (cole aqui) =====
        activeList.setBackground(new Color(50, 50, 50));
        activeList.setForeground(new Color(220, 220, 220));
        closedList.setBackground(new Color(50, 50, 50));
        closedList.setForeground(new Color(220, 220, 220));
        // ===== FIM TEMA ESCURO =====

        // Layout principal
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        main.setBackground(new Color(40, 40, 40));
        // Painel de listas
        // rows: serve para criar uma linha, cols: 2 colunas, hgap/vgap: 10px de espaço
        JPanel listsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        listsPanel.add(wrapWithTitle("Tarefas Ativas", new JScrollPane(activeList)));
        listsPanel.add(wrapWithTitle("Tarefas Encerradas", new JScrollPane(closedList)));

        main.add(listsPanel, BorderLayout.CENTER);

        // Botões
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 8));
        JButton btnCreate = new JButton("Criar");
        JButton btnClose = new JButton("Encerrar selecionada");
        JButton btnSave = new JButton("Salvar XML");
        JButton btnReload = new JButton("Recarregar XML");
        JButton btnView = new JButton("Ver Detalhes");

        buttons.add(btnCreate);
        buttons.add(btnClose);
        buttons.add(btnView);
        buttons.add(btnSave);
        buttons.add(btnReload);

        main.add(buttons, BorderLayout.SOUTH);

        // Ações
        btnCreate.addActionListener(e -> criarTarefaDialog());
        btnClose.addActionListener(e -> encerrarTarefaSelecionada());
        btnSave.addActionListener(e -> salvarXml());
        btnReload.addActionListener(e -> loadFromXml());
        btnView.addActionListener(e -> verDetalhesSelecionada());

        // Duplo clique na lista ativa para ver detalhes
        activeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) verDetalhesSelecionada();
            }
        });

        add(main);
    }

    private JPanel wrapWithTitle(String title, Component comp) {
        JPanel p = new JPanel(new BorderLayout());
        JLabel lbl = new JLabel(title);
        lbl.setFont(lbl.getFont().deriveFont(Font.BOLD, 14f));
        p.add(lbl, BorderLayout.NORTH);
        p.add(comp, BorderLayout.CENTER);
        return p;
    }

    private void criarTarefaDialog() {
        JTextField tituloField = new JTextField();
        JTextArea descricaoArea = new JTextArea(6, 30);
        descricaoArea.setLineWrap(true);
        descricaoArea.setWrapStyleWord(true);

        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.add(new JLabel("Titulo (até 30 chars):"), BorderLayout.NORTH);
        panel.add(tituloField, BorderLayout.CENTER);
        panel.add(new JLabel("Descrição (até 100 chars):"), BorderLayout.SOUTH);

        JPanel south = new JPanel(new BorderLayout());
        south.add(new JScrollPane(descricaoArea), BorderLayout.CENTER);
        panel.add(south, BorderLayout.SOUTH);

        int result = JOptionPane.showConfirmDialog(
                this, panel, "Criar Tarefa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText().trim();
            String descricao = descricaoArea.getText().trim();
            try {
                tratamento.verificarTituloAndDescription(titulo, descricao);
                Task t = new Task(titulo, descricao);
                activeModel.addElement(t);
                // salvar automaticamente as ativas
                salvarAtivas();
                JOptionPane.showMessageDialog(this, "Tarefa criada com sucesso!");
            } catch (SizeIfExceptionCharacters ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Validação", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void encerrarTarefaSelecionada() {
        Task selecionada = activeList.getSelectedValue();
        if (selecionada == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma tarefa selecionada.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int ok = JOptionPane.showConfirmDialog(this, "Encerrar a tarefa '" + selecionada.titulo() + "'?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (ok != JOptionPane.YES_OPTION) return;

        activeModel.removeElement(selecionada);
        closedModel.addElement(selecionada);
        salvarXml(); // salva ambos
        JOptionPane.showMessageDialog(this, "Tarefa encerrada.");
    }

    private void verDetalhesSelecionada() {
        Task t = activeList.getSelectedValue();
        if (t == null) t = closedList.getSelectedValue();
        if (t == null) {
            JOptionPane.showMessageDialog(this, "Nenhuma tarefa selecionada.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, t.formatada(activeModel.contains(t) ? "Pendente" : "Encerrada"), "Detalhes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void loadFromXml() {
        try {
            ArrayList<Task> ativas = new ArrayList<>();
            ArrayList<Task> encerradas = new ArrayList<>();
            XmlStorage.carregarAtivas(arquivoAtivas, ativas);
            XmlStorage.carregarEncerradas(arquivoEncerradas, encerradas);

            activeModel.clear();
            closedModel.clear();
            for (Task t : ativas) activeModel.addElement(t);
            for (Task t : encerradas) closedModel.addElement(t);

            JOptionPane.showMessageDialog(this, "Dados carregados.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar XML: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarAtivas() {
        ArrayList<Task> ativas = new ArrayList<>();
        for (int i = 0; i < activeModel.size(); i++) ativas.add(activeModel.get(i));
        XmlStorage.salvarAtivas(arquivoAtivas, ativas);
    }

    private void salvarEncerradas() {
        ArrayList<Task> encerradas = new ArrayList<>();
        for (int i = 0; i < closedModel.size(); i++) encerradas.add(closedModel.get(i));
        XmlStorage.salvarEncerradas(arquivoEncerradas, encerradas);
    }

    private void salvarXml() {
        try {
            salvarAtivas();
            salvarEncerradas();
            JOptionPane.showMessageDialog(this, "XMLs salvos com sucesso.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar XML: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Renderer para cada célula da lista
    private static class TaskCellRenderer extends JPanel implements ListCellRenderer<Task> {
        private final JLabel title = new JLabel();
        private final JLabel descr = new JLabel();

        TaskCellRenderer() {
            setLayout(new BorderLayout(4, 2));
            title.setFont(title.getFont().deriveFont(Font.BOLD, 13f));
            title.setForeground(new Color(220, 220, 220));
            descr.setFont(descr.getFont().deriveFont(Font.PLAIN, 12f));
            descr.setForeground(new Color(180, 180, 180));;
            add(title, BorderLayout.NORTH);
            add(descr, BorderLayout.CENTER);
            setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Task> list, Task value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value != null) {
                title.setText(value.titulo());
                String d = value.descricao();
                if (d.length() > 100) d = d.substring(0, 100) + "…";
                descr.setText("<html><body style='width:380px'>" + escapeHtml(d) + "</body></html>");
            } else {
                title.setText("");
                descr.setText("");
            }

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }

        private String escapeHtml(String s) {
            return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // opcional: look and feel do sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new TaskManagerApp().setVisible(true);
        });
    }
}