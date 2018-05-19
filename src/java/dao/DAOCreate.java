/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import endereco.Endereco;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import membro.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import membro.Membro;

/**
 *
 * @author EI
 */
public class DAOCreate extends DAO {

    public DAOCreate(PrintWriter out, Connection conexao) {
        super(out, conexao);
    }

    /**
     * Cria registra um login e um membro no banco de dados Retorna as colunas
     * id_login e id_membro, ambas do tipo long
     *
     * @param membro vide classe membro
     * @return id_login, id_membro, id_cadastro, id_endereco
     * @throws SQLException
     */
    public ResultSet registrarMembro(Membro membro) throws SQLException {
        Endereco endereco = membro.getEndereco();
        Login login = membro.getLogin();
        query("select id_login, id_membro, id_cadastro, id_endereco from "
                + "registrarMembro("
                + "?,?,?,?,?,?,"
                + "?,?,?,?,?,?"
                + ") as newMembro;");
        encadear(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getNumero(),
                endereco.getCep(),
                endereco.getReferencia()
        );
        statement.setInt(6, endereco.getCidade().getId());
        statement.setString(7, membro.getDocumentos().getNome());
        statement.setDate(8, membro.getDocumentos().getNascimento());
        statement.setInt(9, membro.getDocumentos().getSexo().id);
        encadear(new int[]{10, 11}, login.getLoginName(), login.getEmail());
        statement.setBytes(12, login.getPass());
        return executeQuery();
    }

    public ResultSet registrarAtividade(Atividade atividade) throws SQLException, IOException {
        Membro membro = atividade.getMembro();
        Endereco endereco = atividade.getEndereco();
        query("select * registrarAtividade("
                + "?,?,?,?,?,?"
                + ",?,?,?,?,?"
                + ")");
    
        encadear(new int[]{1, 3, 5}, 
                atividade.getDescricao(), 
                atividade.getCoordenadas(),
                atividade.getExtensaoImagem());
        
        encadear(new int[]{2, 10},
                membro.getId(), 
                endereco.getCidade().getId()
        );
        
        statement.setBytes(4, getBytesFromImage(atividade));
        
        encadear(new int[]{6, 7, 8, 9,11},
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(), 
                endereco.getReferencia(), 
                endereco.getNumero());
        return executeQuery();
    }

    private static byte[] getBytesFromImage(Atividade atividade) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage) atividade.getImagem(), atividade.getExtensaoImagem(), bos);
        return bos.toByteArray();
    }
}
