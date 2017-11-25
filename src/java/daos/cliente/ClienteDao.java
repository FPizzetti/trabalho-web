/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.cliente;

import java.util.List;
import models.Cliente;

/**
 *
 * @author felip
 */
public interface ClienteDao {

    public void criar(Cliente c);

    public void removerPorCpf(String cpf);

    public Cliente consultarPorId(int id);

    public Cliente consultarPorCpf(String cpf);

    public void atualizar(Cliente c);

    public List<Cliente> listar();

    public List<Cliente> listar(String query);

    public long count();
}
