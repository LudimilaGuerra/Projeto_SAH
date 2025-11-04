/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;

import java.time.LocalDate;

public class Refeicao {
    private int idRefeicao;
    private int idPaciente;
    private LocalDate data;
    private String horario;
    private String status;
    private String feedback;

    public Refeicao() {}

    public Refeicao(int idRefeicao, int idPaciente, LocalDate data, String horario, String status, String feedback) {
        this.idRefeicao = idRefeicao;
        this.idPaciente = idPaciente;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.feedback = feedback;
    }

    // Getters e Setters
    public int getIdRefeicao() { return idRefeicao; }
    public void setIdRefeicao(int idRefeicao) { this.idRefeicao = idRefeicao; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    @Override
    public String toString() {
        return "Refeicao{" +
                "idRefeicao=" + idRefeicao +
                ", idPaciente=" + idPaciente +
                ", data=" + data +
                ", horario='" + horario + '\'' +
                ", status='" + status + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
