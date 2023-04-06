package com.example.a6444911.trab_m3;

public class Funcionario {
    private int id;
    private String username;
    private String password;
    private float salario;
    private String nome;

    public Funcionario(){

    }

    public Funcionario(int _id, String _username, String _password, float _salario, String _nome){
        this.id = _id;
        this.username = _username;
        this.password = _password;
        this.salario = _salario;
        this.nome = _nome;
    }

    public Funcionario(String _username, String _password, float _salario, String _nome){
        this.username = _username;
        this.password = _password;
        this.salario = _salario;
        this.nome = _nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
