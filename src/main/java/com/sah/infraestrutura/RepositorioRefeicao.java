/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.infraestrutura;

package com.sah.infraestrutura;

import com.sah.dominio.Refeicao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioRefeicao {
    private static final String URL = "jdbc:mysql://localhost:3306/sah";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void criar(Refeicao r) {
        String sql = "INSERT INTO Refeicao (FK_Paciente, FK_Cozinha, Data, Horario, Status, Feedback) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, r.getIdPaciente());
            stmt.setInt(2, r.getIdCozinha());
            stmt.setDate(3, r.getData());
            stmt.setTime(4, r.getHorario());
            stmt.setString(5, r.getStatus());
            stmt.setString(6, r.getFeedback());
            stmt.executeUpdate();
            System.out.println("✅ Refeição registrada!");
        } catch (SQLException e) {
            System.out.println("Erro ao criar refeição: " + e.getMessage());
        }
    }

    public List<Refeicao> listar() {
        List<Refeicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Refeicao";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Refeicao(
                        rs.getInt("ID_Refeicao"),
                        rs.getInt("FK_Paciente"),
                        rs.getInt("FK_Cozinha"),
                        rs.getDate("Data"),
                        rs.getTime("Horario"),
                        rs.getString("Status"),
                        rs.getString("Feedback")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar refeições: " + e.getMessage());
        }
        return lista;
    }
}
