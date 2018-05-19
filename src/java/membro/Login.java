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
public class Login{

    private long id;
    private final String loginName, email;
    private final byte[] pass;

    public Login(String loginName, String email, String pass) {
        this.email = email;
        this.pass = pass.getBytes();
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getPass() {
        return pass;
    }

    public String getLoginName() {
        return loginName;
    }

    public long getId() {
        return id;
    }
    
    public void updateId(long id){
        if (this.id == 0 && id > 0) {
            this.id = id;
        }
    }

   
}
