/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author IE
 */
public enum TipoAtividade {
    FISCALIZACAO("Fiscalizacao", 1),
    ACOMPANHAMENTO("Acompanhamento", 2),
    SOLUCAO("Solucao", 3);
    public final String nome;
    public final int codigo;

    private TipoAtividade(String nome, int codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

}
