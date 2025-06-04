package org.example.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class GerenciarUsuariosView extends JFrame {
    private JTextField campoNome = new JTextField(10);
    private JTextField campoLogin = new JTextField(10);
    private JPasswordField campoSenha = new JPasswordField(10);
    private JComboBox<String> comboTipo = new JComboBox<>(new String[]{"admin", "comum"});

    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnEditar = new JButton("Editar");
    private JButton btnExcluir = new JButton("Excluir");
    private JButton btnLimpar = new JButton("Limpar");

    private JTable tabela = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();

    private UsuarioDAO dao = new UsuarioDAO();
    private int idSelecionado = -1;

    public GerenciarUsuariosView() {
        setTitle("Gerenciamento de Usuários");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Login");
        modelo.addColumn("Tipo");

        tabela.setModel(modelo);
        JScrollPane scroll = new JScrollPane(tabela);

        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Nome:"));
        painelTopo.add(campoNome);
        painelTopo.add(new JLabel("Login:"));
        painelTopo.add(campoLogin);
        painelTopo.add(new JLabel("Senha:"));
        painelTopo.add(campoSenha);
        painelTopo.add(new JLabel("Tipo:"));
        painelTopo.add(comboTipo);

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);

        add(painelTopo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        atualizarTabela();

        btnSalvar.addActionListener(e -> salvarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());
        btnExcluir.addActionListener(e -> excluirUsuario());
        btnLimpar.addActionListener(e -> limparCampos());


                tabela.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        int linha = tabela.getSelectedRow();
                        if (linha != -1) {
                            idSelecionado = (int) modelo.getValueAt(linha, 0);
                            campoNome.setText((String) modelo.getValueAt(linha, 1));
                            campoLogin.setText((String) modelo.getValueAt(linha, 2));
                            comboTipo.setSelectedItem((String) modelo.getValueAt(linha, 3));
                            campoSenha.setText(""); // senha não vem da tabela
                        }
                    }
                });
    }

    private void salvarUsuario() {
        try {
            Usuario usuario = new Usuario(
                    campoNome.getText(),
                    campoLogin.getText(),
                    new String(campoSenha.getPassword()),
                    comboTipo.getSelectedItem().toString()
            );
            dao.inserir(usuario);
            limparCampos();
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void editarUsuario() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
            return;
        }

        try {
            Usuario usuario = new Usuario(
                    idSelecionado,
                    campoNome.getText(),
                    campoLogin.getText(),
                    new String(campoSenha.getPassword()),
                    comboTipo.getSelectedItem().toString()
            );
            dao.atualizar(usuario);
            limparCampos();
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
        }
    }

    private void excluirUsuario() {
        if (idSelecionado == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
            return;
        }

        try {
            dao.deletar(idSelecionado);
            limparCampos();
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        campoNome.setText("");
        campoLogin.setText("");
        campoSenha.setText("");
        comboTipo.setSelectedIndex(0);
        idSelecionado = -1;
    }

    private void atualizarTabela() {
        try {
            modelo.setRowCount(0);
            List<Usuario> usuarios = dao.listar();
            for (Usuario u : usuarios) {
                modelo.addRow(new Object[]{
                        u.getId(), u.getNome(), u.getLogin(), u.getTipo()
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar usuários: " + ex.getMessage());
        }
    }
}