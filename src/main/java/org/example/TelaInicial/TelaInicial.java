package org.example.TelaInicial;

import org.example.Categoria.CategoriaView;
import org.example.Login.LoginView;
import org.example.Usuario.ListaUsuariosView;
import org.example.Usuario.Usuario;
import org.example.Usuario.UsuarioView;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial(Usuario usuario) {
        setTitle("Tela Inicial - Bem-vindo, " + usuario.getNome());
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnCategorias = new JButton("Categorias");
        JButton btnEditarUsuario = new JButton("Editar Meu Usuário");
        JButton btnSair = new JButton("Sair");

        add(btnCategorias);
        add(btnEditarUsuario);

        // Ações fixas
        btnCategorias.addActionListener(e -> {
            new CategoriaView(usuario.isAdmin()).setVisible(true);
            dispose();
        });

        btnEditarUsuario.addActionListener(e -> {
            new UsuarioView(usuario).setVisible(true);
            dispose();
        });

        // Botão só para Admin
        if (usuario.isAdmin()) {
            JButton btnGerenciarUsuarios = new JButton("Gerenciar Usuários");
            add(btnGerenciarUsuarios);
            btnGerenciarUsuarios.addActionListener(e -> {
                new ListaUsuariosView().setVisible(true);
                dispose();
            });
        }

        add(btnSair);

        btnSair.addActionListener(e -> {
            new LoginView().setVisible(true);
            dispose();
        });

        setVisible(true);
    }
}
