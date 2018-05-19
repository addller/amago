/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import endereco.Endereco;
import java.awt.Image;
import java.sql.Date;
import membro.Membro;

/**
 *
 * @author IE
 */
public class Atividade {

    private int id;
    private final Membro membro;
    private TipoAtividade tipoAtividade;
    private final Endereco endereco;
    private String descricao,
            justificativa,
            coordenadas;
    private Date dataCriacao,
            dataSolucao,
            dataDeslocamento,
            dataExclusao;
    private Image imagem;
    private String extensaoImagem;

    public Atividade(Membro membro, Endereco endereco, String descricao, String coordenadas) {
        this.membro = membro;
        this.endereco = endereco;
        this.descricao = descricao;
        this.coordenadas = coordenadas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoAtividade(TipoAtividade tipoAtividade) {
        this.tipoAtividade = tipoAtividade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public void setDataSolucao(Date dataSolucao) {
        this.dataSolucao = dataSolucao;
    }

    public void setDataDeslocamento(Date dataDeslocamento) {
        this.dataDeslocamento = dataDeslocamento;
    }

    public void setDataExclusao(Date dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public void adicionarImagem(Image imagem, String extensao) {
        this.imagem = imagem;
        this.extensaoImagem = extensao;
    }

    public int getId() {
        return id;
    }

    public Membro getMembro() {
        return membro;
    }

    public TipoAtividade getTipoAtividade() {
        return tipoAtividade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataSolucao() {
        return dataSolucao;
    }

    public Date getDataDeslocamento() {
        return dataDeslocamento;
    }

    public Date getDataExclusao() {
        return dataExclusao;
    }

    public Image getImagem() {
        return imagem;
    }

    public String getExtensaoImagem() {
        return extensaoImagem;
    }
    
    

}
