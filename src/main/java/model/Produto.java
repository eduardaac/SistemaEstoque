/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import exception.QuantidadeException;
import exception.ValorException;

/**
 *
 * @author duda2
 */
public class Produto {

    private String nome;
    private double valor;
    private int quantidade;
    private String descricao;

    public Produto() {
    }

    public Produto(String nome, double valor, int quantidade, String descricao) throws ValorException, QuantidadeException {
        this.nome = nome;
        if (valor <= 0) {
            throw new ValorException();
        }
        this.valor = valor;
        if (quantidade <= 0) {
            throw new QuantidadeException();
        }
        this.quantidade = quantidade;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) throws ValorException {
        if (valor <= 0) {
            throw new ValorException();
        }
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) throws QuantidadeException {
        if (quantidade <= 0) {
            throw new QuantidadeException();
        }
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
