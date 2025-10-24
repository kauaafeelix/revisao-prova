package org.example.dao;

import org.example.Conexao.Conexao;
import org.example.model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialDAO {

    public boolean existeNomeMaterial (String nome) throws SQLException {

        String sql = """
                SELECT nome
                FROM Material
                WHERE nome = ?
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nome);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
        }
    }
        return false;
    }

    public void cadastrarMaterial (Material material) throws SQLException{
        String sql = """
                INSERT INTO Material (
                nome,
                unidade,
                estoque )
                VALUES (?,?,?)
                """;

        try(Connection conn = Conexao.conectar();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, material.getNome());
            ps.setString(2, material.getUnidade());
            ps.setDouble(3, material.getEstoque());
            ps.executeUpdate();
        }
    }
}
