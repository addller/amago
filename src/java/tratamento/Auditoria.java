/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tratamento;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IE
 */
public abstract class Auditoria {

    public static final List<Erro> LOGIN = new ArrayList<>();

    public void onLogin(Erro erro) {
        LOGIN.add(erro);
    }
}
