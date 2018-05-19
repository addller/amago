/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membro;

import endereco.Endereco;
import java.sql.Date;

/**
 *
 * @author IE
 */
public class DocumentosMembro {
    private long id;
    private final String nome;
    private final TypeSexo sexo;
    private final Date nascimento;
    private Endereco endereco;

    public DocumentosMembro(String nome, TypeSexo sexo, Date nascimento) {
        this.nome = nome;
        this.sexo = sexo;
        this.nascimento = nascimento;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public TypeSexo getSexo() {
        return sexo;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
}
