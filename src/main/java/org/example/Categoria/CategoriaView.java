package org.example.Categoria;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class CategoriaView extends JFrame{
    private JTextField campoNome = new JTextField(20);
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnEditar = new JButton("Editar");
    private JButton btnExcluir = new JButton("Excluir");
    private JTable tabela = new JTable();
    private DefaultTableModel modelo = new DefaultTableModel();
    private CategoriaDAO dao = new CategoriaDAO();
    private int idSelecionado = -1;
    private boolean isAdmin;

    public CategoriaView(boolean isAdmin) {
        this.isAdmin = isAdmin;
        setTitle("Gerenciar Categorias");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        tabela.setModel(modelo);

        JPanel painel = new JPanel(new BorderLayout());
        JPanel topo = new JPanel();

        if (isAdmin) {
            topo.add(new JLabel("Nome:"));
            topo.add(campoNome);
            topo.add(btnSalvar);
            topo.add(btnEditar);
            topo.add(btnExcluir);
        }

        painel.add(topo, BorderLayout.NORTH);
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        add(painel);
        atualizarTabela();

        if (isAdmin) {
            btnSalvar.addActionListener(e -> salvarCategoria());
            btnEditar.addActionListener(e -> editarCategoria());
            btnExcluir.addActionListener(e -> excluirCategoria());

            tabela.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int linha = tabela.getSelectedRow();
                    if (linha != -1) {
                        idSelecionado = (int) modelo.getValueAt(linha, 0);
                        campoNome.setText((String) modelo.getValueAt(linha, 1));
                    }
                }
            });
        }
    }

    private void salvarCategoria() {
        try {
            dao.inserir(new Categoria(campoNome.getText()));
            campoNome.setText("");
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    private void editarCategoria() {
        if (idSelecionado == -1) return;
        try {
            dao.atualizar(new Categoria(idSelecionado, campoNome.getText()));
            campoNome.setText("");
            idSelecionado = -1;
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao editar: " + ex.getMessage());
        }
    }

    private void excluirCategoria() {
        if (idSelecionado == -1) return;
        try {
            dao.deletar(idSelecionado);
            campoNome.setText("");
            idSelecionado = -1;
            atualizarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
        }
    }

    private void atualizarTabela() {
        try {
            modelo.setRowCount(0);
            List<Categoria> categorias = dao.listar();
            for (Categoria c : categorias) {
                modelo.addRow(new Object[]{c.getId(), c.getNome()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao listar: " + ex.getMessage());
        }
    }
}
