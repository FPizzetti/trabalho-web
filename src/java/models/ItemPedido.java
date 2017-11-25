/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * @author felip
 */
public class ItemPedido {

    private Produto produto;
    private Pedido pedido;
    private int quantidade;

    public ItemPedido(Pedido pedido, Produto produto, int quantidade) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
    }
    
    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (this.quantidade != other.quantidade) {
            return false;
        }
        if (this.produto == null || other.produto == null || this.pedido == null || other.pedido == null) {
            return false;
        }
        if (!Objects.equals(this.produto.getId(), other.produto.getId())) {
            return false;
        }
        if (!Objects.equals(this.pedido.getId(), other.pedido.getId())) {
            return false;
        }
        return true;
    }

}
