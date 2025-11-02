/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;

/**
 *
 * @author porti
 */
package com.sah.dominio;

public class Paciente {
    private int idPaciente;
    private int idUser;
    private String restricaoAlimentar;
    private String alergias;
    private String preferencias;
    private String quarto;

    public Paciente() {}

    public Paciente(int idPaciente, int idUser, String restricaoAlimentar, String alergias, String preferencias, String quarto) {
        this.idPaciente = idPaciente;
        this.idUser = idUser;
        this.restricaoAlimentar = restricaoAlimentar;
        this.alergias = alergias;
        this.preferencias = preferencias;
        this.quarto = quarto;
    }

    public int getIdPaciente() { return idPaciente; }
    public void setIdPaciente(int idPaciente) { this.idPaciente = idPaciente; }

    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }

    public String getRestricaoAlimentar() { return restricaoAlimentar; }
    public void setRestricaoAlimentar(String restricaoAlimentar) { this.restricaoAlimentar = restricaoAlimentar; }

    public String getAlergias() { return alergias; }
    public void setAlergias(String alergias) { this.alergias = alergias; }

    public String getPreferencias() { return preferencias; }
    public void setPreferencias(String preferencias) { this.preferencias = preferencias; }

    public String getQuarto() { return quarto; }
    public void setQuarto(String quarto) { this.quarto = quarto; }

    @Override
    public String toString() {
        return "Paciente [ID=" + idPaciente + ", ID_User=" + idUser +
                ", Restricao=" + restricaoAlimentar + ", Alergias=" + alergias +
                ", Preferencias=" + preferencias + ", Quarto=" + quarto + "]";
    }
}
