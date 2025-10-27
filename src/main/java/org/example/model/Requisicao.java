package org.example.model;

import org.example.model.enuns.StatusRequisicao;

public class Requisicao {
    private int id;
    private String setor;
    private String dataSolicitacao;
    private StatusRequisicao status;

    public Requisicao(int id, String setor, String dataSolicitacao, StatusRequisicao status) {
        this.id = id;
        this.setor = setor;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public Requisicao(String setor, String dataSolicitacao, StatusRequisicao status) {
        this.setor = setor;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(String dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public StatusRequisicao getStatus() {
        return status;
    }

    public void setStatus(StatusRequisicao status) {
        this.status = status;
    }
}
