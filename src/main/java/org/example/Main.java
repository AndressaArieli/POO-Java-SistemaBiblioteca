package org.example;

import org.example.Login.LoginView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new LoginView();
        String[] opcoes = {"Admin", "Comum"};
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha o tipo de usuário:",
                "Login",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );
            SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}