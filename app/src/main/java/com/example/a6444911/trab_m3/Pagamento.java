package com.example.a6444911.trab_m3;

public class Pagamento {
    private String formaPag;//nome da forma de pagamento ex=Cartao..

    //SE FOR CARTAO
    private String titular;//nome da pessoa no cartao
    private int num;//numero do cartao
    private String bandeira;
    private int codSeg;//codigo de segurança do cartao
    private int qtdParcela;//quantidade de parcelas para parcelar a comrpa

    //SE FOR DINHIERO
    private float precoDinheiro;
    private float troco;

    //SE FOR CHEQUE
    private String precoCheque;//é string pq em cheque se escreve o valor por extenso da quantia
    private String nome;//NOME DA PESSOA NO CHEQUE
    private String cidade;
    private String data;

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public int getCodSeg() {
        return codSeg;
    }

    public void setCodSeg(int codSeg) {
        this.codSeg = codSeg;
    }

    public int getQtdParcela() {
        return qtdParcela;
    }

    public void setQtdParcela(int qtdParcela) {
        this.qtdParcela = qtdParcela;
    }

    public float getPrecoDinheiro() {
        return precoDinheiro;
    }

    public void setPrecoDinheiro(float precoDinheiro) {
        this.precoDinheiro = precoDinheiro;
    }

    public float getTroco() {
        return troco;
    }

    public void setTroco(float troco) {
        this.troco = troco;
    }

    public String getPrecoCheque() {
        return precoCheque;
    }

    public void setPrecoCheque(String precoCheque) {
        this.precoCheque = precoCheque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
