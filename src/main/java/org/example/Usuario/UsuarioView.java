package org.example.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UsuarioView extends JFrame {

    private Usuario usuario;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    private JTextField campoNome = new JTextField(20);
    private JTextField campoLogin = new JTextField(20);
    private JPasswordField campoSenha = new JPasswordField(20);
    private JCheckBox chkAdmin = new JCheckBox("Administrador");

    private JButton btnSalvar = new JButton("Salvar");

    public UsuarioView(Usuario usuario) {
        this.usuario = usuario;

        setTitle(usuario == null ? "Novo Usuário" : "Editar Usuário");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

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

        gbc.gridx = 1; gbc.gridy = 3;
        add(chkAdmin, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        add(btnSalvar, gbc);

        if (usuario != null) {
            carregarDados();
        }

        btnSalvar.addActionListener(e -> salvarUsuario());
    }

    private void carregarDados() {
        campoNome.setText(usuario.getNome());
        campoLogin.setText(usuario.getLogin());
        campoSenha.setText(usuario.getSenha());  // Se estiver usando senha criptografada, pode ter que ajustar isso
        chkAdmin.setSelected(usuario.isAdmin());
    }

    private void salvarUsuario() {
        String nome = campoNome.getText().trim();
        String login = campoLogin.getText().trim();
        String senha = new String(campoSenha.getPassword()).trim();
        boolean admin = chkAdmin.isSelected();

        if (nome.isEmpty() || login.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        try {
            if (usuario == null) {
                usuario = new Usuario(nome, login, senha, admin);
                usuarioDAO.inserir(usuario);
            } else {
                usuario.setNome(nome);
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuario.setAdmin(admin);
                usuarioDAO.atualizar(usuario);
            }
            JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso.");
            new ListaUsuariosView().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar usuário: " + ex.getMessage());
        }
    }
}
