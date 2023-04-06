package com.example.a6444911.trab_m3;

public class Fornecedor {
    private int id;
    private String CNPJ;
    private String nome;
    private int ie;

    public Fornecedor(){

    }

    public Fornecedor(int _id, String _cnpj, String _nome, int _ie){
        this.id = _id;
        this.CNPJ = _cnpj;
        this.nome = _nome;
        this.ie = _ie;
    }

    public Fornecedor(String _cnpj, String _nome, int _ie){
        this.CNPJ = _cnpj;
        this.nome = _nome;
        this.ie = _ie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIe() {
        return ie;
    }

    public void setIe(int ie) {
        this.ie = ie;
    }
}
