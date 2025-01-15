package br.edu.ifam.tads.produtos.dto;

import br.edu.ifam.tads.produtos.entity.Fornecedor;

public class FornecedorDTO {

    private long id;
    private int codigo;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    public FornecedorDTO(){}

    public FornecedorDTO(Fornecedor fornecedor){
        this.id = fornecedor.getId();
        this.codigo = fornecedor.getCodigo();
        this.nome = fornecedor.getNome();
        this.email = fornecedor.getEmail();
        this.telefone = fornecedor.getEndereco();
    }

    public Fornecedor getFornecedor() {
        return new Fornecedor(id, codigo, nome, email, telefone, endereco);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
