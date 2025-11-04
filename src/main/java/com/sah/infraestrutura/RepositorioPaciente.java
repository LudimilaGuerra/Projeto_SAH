package com.sah.infraestrutura;

import com.sah.dominio.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPaciente {

    public void inserir(Paciente paciente) {
        int idUser = paciente.getIdUser(); 

    String sqlUserExiste = "SELECT 1 FROM Usuario WHERE ID_User = ?";
    boolean userExiste = false;

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmtUser = conn.prepareStatement(sqlUserExiste)) {

        stmtUser.setInt(1, idUser);
        
        try (ResultSet rs = stmtUser.executeQuery()) {
            if (rs.next()) {
                userExiste = true; 
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao verificar a existência do usuário: " + e.getMessage());
        return; 
    }

    if (!userExiste) {
        System.out.println("Erro de Integridade: O Usuário com ID " + idUser + " não está cadastrado na tabela USUARIO.");
        return;
    }
        
        String sqlVerificacao = "SELECT 1 FROM Paciente WHERE FK_ID_User = ?";
    boolean pacienteExiste = false;

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmtVerificacao = conn.prepareStatement(sqlVerificacao)) {

        stmtVerificacao.setInt(1, paciente.getIdUser());
        
        try (ResultSet rs = stmtVerificacao.executeQuery()) {
            if (rs.next()) {
                pacienteExiste = true; // Se encontrar pelo menos uma linha, o paciente existe
            }
        }

    } catch (SQLException e) {
        System.out.println("Erro ao verificar a existência do paciente: " + e.getMessage());
        return;
    }

    
    if (pacienteExiste) {
        System.out.println(" Paciente JA CADASTRADO! Nao e possível inserir o usuário ID: " + paciente.getIdUser());
        return; // Sai do método e não executa a inserção
    }

    //LÓGICA DE INSERÇÃO
    String sqlInsercao = "INSERT INTO Paciente (FK_ID_User, Restricao_Alimentar, Alergias, Preferencias, Quarto) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.getConnection();
         PreparedStatement stmtInsercao = conn.prepareStatement(sqlInsercao)) {

        stmtInsercao.setInt(1, paciente.getIdUser());
        stmtInsercao.setString(2, paciente.getRestricaoAlimentar());
        stmtInsercao.setString(3, paciente.getAlergias());
        stmtInsercao.setString(4, paciente.getPreferencias());
        stmtInsercao.setString(5, paciente.getQuarto());
        stmtInsercao.executeUpdate();

        System.out.println("Paciente cadastrado com sucesso!");

        } 
        catch (SQLException e) {
        System.out.println(" Erro ao inserir paciente: " + e.getMessage());
        }
    }

    public List<Paciente> listar() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT p.*, u.Nome " + 
                     "FROM paciente p " +
                     "JOIN usuario u ON p.FK_ID_User = u.ID_User " +
                     "ORDER BY u.Nome";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdPaciente(rs.getInt("ID_Paciente"));
                p.setIdUser(rs.getInt("FK_ID_User"));
                p.setnome(rs.getString("Nome"));
                p.setRestricaoAlimentar(rs.getString("Restricao_Alimentar"));
                p.setAlergias(rs.getString("Alergias"));
                p.setPreferencias(rs.getString("Preferencias"));
                p.setQuarto(rs.getString("Quarto"));
                pacientes.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return pacientes;
    }
    
}


