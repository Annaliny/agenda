package ufpb.com.br;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collection;

public class JanelaPrincipal extends JFrame {

    private AgendaInterface agenda;

    private JTextField nomeField;
    private JTextField diaField;
    private JTextField mesField;
    private JTextArea outputArea;

    public JanelaPrincipal(AgendaInterface agenda) {
        this.agenda = agenda;

        setTitle("Agenda de Contatos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Nome do Contato
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nome do Contato:"), gbc);

        gbc.gridx = 1;
        nomeField = new JTextField(15);
        panel.add(nomeField, gbc);

        // Dia de Aniversário
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Dia de Aniversário:"), gbc);

        gbc.gridx = 1;
        diaField = new JTextField(5);
        panel.add(diaField, gbc);

        // Mês de Aniversário
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Mês de Aniversário:"), gbc);

        gbc.gridx = 1;
        mesField = new JTextField(5);
        panel.add(mesField, gbc);

        // Botões de ação
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;

        JButton addButton = new JButton("Adicionar Contato");
        addButton.addActionListener(new AddButtonListener());
        panel.add(addButton, gbc);

        gbc.gridy = 4;
        JButton removeButton = new JButton("Remover Contato");
        removeButton.addActionListener(new RemoveButtonListener());
        panel.add(removeButton, gbc);

        gbc.gridy = 5;
        JButton searchButton = new JButton("Pesquisar Aniversariantes");
        searchButton.addActionListener(new SearchButtonListener());
        panel.add(searchButton, gbc);

        gbc.gridy = 6;
        JButton saveButton = new JButton("Salvar Dados");
        saveButton.addActionListener(new SaveButtonListener());
        panel.add(saveButton, gbc);

        gbc.gridy = 7;
        JButton loadButton = new JButton("Recuperar Dados");
        loadButton.addActionListener(new LoadButtonListener());
        panel.add(loadButton, gbc);

        // Área de saída para mostrar os resultados
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    // Listeners de ação...
    // Listener para adicionar um contato
    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            try {
                int dia = Integer.parseInt(diaField.getText());
                int mes = Integer.parseInt(mesField.getText());
                Contato contato = new Contato(nome, dia, mes);
                agenda.adicionarContato(contato);
                outputArea.append("Contato adicionado: " + nome + "\n");
            } catch (NumberFormatException ex) {
                outputArea.append("Dia e mês devem ser números.\n");
            }
        }
    }

    // Listener para remover um contato
    private class RemoveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = nomeField.getText();
            try {
                agenda.removerContato(nome);
                outputArea.append("Contato removido: " + nome + "\n");
            } catch (ContatoInexistenteException ex) {
                outputArea.append(ex.getMessage() + "\n");
            }
        }
    }

    // Listener para pesquisar aniversariantes
    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int dia = Integer.parseInt(diaField.getText());
                int mes = Integer.parseInt(mesField.getText());
                Collection<Contato> aniversariantes = agenda.pesquisaAniversariantes(dia, mes);
                outputArea.append("Aniversariantes no dia " + dia + "/" + mes + ":\n");
                for (Contato contato : aniversariantes) {
                    outputArea.append("- " + contato.getNome() + "\n");
                }
                if (aniversariantes.isEmpty()) {
                    outputArea.append("Nenhum aniversariante encontrado.\n");
                }
            } catch (NumberFormatException ex) {
                outputArea.append("Dia e mês devem ser números.\n");
            }
        }
    }

    // Listener para salvar dados
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                agenda.salvarDados();
                outputArea.append("Dados salvos com sucesso.\n");
            } catch (IOException ex) {
                outputArea.append("Erro ao salvar dados: " + ex.getMessage() + "\n");
            }
        }
    }

    // Listener para recuperar dados
    private class LoadButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                agenda.recuperarDados();
                outputArea.append("Dados recuperados com sucesso.\n");
            } catch (IOException ex) {
                outputArea.append("Erro ao recuperar dados: " + ex.getMessage() + "\n");
            }
        }
    }

    public static void main(String[] args) {
        AgendaInterface agenda = new Agenda("contatos.txt");
        JanelaPrincipal janela = new JanelaPrincipal(agenda);
        janela.setVisible(true);
    }
}
