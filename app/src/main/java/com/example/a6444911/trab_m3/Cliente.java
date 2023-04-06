package com.example.a6444911.trab_m3;

public class Cliente {
    private int id;
    private String cpf;
    private String nome;
    private long telefone;
    private long cep;
    private String cidade_uf;

    public Cliente(){

    }

    public Cliente(int _id, String _cpf, String _nome, long _telefone, long _cep, String _cidade_uf){
        this.id = _id;
        this.cpf = _cpf;
        this.nome = _nome;
        this.telefone = _telefone;
        this.cep = _cep;
        this.cidade_uf = _cidade_uf;
    }

    public Cliente(String _cpf, String _nome, long _telefone, long _cep, String _cidade_uf){
        this.cpf = _cpf;
        this.nome = _nome;
        this.telefone = _telefone;
        this.cep = _cep;
        this.cidade_uf = _cidade_uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public long getCep() {
        return cep;
    }

    public void setCep(long cep) {
        this.cep = cep;
    }

    public String getCidade_uf() {
        return cidade_uf;
    }

    public void setCidade_uf(String cidade_uf) {
        this.cidade_uf = cidade_uf;
    }
}
