package org.example.model;

import java.time.LocalDate;

public class NotaEntrada {

    private int id;
    private int idFornecedor;
    private LocalDate dataEntrada;


    public NotaEntrada(int id, int idFornecedor, LocalDate dataEntrada) {
        this.id = id;
        this.idFornecedor = idFornecedor;
        this.dataEntrada = dataEntrada;
    }
    public NotaEntrada(int idFornecedor, LocalDate dataEntrada) {
        this.idFornecedor = idFornecedor;
        this.dataEntrada = dataEntrada;
    }

    public NotaEntrada(int idFornecedor) {
        this.idFornecedor = idFornecedor ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFornecedor() {
        return idFornecedor;
    }

    public void setFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
