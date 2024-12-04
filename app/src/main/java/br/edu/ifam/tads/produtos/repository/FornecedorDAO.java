package br.edu.ifam.tads.produtos.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifam.tads.produtos.entity.Fornecedor;

public class FornecedorDAO {

    private SQLiteDatabase sqLiteDatabase;

    private FornecedorDAO(){}

    public FornecedorDAO(Context context){
         BDProdutos bdProdutos =
                new BDProdutos(context, "produto",null,1);
        sqLiteDatabase = bdProdutos.getWritableDatabase();

    }

    public List<Fornecedor> getFornecedor(){
        List<Fornecedor> fornecedores = new ArrayList<>();
        return fornecedores;
    }
    public Fornecedor getFornecedor(long id){
        Fornecedor fornecedor = new Fornecedor();

        return null;
    }
    public void insert(Fornecedor fornecedor){}
    public void upgrade(long id, Fornecedor fornecedor){}
    public void delete(long id){}

}
