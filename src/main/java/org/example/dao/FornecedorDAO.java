package org.example.dao;

import org.example.Conexao.Conexao;
import org.example.model.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {

    public boolean existeCnpj(String cnpj) throws SQLException {
        String sql = """
                SELECT cnpj
                FROM Fornecedor
                WHERE cnpj = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cnpj);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        return false;
    }

    public void cadastrarFornecedor(Fornecedor fornecedor) throws SQLException{

        String sql = """
                INSERT INTO Fornecedor (
                nome,
                cnpj)
                VALUES (?,?)
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, fornecedor.getNome());
            ps.setString(2, fornecedor.getCnpj());
            ps.executeUpdate();
        }
    }

    public List<Fornecedor> listarFornecedores() throws SQLException {
        List<Fornecedor> fornecedores = new ArrayList<>();
        String sql = """
                SELECT id,
                nome
                FROM Fornecedor
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");

                Fornecedor fornecedor = new Fornecedor(id, nome);
                fornecedores.add(fornecedor);
            }
            return fornecedores;
        }
    }

    public Fornecedor existeId(int id) throws SQLException {
        String sql = """
                SELECT id
                FROM Fornecedor
                WHERE id = ?
                """;

        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Fornecedor(rs.getInt("id"), rs.getString("nome"));
            }
        }
        return null;
    }
}
