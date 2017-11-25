/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author felip
 */
public class Pedido {

    private double preco;
    
    private int id;
    
    private Cliente cliente;
    
    private Date data;
    private List<ItemPedido> itens;

    public Pedido() {
        this.itens = new ArrayList<>();
        this.data = new Date();
    }
    
    public double getTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getProduto().getPreco();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void addItem(ItemPedido i) {
        this.itens.add(i);
    }

    public void removeItem(ItemPedido i) {
        this.itens.remove(i);
    }
}
