package com.example.a6444911.trab_m3;

public class Vendas {
    private int id;
    private String nome_cliente;
    private String nome_funcionario;
    private float valor;
    private int qtd_produtos;
    private String forma_pag;

    public Vendas(){

    }

    public Vendas(int _id, String _nome_cliente, String _nome_funcionario, float _valor, int _qtd, String _forma_pag){
        this.id = _id;
        this.nome_cliente = _nome_cliente;
        this.nome_funcionario = _nome_funcionario;
        this.valor = _valor;
        this.qtd_produtos = _qtd;
        this.forma_pag = _forma_pag;
    }

    public Vendas(String _nome_cliente, String _nome_funcionario, float _valor, int _qtd, String _forma_pag){
        this.nome_cliente = _nome_cliente;
        this.nome_funcionario = _nome_funcionario;
        this.valor = _valor;
        this.qtd_produtos = _qtd;
        this.forma_pag = _forma_pag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQtd_produtos() {
        return qtd_produtos;
    }

    public void setQtd_produtos(int qtd_produtos) {
        this.qtd_produtos = qtd_produtos;
    }

    public String getForma_pag() {
        return forma_pag;
    }

    public void setForma_pag(String forma_pag) {
        this.forma_pag = forma_pag;
    }
}
