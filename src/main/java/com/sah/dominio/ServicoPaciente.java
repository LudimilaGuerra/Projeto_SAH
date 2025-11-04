
package com.sah.dominio;

import com.sah.infraestrutura.RepositorioPaciente;
import java.util.List;
import java.util.Scanner;

public class ServicoPaciente {
    private final RepositorioPaciente repo = new RepositorioPaciente();

    public void menuPaciente(Scanner sc) {
        int opcao;
        do {
            System.out.println("\n=== MENU PACIENTE ===");
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
        Paciente p = new Paciente();
        System.out.print("ID do Usuário: ");
        p.setIdUser(Integer.parseInt(sc.nextLine()));
        System.out.print("Restrição Alimentar: ");
        p.setRestricaoAlimentar(sc.nextLine());
        System.out.print("Alergias: ");
        p.setAlergias(sc.nextLine());
        System.out.print("Preferências: ");
        p.setPreferencias(sc.nextLine());
        System.out.print("Quarto: ");
        p.setQuarto(sc.nextLine());
        repo.inserir(p);
    }

    private void listarOrganizado() {
        List<Paciente> pacientes = repo.listar();

        if (pacientes.isEmpty()) {
            System.out.println("\nℹ️ Não há pacientes cadastrados no momento.");
            return;
        }

        System.out.println("\n--- Lista de Pacientes Cadastrados ---");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        
        String formatoCabecalho = "| %-30s | %-12s | %-20s | %-20s | %-20s | %-6s |%n";
        System.out.format(formatoCabecalho, 
                          "Nome do Paciente", "ID Usuário", "Restrição", "Alergias", "Preferências", "Quarto");
        
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");

        String formatoLinha = "| %-30s | %-12d | %-20s | %-20s | %-20s | %-6s |%n";
        
        for (Paciente p : pacientes) {
            // CORREÇÃO AQUI: O primeiro argumento agora é p.getNome()
            System.out.format(formatoLinha,
                              p.getnome(), // Informação do Nome (vinda do JOIN)
                              p.getIdUser(),
                              p.getRestricaoAlimentar(),
                              p.getAlergias(),
                              p.getPreferencias(),
                              p.getQuarto());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
    }
}
