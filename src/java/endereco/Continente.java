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
public enum Continente {
    AMERICA_DO_NORTE(1, "América do norte"),
    AMERICA_CENTRAL(2, "América Central"),
    AMERICA_DO_SUL(3, "América do sul"),
    AFRICA(4, "África"),
    ASIA(5, "Ásia"),
    EUROPA(6, "Europa"),
    OCEANIA(7, "Oceania"),
    ANTARTIDA(8, "Antártida");
    public final int id;
    public final String nome;

    private Continente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
