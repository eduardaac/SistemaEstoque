package view;

import controller.AdicionarProduto;
import controller.RemoverProduto;
import controller.ClonarProduto;
import exception.OpcaoException;
import exception.QuantidadeException;
import exception.ValorException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaSistemaEstoque {

    private JFrame tela;
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
    private JTextField tfNome;
    private JTextField tfValor;
    private JTextField tfQuantidade;
    private JTextField tfDescricao;
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    public void desenha() {
        tela = new JFrame("Produtos");
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(new BorderLayout());
        desenhaTabela();
        desenhaFormulario();
        tela.pack();
        tela.setVisible(true);
    }

    private void desenhaTabela() {
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Valor");
        modeloTabela.addColumn("Quantidade");
        modeloTabela.addColumn("Descrição");

        tabelaProdutos = new JTable(modeloTabela);
        JScrollPane painelRolagem = new JScrollPane(tabelaProdutos);

        JPanel painelTabela = new JPanel(new BorderLayout());
        painelTabela.setBorder(BorderFactory.createEmptyBorder(0, 1, H_GAP, H_GAP));
        painelTabela.add(painelRolagem, BorderLayout.CENTER);

        tela.getContentPane().add(painelTabela, BorderLayout.CENTER);
    }

    private void desenhaFormulario() {
        JPanel painelFormulario = new JPanel();
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Produto"));
        painelFormulario.setLayout(new GridBagLayout());
        painelFormulario.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 1, V_GAP, H_GAP);

        JLabel lblNome = new JLabel("Nome");
        tfNome = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 0;
        painelFormulario.add(lblNome, gbc);
        gbc.gridx = 1;
        painelFormulario.add(tfNome, gbc);

        JLabel lblValor = new JLabel("Valor");
        tfValor = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 1;
        painelFormulario.add(lblValor, gbc);
        gbc.gridx = 1;
        painelFormulario.add(tfValor, gbc);

        JLabel lblQuantidade = new JLabel("Quantidade");
        tfQuantidade = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 2;
        painelFormulario.add(lblQuantidade, gbc);
        gbc.gridx = 1;
        painelFormulario.add(tfQuantidade, gbc);

        JLabel lblDescricao = new JLabel("Descrição");
        tfDescricao = new JTextField(20);
        gbc.gridx = 0;
        gbc.gridy = 3;
        painelFormulario.add(lblDescricao, gbc);
        gbc.gridx = 1;
        painelFormulario.add(tfDescricao, gbc);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.LINE_AXIS));

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new AdicionarProduto(this));
        painelBotoes.add(btnAdicionar);

        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(new RemoverProduto(this));
        painelBotoes.add(btnRemover);

        JButton btnClonar = new JButton("Clonar");
        btnClonar.addActionListener(new ClonarProduto(this));
        painelBotoes.add(btnClonar);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        painelFormulario.add(painelBotoes, gbc);

        JPanel painelAdicionar = new JPanel(new BorderLayout());
        painelAdicionar.setBorder(BorderFactory.createEmptyBorder(H_GAP, H_GAP, H_GAP, H_GAP));
        painelAdicionar.add(painelFormulario, BorderLayout.NORTH);

        tela.getContentPane().add(painelAdicionar, BorderLayout.WEST);
    }

    public void addProduto() {
        String nome = tfNome.getText();
        String valor = tfValor.getText();
        String quantidade = tfQuantidade.getText();
        String descricao = tfDescricao.getText();

        try {
            double valorDouble = Double.parseDouble(valor);
            if (valorDouble <= 0) {
                throw new ValorException();
            }

            int quantidadeInt = Integer.parseInt(quantidade);
            if (quantidadeInt <= 0) {
                throw new QuantidadeException();
            }

            Object[] rowData = {nome, valorDouble, quantidadeInt, descricao};
            modeloTabela.addRow(rowData);

            tfNome.setText("");
            tfValor.setText("");
            tfQuantidade.setText("");
            tfDescricao.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato inválido!");
        } catch (ValorException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido!");
        } catch (QuantidadeException e) {
            JOptionPane.showMessageDialog(null, "Quantidade inválida!");
        }
    }

    public void removerProduto() {
        int selectedRow = tabelaProdutos.getSelectedRow();

        try {
            validateIndex(selectedRow);
            modeloTabela.removeRow(selectedRow);

        } catch (OpcaoException e) {
            JOptionPane.showMessageDialog(null, "Opção ainda não selecionada!");
        }
    }

    public void clonarProduto() {
        int selectedRow = tabelaProdutos.getSelectedRow();
        try {
            validateIndex(selectedRow);
            Object[] rowData = {
                tabelaProdutos.getValueAt(selectedRow, 0),
                tabelaProdutos.getValueAt(selectedRow, 1),
                tabelaProdutos.getValueAt(selectedRow, 2),
                tabelaProdutos.getValueAt(selectedRow, 3)
            };
            modeloTabela.addRow(rowData);

        } catch (OpcaoException e) {
            JOptionPane.showMessageDialog(null, "Opção ainda não selecionada!");
        }
    }

    private void validateIndex(int index) throws OpcaoException {
        if (index == -1) {
            throw new OpcaoException();
        }
    }
}
