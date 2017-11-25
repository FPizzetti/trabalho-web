/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.produto;

import java.util.List;
import models.Produto;

/**
 *
 * @author felip
 */
public interface ProdutoDao {

    public void criar(Produto p);

    public void removerPorId(int id);

    public Produto consultarPorId(int id);

    public void atualizar(Produto p);

    public List<Produto> listar();

    public List<Produto> listar(String query);

    public long count();
}
