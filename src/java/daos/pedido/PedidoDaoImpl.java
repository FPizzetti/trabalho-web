/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.pedido;

import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;
import models.ItemPedido;
import models.Pedido;
import models.Produto;

/**
 *
 * @author felip
 */
public class PedidoDaoImpl implements PedidoDao {

    private final String stmtInserirItemPedido = "INSERT INTO item_pedido(pedido, produto, quantidade) VALUES(?,?,?)";
    private final String stmtInserirPedido = "INSERT INTO pedido(descricao, cliente) VALUES(?,?)";
    private final String stmtListar = "SELECT * FROM pedido";
    private final String stmtListarItens = "SELECT * FROM item_pedido INNER JOIN produto ON item_pedido.produto = produto.id WHERE pedido = ?";
    private final String stmtPesquisar = "SELECT * FROM pedido INNER JOIN cliente ON pedido.cliente = cliente.id  WHERE cpf LIKE ?";
    private final String stmtRemover = "DELETE FROM produto WHERE id = ?";
    private final String stmtAtualizar = "UPDATE produto SET descricao = ?, preco = ? WHERE id = ?";

    @Override
    public void criar(Pedido p) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserirPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, null);
            stmt.setInt(2, p.getCliente().getId());
            stmt.executeUpdate();
            p.setId(getId(stmt));
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um produto no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

    private int getId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public void adicionarItem(ItemPedido i) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserirItemPedido, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, i.getPedido().getId());
            stmt.setInt(2, i.getProduto().getId());
            stmt.setInt(3, i.getQuantidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao inserir um item no banco de dados. Origem=" + ex.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

    @Override
    public void removerItem(int produtoId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> listarItens(int idPedido) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ItemPedido> lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListarItens);
            stmt.setInt(1, idPedido);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                
                produto.setId((rs.getInt("id")));
                produto.setDescricao((rs.getString("descricao")));
                produto.setPreco((rs.getDouble("preco")));
                
                ItemPedido itemPedido = new ItemPedido(produto, rs.getInt("quantidade"));
                
                lista.add(itemPedido);
            }
            
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de pedidos. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            }
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

    @Override
    public List<Pedido> listarPedidos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Pedido> listarPedidos(String query) {
        query = "%" + query + "%";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Pedido> lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtPesquisar);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                Cliente cliente = new Cliente(rs.getInt("cliente"), rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                
                pedido.setId(rs.getInt("id"));
                pedido.setData(rs.getDate("data"));
                pedido.setCliente(cliente);
                
                lista.add(pedido);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de pedidos. Origem=" + ex.getMessage());
        } finally {
            try {
                rs.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar result set. Ex=" + ex.getMessage());
            }
            try {
                stmt.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar stmt. Ex=" + ex.getMessage());
            }
            try {
                con.close();
            } catch (Exception ex) {
                System.out.println("Erro ao fechar conexão. Ex=" + ex.getMessage());
            }
        }
    }

}
