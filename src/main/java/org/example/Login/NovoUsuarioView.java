package org.example.Login;
import org.example.Usuario.Usuario;
import org.example.Usuario.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class NovoUsuarioView extends JDialog{
    private JTextField campoNome = new JTextField(15);
    private JTextField campoLogin = new JTextField(15);
    private JPasswordField campoSenha = new JPasswordField(15);
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"comum", "admin"});
    private JButton btnCriar = new JButton("Criar");
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public NovoUsuarioView(JFrame parent) {
        super(parent, "Criar Novo Usuário", true);
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        add(campoNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Login:"), gbc);
        gbc.gridx = 1;
        add(campoLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        add(campoSenha, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        add(comboTipo, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        add(btnCriar, gbc);

        btnCriar.addActionListener(e -> {
            try {
                String nome = campoNome.getText();
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());
                String tipo = (String) comboTipo.getSelectedItem();

                if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                    return;
                }

                Usuario novoUsuario = new Usuario(nome, login, senha, tipo);
                usuarioDAO.inserir(novoUsuario);
                JOptionPane.showMessageDialog(this, "Usuário criado com sucesso!");
                dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao criar usuário: " + ex.getMessage());
            }
        });
    }
}
