package org.example.dao;

import org.example.Conexao.Conexao;
import org.example.model.Requisicao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RequisicaoDAO {

    public void criarRequisicao(Requisicao requisicao) throws SQLException {

        String sql = """
                INSERT INTO Requisicao (
                setor, data_solicitacao, status )
                VALUES (?,?, 'PENDENTE')
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, requisicao.getSetor());
            ps.setString(2, requisicao.getDataSolicitacao());
            ps.executeUpdate();
        }
    }
}
