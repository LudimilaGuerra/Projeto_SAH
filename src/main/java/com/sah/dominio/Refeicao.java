/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;

package com.sah.dominio;

import java.sql.Date;
import java.sql.Time;

public class Refeicao {
    private int idRefeicao;
    private int idPaciente;
    private int idCozinha;
    private Date data;
    private Time horario;
    private String status;
    private String feedback;

    public Refeicao() {}

    public Refeicao(int idRefeicao, int idPaciente, int idCozinha, Date data, Time horario, String status, String feedback) {
        this.idRefeicao = idRefeicao;
        this.idPaciente = idPaciente;
        this.idCozinha = idCozinha;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.feedback = feedback;
    }

    public int getIdRefeicao() { return idRefeicao; }
    public void setIdRefeicao(int idRefeicao) { this.idRefeicao = idRefeicao; }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public int getIdCozinha() { return idCozinha; }
    public void setIdCozinha(int idCozinha) { this.idCozinha = idCozinha; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }

    public Time getHorario() { return horario; }
    public void setHorario(Time horario) { this.horario = horario; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    @Override
    public String toString() {
        return "Refeicao [ID=" + idRefeicao + ", Paciente=" + idPaciente +
                ", Cozinha=" + idCozinha + ", Data=" + data + ", Horario=" + horario +
                ", Status=" + status + ", Feedback=" + feedback + "]";
    }
}
