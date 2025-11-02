/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;


package com.sah.dominio;

import com.sah.infraestrutura.RepositorioPaciente;
import java.util.List;

public class ServicoPaciente {
    private final RepositorioPaciente repo = new RepositorioPaciente();

    public void criar(Paciente p) { repo.criar(p); }
    public List<Paciente> listar() { return repo.listar(); }
    public void atualizar(Paciente p) { repo.atualizar(p); }
    public void deletar(int id) { repo.deletar(id); }
}

