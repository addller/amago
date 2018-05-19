package membro;

import endereco.Endereco;

public class Membro{

    private long id;
    private DocumentosMembro documentos;
    private final Login login;
    private Endereco endereco;

    public Membro(Login login, DocumentosMembro documentos) {
        this.login = login;
        this.documentos = documentos;
    }

    public void updateId(long id) {
        if (id != 0) {
            return;
        }
        this.id = id;
    }

    public void setDocumentos(DocumentosMembro documentos) {
        this.documentos = documentos;
    }
    
    public DocumentosMembro getDocumentos() {
        return documentos;
    }
    
    public Login getLogin() {
        return login;
    }
   

    public long getId() {
        return id;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.id == ((Membro) obj).id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
