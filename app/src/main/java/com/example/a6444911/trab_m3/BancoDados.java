package com.example.a6444911.trab_m3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String NOME_BANCO = "bd_cgs";

    private static final String TABELA_CLIENTES = "clientes";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_CPF = "nome"; //invertido
    private static final String COLUNA_NOME = "cpf"; //invertido
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_CEP = "cep";
    private static final String COLUNA_CIDADE_UF = "cidade";

    private static final String TABELA_FORNECEDORES = "fornecedores";
    private static final String COLUNA_ID2 = "id";
    private static final String COLUNA_CNPJ = "cnpj";
    private static final String COLUNA_NOME2 = "nome";
    private static final String COLUNA_IE = "ie";

    private static final String TABELA_FUNCIONARIOS = "funcionarios";
    private static final String COLUNA_ID3 = "id";
    private static final String COLUNA_NOME3 = "nome";
    private static final String COLUNA_SALARIO = "salario";
    private static final String COLUNA_USERNAME = "usuario";
    private static final String COLUNA_PASSWORD = "senha";

    private static final String TABELA_PRODUTOS = "produtos";
    private static final String COLUNA_ID4 = "id";
    private static final String COLUNA_NOME4 = "nome";
    private static final String COLUNA_VALOR = "valor";
    private static final String COLUNA_TAMANHO = "tamanho";
    private static final String COLUNA_ESTOQUE = "estoque";

    private static final String TABELA_VENDAS = "vendas";
    private static final String COLUNA_ID5 = "id";
    private static final String COLUNA_NOME_CLIENTE = "cliente";
    private static final String COLUNA_NOME_FUNC = "vendedor";
    private static final String COLUNA_VALOR_TOTAL = "valor";
    private static final String COLUNA_QTD = "qtd_de_produtos";
    private static final String COLUNA_FORMA_PAG = "forma_de_pagamento";

    public BancoDados(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String QUERY_COLUNA1 = "CREATE TABLE " + TABELA_CLIENTES + "("
                + COLUNA_ID + " INTEGER PRIMARY KEY, " + COLUNA_CPF + " TEXT, "
                + COLUNA_NOME + " TEXT, " + COLUNA_TELEFONE + " TEXT, " + COLUNA_CEP
                + " TEXT, " + COLUNA_CIDADE_UF + " TEXT)";
        db.execSQL(QUERY_COLUNA1);

        String QUERY_COLUNA2 = "CREATE TABLE " + TABELA_FORNECEDORES + "("
                + COLUNA_ID2 + " INTEGER PRIMARY KEY, " + COLUNA_CNPJ + " TEXT, "
                + COLUNA_NOME2 + " TEXT, " + COLUNA_IE + " TEXT)";
        db.execSQL(QUERY_COLUNA2);

        String QUERY_COLUNA3 = "CREATE TABLE " + TABELA_FUNCIONARIOS + "("
                + COLUNA_ID3 + " INTEGER PRIMARY KEY, " + COLUNA_NOME3 + " TEXT, "
                + COLUNA_SALARIO + " TEXT, " + COLUNA_USERNAME + " TEXT, "
                + COLUNA_PASSWORD + " TEXT)";
        db.execSQL(QUERY_COLUNA3);

        String QUERY_COLUNA4 = "CREATE TABLE " + TABELA_PRODUTOS + "("
                + COLUNA_ID4 + " INTEGER PRIMARY KEY, " + COLUNA_NOME4 + " TEXT, "
                + COLUNA_VALOR + " TEXT, " + COLUNA_TAMANHO + " TEXT, "
                + COLUNA_ESTOQUE + " TEXT)";
        db.execSQL(QUERY_COLUNA4);

        String QUERY_COLUNA5 = "CREATE TABLE " + TABELA_VENDAS + "("
                + COLUNA_ID5 + " INTEGER PRIMARY KEY, " + COLUNA_NOME_CLIENTE + " TEXT, "
                + COLUNA_NOME_FUNC + " TEXT, " + COLUNA_VALOR_TOTAL + " TEXT, "
                + COLUNA_QTD + " TEXT, " + COLUNA_FORMA_PAG + " TEXT)";
        db.execSQL(QUERY_COLUNA5);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    /************************************************************************************************************************************/

    void addCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_CPF, cliente.getCpf());
        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_CEP, cliente.getCep());
        values.put(COLUNA_CIDADE_UF, cliente.getCidade_uf());

        db.insert(TABELA_CLIENTES, null, values);
        db.close();
    }

    void apagarCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_CLIENTES, COLUNA_ID + " = ?", new String[]{String.valueOf(cliente.getId())});
        db.close();
    }

    Cliente selecionarCliente(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_CLIENTES, new String[]{COLUNA_ID, COLUNA_NOME, COLUNA_CPF, COLUNA_TELEFONE, COLUNA_CEP,
                        COLUNA_CIDADE_UF}, COLUNA_ID +" = ?", new String[]{String.valueOf(codigo)},
                        null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Cliente cliente = new Cliente(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2),Long.parseLong(cursor.getString(3)),
                Long.parseLong(cursor.getString(4)), cursor.getString(5));

        return cliente;
    }

    void atualizaCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_CPF, cliente.getCpf());
        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_CEP, cliente.getCep());
        values.put(COLUNA_CIDADE_UF, cliente.getCidade_uf());

        db.update(TABELA_CLIENTES, values, COLUNA_ID + " = ?", new String[]{String.valueOf(cliente.getId())});
    }

    public List<Cliente> Exibe_cliente() {
        List<Cliente> listaCliente = new ArrayList<Cliente>();
        String query = "SELECT * FROM clientes"; //+ TABELA_CLIENTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Cliente cliente = new Cliente();
                cliente.setId(Integer.parseInt(c.getString(0)));
                cliente.setCpf(c.getString(1));
                cliente.setNome(c.getString(2));
                cliente.setTelefone(Long.parseLong(c.getString(3)));
                cliente.setCep(Long.parseLong(c.getString(4)));
                cliente.setCidade_uf(c.getString(5));

                listaCliente.add(cliente);

            } while (c.moveToNext());
        }
        return listaCliente;
    }

    public String ValidarIdCliente(int codigo){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM clientes WHERE id = ?", new String[]{String.valueOf(codigo)});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

    public ArrayList<String> getClientes(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM " + TABELA_CLIENTES;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String cnome = cursor.getString(cursor.getColumnIndex(COLUNA_CPF));
                    list.add(cnome);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    /************************************************************************************************************************************/

    void addFornecedor(Fornecedor fornecedor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_CNPJ, fornecedor.getCNPJ());
        values.put(COLUNA_NOME2, fornecedor.getNome());
        values.put(COLUNA_IE, fornecedor.getIe());

        db.insert(TABELA_FORNECEDORES, null, values);
        db.close();
    }

    void apagarFornecedor(Fornecedor fornecedor){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_FORNECEDORES, COLUNA_ID2 + " = ?", new String[]{String.valueOf(fornecedor.getId())});
        db.close();
    }

    Fornecedor selecionarFornecedor(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_FORNECEDORES, new String[]{COLUNA_ID2, COLUNA_CNPJ, COLUNA_NOME2, COLUNA_IE},
                COLUNA_ID2 +" = ?", new String[]{String.valueOf(codigo)}, null, null,
                null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Fornecedor fornecedor = new Fornecedor(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2),Integer.parseInt(cursor.getString(3)));

        return fornecedor;
    }

    void atualizaFornecedor(Fornecedor fornecedor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_CNPJ, fornecedor.getCNPJ());
        values.put(COLUNA_NOME2, fornecedor.getNome());
        values.put(COLUNA_IE, fornecedor.getIe());
        db.update(TABELA_FORNECEDORES, values, COLUNA_ID2 + " = ?", new String[]{String.valueOf(fornecedor.getId())});
    }

    public List<Fornecedor> Exibe_fornecedor() {
        List<Fornecedor> listaFornecedor = new ArrayList<Fornecedor>();
        String query = "SELECT * FROM fornecedores"; //+ TABELA_FORNECEDORES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(Integer.parseInt(c.getString(0)));
                fornecedor.setCNPJ(c.getString(1));
                fornecedor.setNome(c.getString(2));
                fornecedor.setIe(Integer.parseInt(c.getString(3)));

                listaFornecedor.add(fornecedor);
            } while (c.moveToNext());
        }
        return listaFornecedor;
    }

    public String ValidarIdFornecedor(int codigo){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM fornecedores WHERE id = ?", new String[]{String.valueOf(codigo)});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

    /************************************************************************************************************************************/

    void addFuncionario(Funcionario funcionario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME3, funcionario.getNome());
        values.put(COLUNA_SALARIO, funcionario.getSalario());
        values.put(COLUNA_USERNAME, funcionario.getUsername());
        values.put(COLUNA_PASSWORD, funcionario.getPassword());

        db.insert(TABELA_FUNCIONARIOS, null, values);
        db.close();
    }

    void apagarFuncionario(Funcionario funcionario){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_FUNCIONARIOS, COLUNA_ID3 + " = ?", new String[]{String.valueOf(funcionario.getId())});
        db.close();
    }

    Funcionario selecionarFuncionario(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_FUNCIONARIOS, new String[]{COLUNA_ID3, COLUNA_USERNAME, COLUNA_PASSWORD,
                        COLUNA_SALARIO, COLUNA_NOME3}, COLUNA_ID3 + " = ?", new String[]{String.valueOf(codigo)},
                        null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Funcionario funcionario = new Funcionario(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), Float.parseFloat(cursor.getString(3)), cursor.getString(4));

        return funcionario;
    }

    void atualizaFuncionario(Funcionario funcionario){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME3, funcionario.getNome());
        values.put(COLUNA_SALARIO, funcionario.getSalario());
        values.put(COLUNA_USERNAME, funcionario.getUsername());
        values.put(COLUNA_PASSWORD, funcionario.getPassword());
        db.update(TABELA_FUNCIONARIOS, values, COLUNA_ID3 + " = ?", new String[]{String.valueOf(funcionario.getId())});
    }

    public List<Funcionario> Exibe_funcionarios(){
        List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
        String query = "SELECT * FROM funcionarios"; //+ TABELA_FUNCIONARIOS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(Integer.parseInt(c.getString(0)));
                funcionario.setNome(c.getString(1));
                funcionario.setSalario(Float.parseFloat(c.getString(2)));
                funcionario.setUsername(c.getString(3));
                //funcionario.setPassword(c.getString(5));

                listaFuncionario.add(funcionario);
            }while(c.moveToNext());
        }
        return listaFuncionario;
    }

    public String ValidarLogin(String usuario, String senha){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM funcionarios WHERE usuario = ? AND senha = ?", new String[]{usuario,senha});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

    public String ValidarEmail(String usuario){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM funcionarios WHERE usuario = ?", new String[]{usuario});
        if(c.getCount()>0){
            return "ERRO";
        }
        return "OK";
    }

    public String ValidarIdFuncionario(int codigo){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM funcionarios WHERE id = ?", new String[]{String.valueOf(codigo)});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

    public ArrayList<String> getFuncionario(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM " + TABELA_FUNCIONARIOS;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String cnome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME3));
                    list.add(cnome);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    /************************************************************************************************************************************/

    void addProduto(Produto produto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME4, produto.getNome());
        values.put(COLUNA_VALOR, produto.getValor());
        values.put(COLUNA_TAMANHO, produto.getTamanho());
        values.put(COLUNA_ESTOQUE, produto.getEstoque());

        db.insert(TABELA_PRODUTOS, null, values);
        db.close();
    }

    void apagarProduto(Produto produto){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_PRODUTOS, COLUNA_ID4 + " = ?", new String[]{String.valueOf(produto.getId())});
        db.close();
    }

    Produto selecionarProduto(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_PRODUTOS, new String[]{COLUNA_ID4, COLUNA_NOME4, COLUNA_VALOR, COLUNA_TAMANHO,
                        COLUNA_ESTOQUE}, COLUNA_ID4 +" = ?", new String[]{String.valueOf(codigo)},
                        null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Produto produto = new Produto(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Float.parseFloat(cursor.getString(2)), cursor.getString(3),
                Integer.parseInt(cursor.getString(4)));

        return produto;
    }

    Produto selecionarProduto2(String nome){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_PRODUTOS, new String[]{COLUNA_ID4, COLUNA_NOME4, COLUNA_VALOR, COLUNA_TAMANHO,
                        COLUNA_ESTOQUE}, COLUNA_NOME4 +" = ?", new String[]{String.valueOf(nome)},
                null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Produto produto = new Produto(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                Float.parseFloat(cursor.getString(2)), cursor.getString(3),
                Integer.parseInt(cursor.getString(4)));

        return produto;
    }

    void atualizaProduto(Produto produto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME4, produto.getNome());
        values.put(COLUNA_VALOR, produto.getValor());
        values.put(COLUNA_TAMANHO, produto.getTamanho());
        values.put(COLUNA_ESTOQUE, produto.getEstoque());

        db.update(TABELA_PRODUTOS, values, COLUNA_ID4 + " = ?", new String[]{String.valueOf(produto.getId())});
    }

    void atualizaEstoqueProduto(Produto produto){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_ESTOQUE, produto.getEstoque());
        db.update(TABELA_PRODUTOS, values, COLUNA_ID4 + " = ?", new String[]{String.valueOf(produto.getId())});
    }

    public List<Produto> Exibe_Produto() {
        List<Produto> listaProduto = new ArrayList<Produto>();
        String query = "SELECT * FROM produtos"; //+ TABELA_CLIENTES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Produto produto = new Produto();
                produto.setId(Integer.parseInt(c.getString(0)));
                produto.setNome(c.getString(1));
                produto.setValor(Float.parseFloat(c.getString(2)));
                produto.setTamanho(c.getString(3));
                produto.setEstoque(Integer.parseInt(c.getString(4)));

                listaProduto.add(produto);

            } while (c.moveToNext());
        }
        return listaProduto;
    }

    public String ValidarIdProduto(int codigo){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM produtos WHERE id = ?", new String[]{String.valueOf(codigo)});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

    public ArrayList<String> getProduto(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();

        try {
            String selectQuery = "SELECT * FROM " + TABELA_PRODUTOS;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String cnome = cursor.getString(cursor.getColumnIndex(COLUNA_NOME4));
                    list.add(cnome);
                }
            }
            db.setTransactionSuccessful();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            db.endTransaction();
            db.close();
        }
        return list;
    }

    /************************************************************************************************************************************/

    void addVendas(Vendas vendas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME_CLIENTE, vendas.getNome_cliente());
        values.put(COLUNA_NOME_FUNC, vendas.getNome_funcionario());
        values.put(COLUNA_VALOR_TOTAL, vendas.getValor());
        values.put(COLUNA_QTD, vendas.getQtd_produtos());
        values.put(COLUNA_FORMA_PAG, vendas.getForma_pag());

        db.insert(TABELA_VENDAS, null, values);
        db.close();
    }

    void apagarVenda(Vendas vendas){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_VENDAS, COLUNA_ID5 + " = ?", new String[]{String.valueOf(vendas.getId())});
        db.close();
    }

    Vendas selecionarVendas(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_VENDAS, new String[]{COLUNA_ID5, COLUNA_NOME_CLIENTE, COLUNA_NOME_FUNC,
                        COLUNA_VALOR_TOTAL, COLUNA_QTD, COLUNA_FORMA_PAG}, COLUNA_ID5 +" = ?", new String[]{String.valueOf(codigo)},
                        null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        Vendas vendas = new Vendas(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2),Float.parseFloat(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));

        return vendas;
    }

    void atualizaVendas(Vendas vendas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME_CLIENTE, vendas.getNome_cliente());
        values.put(COLUNA_NOME_FUNC, vendas.getNome_funcionario());
        values.put(COLUNA_VALOR_TOTAL, vendas.getValor());
        values.put(COLUNA_QTD, vendas.getQtd_produtos());
        values.put(COLUNA_FORMA_PAG, vendas.getForma_pag());

        db.update(TABELA_VENDAS, values, COLUNA_ID5 + " = ?", new String[]{String.valueOf(vendas.getId())});
    }

    public List<Vendas> Exibe_Vendas() {
        List<Vendas> listaVendas = new ArrayList<Vendas>();
        String query = "SELECT * FROM " + TABELA_VENDAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Vendas vendas = new Vendas();
                vendas.setId(Integer.parseInt(c.getString(0)));
                vendas.setNome_cliente(c.getString(1));
                vendas.setNome_funcionario(c.getString(2));
                vendas.setValor(Float.parseFloat(c.getString(3)));
                vendas.setQtd_produtos(Integer.parseInt(c.getString(4)));
                vendas.setForma_pag(c.getString(5));

                listaVendas.add(vendas);


            } while (c.moveToNext());
        }
        return listaVendas;
    }

    public String ValidarIdVendas(int codigo){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM vendas WHERE id = ?", new String[]{String.valueOf(codigo)});
        if(c.getCount()>0){
            return "OK";
        }
        return "ERRO";
    }

}


