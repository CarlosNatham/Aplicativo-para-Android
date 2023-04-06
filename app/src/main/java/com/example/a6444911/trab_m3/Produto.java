package com.example.a6444911.trab_m3;

public class Produto {
    private int id;
    private String nome;
    private float valor;
    private String tamanho;
    private int estoque;

    public Produto(){

    }

    public Produto(int _id, String _nome, float _valor, String _tamanho, int _estoque){
        this.id = _id;
        this.nome = _nome;
        this.valor = _valor;
        this.tamanho = _tamanho;
        this.estoque = _estoque;
    }

    public Produto(String _nome, float _valor, String _tamanho, int _estoque){
        this.nome = _nome;
        this.valor = _valor;
        this.tamanho = _tamanho;
        this.estoque = _estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
}
