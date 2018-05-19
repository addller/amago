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
public enum Pais {
    BRASIL("Brasil", "BRA", "Português", "pt", "+55", 34, Continente.AMERICA_DO_SUL);
    public final String nome, sigla, idioma, abreviacaoLingua, codigoTelefonico;
    public final int id;
    public final Continente continente;

    private Pais(String nome, String sigla, String idioma,
            String abreviacaoLingua, String codigoTelefonico, int id,
            Continente continente) {
        this.nome = nome;
        this.sigla = sigla;
        this.idioma = idioma;
        this.abreviacaoLingua = abreviacaoLingua;
        this.codigoTelefonico = codigoTelefonico;
        this.id = id;
        this.continente = continente;
    }

    public Pais getPais(String valor) {
        for (Pais pais : Pais.values()) {
            if (valor.equals(pais.nome)) {
                return pais;
            }
        }
        return null;
    }

    public Pais getPais(int id) {
        for (Pais pais : Pais.values()) {
            if (id == pais.id) {
                return pais;
            }
        }
        return null;
    }

}
