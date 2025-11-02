/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.apresentacao;

/**
 *
 * @author porti
 */
package com.sah.apresentacao;

import com.sah.dominio.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class VisaoConsole {

    private final ServicoUsuario servicoUsuario = new ServicoUsuario();
    private final ServicoPaciente servicoPaciente = new ServicoPaciente();
    private final ServicoRefeicao servicoRefeicao = new ServicoRefeicao();
    private final Scanner sc = new Scanner(System.in);

    public void iniciar() {
        System.out.println("=== LOGIN SAH ===");
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        Usuario usuario = servicoUsuario.login(login, senha);

        if (usuario == null) {
            System.out.println("❌ Usuário ou senha incorretos.");
            return;
        }

        System.out.println("\n✅ Bem-vindo, " + usuario.getNome() + " (" + usuario.getTipo() + ")");
        menuPrincipal(usuario);
    }

    private void menuPrincipal(Usuario usuario) {
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Gerenciar Usuários");
            System.out.println("2 - Gerenciar Pacientes");
            System.out.println("3 - Gerenciar Refeições");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> menuUsuarios(usuario);
                case 2 -> menuPacientes(usuario);
                case 3 -> menuRefeicoes(usuario);
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===================== MENU DE USUÁRIOS =====================
    private void menuUsuarios(Usuario usuario) {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Usuários ---");
            System.out.println("1 - Listar usuários");
            if (!usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                System.out.println("2 - Criar usuário");
                System.out.println("3 - Atualizar usuário");
                System.out.println("4 - Deletar usuário");
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> servicoUsuario.listarUsuarios().forEach(System.out::println);
                case 2 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    Usuario novo = new Usuario();
                    System.out.print("Nome: "); novo.setNome(sc.nextLine());
                    System.out.print("Login: "); novo.setLogin(sc.nextLine());
                    System.out.print("Senha: "); novo.setSenha(sc.nextLine());
                    System.out.print("Tipo (Medico/Nutricionista/Enfermeiro): ");
                    novo.setTipo(sc.nextLine());
                    servicoUsuario.criarUsuario(novo);
                }
                case 3 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    System.out.print("ID do usuário: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Usuario u = new Usuario();
                    u.setId(id);
                    System.out.print("Novo nome: "); u.setNome(sc.nextLine());
                    System.out.print("Novo login: "); u.setLogin(sc.nextLine());
                    System.out.print("Nova senha: "); u.setSenha(sc.nextLine());
                    System.out.print("Novo tipo: "); u.setTipo(sc.nextLine());
                    servicoUsuario.atualizarUsuario(u);
                }
                case 4 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    System.out.print("ID do usuário: ");
                    int id = Integer.parseInt(sc.nextLine());
                    servicoUsuario.deletarUsuario(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===================== MENU DE PACIENTES =====================
    private void menuPacientes(Usuario usuario) {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Pacientes ---");
            System.out.println("1 - Listar pacientes");
            if (!usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                System.out.println("2 - Cadastrar paciente");
                System.out.println("3 - Atualizar paciente");
                System.out.println("4 - Deletar paciente");
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> servicoPaciente.listar().forEach(System.out::println);
                case 2 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    Paciente p = new Paciente();
                    System.out.print("ID do usuário vinculado: ");
                    p.setIdUser(Integer.parseInt(sc.nextLine()));
                    System.out.print("Restrição alimentar: "); p.setRestricaoAlimentar(sc.nextLine());
                    System.out.print("Alergias: "); p.setAlergias(sc.nextLine());
                    System.out.print("Preferências: "); p.setPreferencias(sc.nextLine());
                    System.out.print("Quarto: "); p.setQuarto(sc.nextLine());
                    servicoPaciente.criar(p);
                }
                case 3 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    System.out.print("ID do paciente: ");
                    int id = Integer.parseInt(sc.nextLine());
                    Paciente p = new Paciente();
                    p.setIdPaciente(id);
                    System.out.print("Nova restrição alimentar: "); p.setRestricaoAlimentar(sc.nextLine());
                    System.out.print("Novas alergias: "); p.setAlergias(sc.nextLine());
                    System.out.print("Novas preferências: "); p.setPreferencias(sc.nextLine());
                    System.out.print("Novo quarto: "); p.setQuarto(sc.nextLine());
                    servicoPaciente.atualizar(p);
                }
                case 4 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    System.out.print("ID do paciente: ");
                    int id = Integer.parseInt(sc.nextLine());
                    servicoPaciente.deletar(id);
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    // ===================== MENU DE REFEIÇÕES =====================
    private void menuRefeicoes(Usuario usuario) {
        int opcao;
        do {
            System.out.println("\n--- Gerenciar Refeições ---");
            System.out.println("1 - Listar refeições");
            if (!usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                System.out.println("2 - Cadastrar refeição");
            }
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 -> listarRefeicoes();
                case 2 -> {
                    if (usuario.getTipo().equalsIgnoreCase("Enfermeiro")) {
                        System.out.println("Acesso negado!");
                        break;
                    }
                    cadastrarRefeicao();
                }
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void listarRefeicoes() {
        List<Refeicao> lista = servicoRefeicao.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma refeição cadastrada.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    private void cadastrarRefeicao() {
        Refeicao r = new Refeicao();
        System.out.print("ID do paciente: ");
        r.setIdPaciente(Integer.parseInt(sc.nextLine()));
        System.out.print("ID da cozinha: ");
        r.setIdCozinha(Integer.parseInt(sc.nextLine()));
        System.out.print("Data (YYYY-MM-DD): ");
        r.setData(Date.valueOf(sc.nextLine()));
        System.out.print("Horário (HH:MM:SS): ");
        r.setHorario(Time.valueOf(sc.nextLine()));
        System.out.print("Status: ");
        r.setStatus(sc.nextLine());
        System.out.print("Feedback: ");
        r.setFeedback(sc.nextLine());
        servicoRefeicao.criar(r);
    }
}
