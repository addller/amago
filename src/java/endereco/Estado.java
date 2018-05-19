/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package endereco;

/**
 *
 * @author IE
 */
public class Estado {

    final int id;
    private final Pais pais;
    private final Regiao regiao;
    public final String nome, sigla;

    public Estado(int id, Pais pais, Regiao regiao, String nome, String sigla) {
        this.id = id;
        this.pais = pais;
        this.regiao = regiao;
        this.nome = nome;
        this.sigla = sigla;
    }

    

}
