package controller;

import view.TelaSistemaEstoque;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.TelaSistemaEstoque;

public class ClonarProduto implements ActionListener {
    private final TelaSistemaEstoque tela;

    public ClonarProduto(TelaSistemaEstoque tela) {
        this.tela = tela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tela.clonarProduto();
    }
}