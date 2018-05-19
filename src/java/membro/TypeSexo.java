/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membro;

/**
 *
 * @author IE
 */
public enum TypeSexo {
    MASCULINO("masculino",1), FEMININO("feminino",2);
    public final String sexo;
    public final int id;

    private TypeSexo(String sexo, int codigo) {
        this.sexo = sexo;
        this.id = codigo;
    }

    public static TypeSexo getSexo(String value) {
        switch(value){
            case "masculino": return MASCULINO; 
            case "feminino":return FEMININO; 
            default:return null;
        }
    }
    
}
