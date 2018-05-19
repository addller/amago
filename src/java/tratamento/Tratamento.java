/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamento;

import java.io.PrintWriter;
import membro.Login;
import org.json.JSONException;
import org.json.JSONObject;
import protocolo.Protocolo;

/**
 *
 * @author IE
 */
public abstract class Tratamento {
    
    public static Protocolo.Nivel acesso(PrintWriter out, String erro, JSONObject json) {
        try {
            boolean uniqueUser = erro.contains("duplicate key value violates unique constraint \"unique_user_name\""),
                    uniqueEmail = erro.contains("duplicate key value violates unique constraint \"unique_email\"");
            Protocolo.Status erroCliente = Protocolo.Status.CLIENT_ERROR;
            Protocolo.Nivel nivelUsuario = Protocolo.Nivel.USUARIO;
            
            if (json == null) {
                Erro view = new Erro(null, "Interface de requisição inadequada", Protocolo.Nivel.EXTERNO, erroCliente, json == null);
                view.isVerificado();
                out.println(view.get());
                return view.getNivel();
            }
            
            Login login = new Login(json.getString("loginName"), json.getString("email"), erro);
            Erro<Login> userName = new Erro(login, "Nome de usuário indisponível", nivelUsuario, erroCliente, uniqueUser),
                    email = new Erro(login, "Email já Cadastrado", nivelUsuario, erroCliente, uniqueEmail);
            
            Erro[] erros = {userName, email};
            
            for (Erro err : erros) {
                if (err.isVerificado()) {
                    out.println(err.get());
                    return err.getNivel();
                }
            }
            Erro erroDesconhecido = new Erro(json, "Não foi possível realizar a ação solicitada, tente novamente mais tarde", nivelUsuario, erroCliente, true);
            erroDesconhecido.isVerificado();
            out.write(erroDesconhecido.get());
        } catch (JSONException ex) {
            System.out.println(ex);
        }
        return Protocolo.Nivel.DESCONHECIDO;
    }
}
