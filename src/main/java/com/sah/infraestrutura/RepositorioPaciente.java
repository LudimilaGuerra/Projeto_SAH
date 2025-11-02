/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.infraestrutura;


package com.sah.infraestrutura;

import com.sah.dominio.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioPaciente {
    private static final String URL = "jdbc:mysql://localhost:3306/sah";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // CREATE
    public void criar(Paciente p) {
        String sql = "INSERT INTO Paciente (ID_User, Restricao_Alimentar, Alergias, Preferencias, Quarto) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getIdUser());
            stmt.setString(2, p.getRestricaoAlimentar());
            stmt.setString(3, p.getAlergias());
            stmt.setString(4, p.getPreferencias());
            stmt.setString(5, p.getQuarto());
            stmt.executeUpdate();
            System.out.println("✅ Paciente cadastrado!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar paciente: " + e.getMessage());
        }
    }

    // READ
    public List<Paciente> listar() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Paciente";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Paciente(
                        rs.getInt("ID_Paciente"),
                        rs.getInt("ID_User"),
                        rs.getString("Restricao_Alimentar"),
                        rs.getString("Alergias"),
                        rs.getString("Preferencias"),
                        rs.getString("Quarto")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pacientes: " + e.getMessage());
        }
        return lista;
    }

    // UPDATE
    public void atualizar(Paciente p) {
        String sql = "UPDATE Paciente SET Restricao_Alimentar=?, Alergias=?, Preferencias=?, Quarto=? WHERE ID_Paciente=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getRestricaoAlimentar());
            stmt.setString(2, p.getAlergias());
            stmt.setString(3, p.getPreferencias());
            stmt.setString(4, p.getQuarto());
            stmt.setInt(5, p.getIdPaciente());
            stmt.executeUpdate();
            System.out.println("✅ Paciente atualizado!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    // DELETE
    public void deletar(int idPaciente) {
        String sql = "DELETE FROM Paciente WHERE ID_Paciente=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPaciente);
            stmt.executeUpdate();
            System.out.println("🗑️ Paciente removido!");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar paciente: " + e.getMessage());
        }
    }
}

