package org.example.dao;

import org.example.Conexao.Conexao;
import org.example.model.NotaEntrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class NotaEntradaDAO {

    public void cadastrarNotaEntrada(NotaEntrada notaEntrada)throws SQLException{

        String sql = """
                INSERT INTO NotaEntrada (idFornecedor, dataEntrada)
                VALUES (?, NOW());
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, notaEntrada.getIdFornecedor());
            ps.executeUpdate();
        }
    }
}
