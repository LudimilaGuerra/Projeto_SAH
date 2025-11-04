/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;

import com.sah.infraestrutura.RepositorioRefeicao;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ServicoRefeicao {
    private final RepositorioRefeicao repo = new RepositorioRefeicao();

    public void menuRefeicao(Scanner sc) {
        int opcao;
        do {
            System.out.println("\n=== MENU REFEIÇÃO ===");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Listar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> cadastrar(sc);
                case 2 -> listarOrganizado();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrar(Scanner sc) {
        Refeicao r = new Refeicao();
        System.out.print("ID do Paciente: ");
        r.setIdPaciente(Integer.parseInt(sc.nextLine()));
        System.out.print("Data (AAAA-MM-DD): ");
        r.setData(LocalDate.parse(sc.nextLine()));
        System.out.print("Horário: ");
        r.setHorario(sc.nextLine());
        System.out.print("Status: ");
        r.setStatus(sc.nextLine());
        System.out.print("Feedback: ");
        r.setFeedback(sc.nextLine());
        repo.inserir(r);
    }

    private void listarOrganizado() {
        List<Refeicao> refeicoes = repo.listar();

        if (refeicoes.isEmpty()) {
            System.out.println("\n️ Não há refeições registradas no momento.");
            return;
        }

        System.out.println("\n--- Registro de Refeições ---");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        String formatoCabecalho = "| %-10s | %-12s | %-12s | %-10s | %-15s | %-40s |%n";
        System.out.format(formatoCabecalho, 
                          "ID Ref.", "ID Paciente", "Data", "Horário", "Status", "Feedback");
        
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        String formatoLinha = "| %-10d | %-12d | %-12s | %-10s | %-15s | %-40s |%n";
        
        for (Refeicao r : refeicoes) {
            System.out.format(formatoLinha,
                              r.getIdRefeicao(),
                              r.getIdPaciente(),
                              r.getData().toString(), 
                              r.getHorario(),
                              r.getStatus(),
                              r.getFeedback());
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
    }
}
