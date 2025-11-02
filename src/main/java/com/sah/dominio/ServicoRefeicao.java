/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sah.dominio;

package com.sah.dominio;

import com.sah.infraestrutura.RepositorioRefeicao;
import java.util.List;

public class ServicoRefeicao {
    private final RepositorioRefeicao repo = new RepositorioRefeicao();

    public void criar(Refeicao r) { repo.criar(r); }
    public List<Refeicao> listar() { return repo.listar(); }
}
