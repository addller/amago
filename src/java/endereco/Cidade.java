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
public class Cidade {

    private final int  id;
    private int idEstado;
    private Estado estado;
    private final String nome;

    public Cidade(int id, int idEstado, String nome) {
        this.id = id;
        this.nome = nome;
        this.idEstado = idEstado;
    }

    public int getId() {
        return id;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getNome() {
        return nome;
    }

    public int getIdEstado() {
        return idEstado;
    }

    
}
