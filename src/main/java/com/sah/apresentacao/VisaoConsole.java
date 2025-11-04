/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author porti
 */
package com.sah.apresentacao;

import com.sah.dominio.ServicoPaciente;
import com.sah.dominio.ServicoRefeicao;
import java.util.Scanner;
import com.sah.dominio.ServicoLogin;

public class VisaoConsole {
    private final Scanner scanner = new Scanner(System.in);
    private final ServicoPaciente servicoPaciente = new ServicoPaciente();
    private final ServicoRefeicao servicoRefeicao = new ServicoRefeicao();
    private final ServicoLogin servicoLogin = new ServicoLogin();

    public void iniciar() {
        boolean logado = false;
        while (!logado) {
            System.out.println("\n=== LOGIN SAH ===");
            System.out.print("Login: ");
            String login = scanner.nextLine();
            System.out.print("Senha: ");
            String senha = scanner.nextLine();
            if (servicoLogin.autenticar(login, senha)) {
                logado = true;
                System.out.println("\nLogin realizado com sucesso!");
                menuPrincipal();
            } else {
                System.out.println("\nErro: Login ou senha inválidos. Tente novamente.");
            }
        }
    }

    private void menuPrincipal() {
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar Pacientes");
            System.out.println("2 - Gerenciar Refeições");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> servicoPaciente.menuPaciente(scanner);
                case 2 -> servicoRefeicao.menuRefeicao(scanner);
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
}
