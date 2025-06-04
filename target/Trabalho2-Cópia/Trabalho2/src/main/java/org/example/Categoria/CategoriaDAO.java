package org.example.Categoria;
import org.example.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CategoriaDAO {
    private Connection conectar() throws SQLException {
        return Conexao.conectar();
    }

    public void inserir(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
        }
    }

    public List<Categoria> listar() throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria ORDER BY id";
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("id"), rs.getString("nome")));
            }
        }
        return lista;
    }

    public void atualizar(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
