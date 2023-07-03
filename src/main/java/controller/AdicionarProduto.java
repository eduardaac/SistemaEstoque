package controller;

import view.TelaSistemaEstoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdicionarProduto implements ActionListener {

    private final TelaSistemaEstoque tela;

    public AdicionarProduto(TelaSistemaEstoque tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.addProduto();
    }
}