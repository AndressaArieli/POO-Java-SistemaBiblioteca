package org.example.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ListaUsuariosView extends JFrame {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private JTable tabelaUsuarios;
    private DefaultTableModel modelo;

    public ListaUsuariosView() {
        setTitle("Gerenciar Usuários");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new String[]{"ID", "Nome", "Login", "Tipo"}, 0);
        tabelaUsuarios = new JTable(modelo);
        add(new JScrollPane(tabelaUsuarios), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();

        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);

        add(painelBotoes, BorderLayout.SOUTH);

        carregarUsuarios();

        btnEditar.addActionListener(e -> {
            int linha = tabelaUsuarios.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelo.getValueAt(linha, 0);
                try {
                    Usuario usuario = usuarioDAO.buscarPorId(id);
                    new UsuarioView(usuario).setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao buscar usuário: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int linha = tabelaUsuarios.getSelectedRow();
            if (linha >= 0) {
                int id = (int) modelo.getValueAt(linha, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Confirma exclusão?");
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        usuarioDAO.deletar(id);
                        carregarUsuarios();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Erro ao deletar usuário: " + ex.getMessage());
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
            }
        });
    }

    private void carregarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDAO.listar();
            modelo.setRowCount(0);
            for (Usuario u : usuarios) {
                modelo.addRow(new Object[]{u.getId(), u.getNome(), u.getLogin(), u.isAdmin() ? "Admin" : "Comum"});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários: " + e.getMessage());
        }
    }
}
