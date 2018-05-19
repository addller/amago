/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamento;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;
import protocolo.Protocolo;

/**
 *
 * @author IE
 * @param <Type>
 */
public class Erro<Type> {

    private final String motivo;
    private final Protocolo.Nivel nivel;
    private final Protocolo.Status status;
    private final boolean verificado;
    private long data;
    private final Type origem;

    public Erro(Type origem, String motivo, Protocolo.Nivel nivel, Protocolo.Status status, boolean verificado) {
        this.motivo = motivo;
        this.nivel = nivel;
        this.verificado = verificado;
        this.origem = origem;
        this.status = status;
    }

    public Type getOrigem() {
        return origem;
    }

    //Ainda é insuficiente para tratar erros graves, pois não está pegando dados da origem do erro
    public boolean isVerificado() {
        if (verificado && nivel.nivel > 2) {
            data = System.currentTimeMillis();
            Auditoria.LOGIN.add(this);
        }
        return verificado;
    }

    public Protocolo.Nivel getNivel() {
        return nivel;
    }

    public final String get() {
        Protocolo protocolo = new Protocolo(
                Protocolo.Type.RESPONSE,
                Protocolo.Action.CREATE,
                Protocolo.Target.LOGIN);
        protocolo.valueStatus(status,motivo);
        protocolo.valueNivel(nivel);
        
        return protocolo.getJson().toString();
    }

    private String getJson(String[][] protocolo) {
        JSONObject json = new JSONObject();
        for (String[] linha : protocolo) {
            try {
                json.put(linha[0], linha[1]);

            } catch (JSONException ex) {
                Logger.getLogger(Erro.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return json.toString();
    }

}
