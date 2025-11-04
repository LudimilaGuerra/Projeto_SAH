
package com.sah.infraestrutura;

import com.sah.dominio.Refeicao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepositorioRefeicao {
    /**
     * @param idPaciente 
     * @return true se o paciente for encontrado, false caso contrário
     */
    public boolean pacienteExiste(int idPaciente) {
        // Query para verificar a existência
        String sql = "SELECT 1 FROM Paciente WHERE ID_Paciente = ?"; 
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPaciente);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar a existência do paciente no repositório: " + e.getMessage());
            return false;
        }
    }


    public void inserir(Refeicao r) {
        if (!pacienteExiste(r.getIdPaciente())) {
            System.out.println("Erro de Integridade: Não é possível cadastrar a refeição. O Paciente com ID " + r.getIdPaciente() + " não existe.");
            return; // Interrompe a execução
        }
        // --------------------------------------------------------
        
        // Se o paciente existir, a inserção prossegue
        String sql = "INSERT INTO Refeicao (FK_Paciente, Data, Horario, Status, Feedback) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getIdPaciente());
            stmt.setDate(2, Date.valueOf(r.getData()));
            stmt.setString(3, r.getHorario());
            stmt.setString(4, r.getStatus());
            stmt.setString(5, r.getFeedback());
            stmt.executeUpdate();

            System.out.println("Refeição cadastrada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir refeição: " + e.getMessage());
        }
    }
    public List<Refeicao> listar() {
        List<Refeicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Refeicao";

        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Refeicao r = new Refeicao();
                r.setIdRefeicao(rs.getInt("ID_Refeicao"));
                r.setIdPaciente(rs.getInt("FK_Paciente"));
                r.setData(rs.getDate("Data").toLocalDate());
                r.setHorario(rs.getString("Horario"));
                r.setStatus(rs.getString("Status"));
                r.setFeedback(rs.getString("Feedback"));
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar refeições: " + e.getMessage());
        }

        return lista;
    }
    
}
