package org.example.Login;
import org.example.Usuario.Usuario;
import org.example.Usuario.UsuarioDAO;
import org.example.Categoria.CategoriaView;
import org.example.TelaInicial.TelaInicial;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginView  extends JFrame {
    private JTextField campoLogin = new JTextField(15);
    private JPasswordField campoSenha = new JPasswordField(15);
    private JButton btnEntrar = new JButton("Entrar");
    private JButton btnNovoUsuario = new JButton("Criar novo usuário");

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public LoginView() {
        setTitle("Login");
        setSize(350, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Login:"), gbc);

        gbc.gridx = 1;
        add(campoLogin, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Senha:"), gbc);

        gbc.gridx = 1;
        add(campoSenha, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(btnEntrar, gbc);

        gbc.gridx = 1;
        add(btnNovoUsuario, gbc);

        btnEntrar.addActionListener(e -> {
            try {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());
                if (usuarioDAO.validarLogin(login, senha)) {
                    Usuario usuario = usuarioDAO.buscarUsuarioPorLogin(login);
                    JOptionPane.showMessageDialog(this, "Bem-vindo(a), " + usuario.getNome() + "!");
                    abrirTelaPrincipal(usuario.getTipo());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Login ou senha inválidos.");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        btnNovoUsuario.addActionListener(e -> {
            new NovoUsuarioView(this).setVisible(true);
        });
    }

    private void abrirTelaPrincipal(String tipo) {
        SwingUtilities.invokeLater(() -> new CategoriaView(tipo.equals("admin")).setVisible(true));
    }
}
