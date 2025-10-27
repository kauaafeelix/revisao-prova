package org.example.dao;

import org.example.Conexao.Conexao;
import org.example.model.NotaEntrada;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;

public class NotaEntradaDAO {

    public int cadastrarNotaEntrada(NotaEntrada notaEntrada) throws SQLException {

        String sql = """
                INSERT INTO NotaEntrada (idFornecedor, dataEntrada)
                VALUES (?, NOW());
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, notaEntrada.getIdFornecedor());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int idGerado = rs.getInt(1);
                notaEntrada.setId(idGerado);
                return idGerado;
            }
        }
        return 0;
    }

    public void associarMateriais(HashMap<Integer, Double> estoque, int idFornecedor) throws SQLException {
        String sql = """
                INSERT INTO NotaEntrada (idNotaEntrada, idMaterial, quantidade)
                VALUES (?,?,?);
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (Integer idMaterial : estoque.keySet()) {
                ps.setInt(1, idFornecedor);
                ps.setInt(2, idMaterial);
                ps.setDouble(3, estoque.get(idMaterial));
                ps.executeUpdate();
            }
        }
    }
}
