/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.pedido;

import java.util.List;
import models.ItemPedido;
import models.Pedido;

/**
 *
 * @author felip
 */
public interface PedidoDao {

    public void criar(Pedido p);

    public void adicionarItem(ItemPedido i);

    public void removerItem(int produtoId);

    public List<ItemPedido> listarItens(int idPedido);

    public List<Pedido> listarPedidos();

    public List<Pedido> listarPedidos(String query);
}
