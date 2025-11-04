/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;

import com.sah.infraestrutura.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author luizt
 */
public class ServicoLogin {
    /**
     * @param 
     * @param 
     * @return 
     */
    public boolean autenticar(String login, String senha) {
        // busca o usuário com o login e senha correspondentes.
        // 
        String sql = "SELECT * FROM usuario WHERE Login = ? AND Senha = ?";
        
        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {
             
            stmt.setString(1, login);
            stmt.setString(2, senha);

            // Executa a consulta
            try (ResultSet rs = stmt.executeQuery()) {
                // Se rs.next() retornar true, significa que uma linha foi encontrada
                //  login e a senha estão corretos no banco.
                return rs.next(); 
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar usuário: " + e.getMessage());
            return false;
        }
    }
    
}
