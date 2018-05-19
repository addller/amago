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
public enum Regiao {

    LESTE(1, "Leste", "E"), OESTE(2, "Oeste", "O"),
    NORTE(3, "Norte", "N"),
    SUL(4, "Sul", "S"),
    NORDESTE(5, "Nordeste", "NE"),
    NOROESTE(6, "Noroeste", "NO"),
    SUDESTE(7, "Sudeste", "SE"),
    SUDOESTE(8, "Sudoeste", "SO"),
    LES_NORDESTE(9, "Lés-nordeste", "ENE"),
    LES_SUDESTE(10, "Lés-sudeste", "ESE"),
    SU_SUTESTE(11, "Su-sudeste", "SSE"),
    NOR_NORDESTE(12, "Nor-nordeste", "NNE"),
    NOR_NOROESTE(13, "Nor-noroeste", "NNO"),
    SU_DUDOESTE(14, "Su-sudoeste", "SSO"),
    OES_SUDOESTE(15, "Oés-sudoeste", "OSO"),
    OES_NOROESTE(16, "Oés-noroeste", "ONO"),
    CENTRO_OESTE(18, "Centro-Oeste", "CO");
    public final int id;
    String nome, sigla;

    private Regiao(int id, String nome, String sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public Regiao getRegiao(String nome) {
        for (Regiao regiao : Regiao.values()) {
            if (nome.equals(regiao.nome)) {
                return regiao;
            }
        }
        return null;
    }

    public Regiao getRegiao(int id) {
        for (Regiao regiao : Regiao.values()) {
            if (regiao.id == id) {
                return regiao;
            }
        }
        return null;
    }

}
