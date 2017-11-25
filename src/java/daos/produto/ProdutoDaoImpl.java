/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.produto;

import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Produto;

/**
 *
 * @author felip
 */
public class ProdutoDaoImpl implements ProdutoDao {

    private final String stmtInserir = "INSERT INTO produto(descricao, preco) VALUES(?,?)";
    private final String stmtConsultar = "SELECT * FROM produto WHERE id = ?";
    private final String stmtPesquisar = "SELECT * FROM produto WHERE descricao LIKE ?";
    private final String stmtListar = "SELECT * FROM produto";
    private final String stmtRemover = "DELETE FROM produto WHERE id = ?";
    private final String stmtAtualizar = "UPDATE produto SET descricao = ?, preco = ? WHERE id = ?";

    public ProdutoDaoImpl() {
    }

    @Override
    public void criar(Produto p) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, p.getPreco());
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

    @Override
    public void removerPorId(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtRemover);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao remover um produto no banco de dados. Origem=" + ex.getMessage());
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
    public void atualizar(Produto p) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);
            stmt.setString(1, p.getDescricao());
            stmt.setDouble(2, (p.getPreco() / 100));
            stmt.setInt(3, p.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar um produto no banco de dados. Origem=" + ex.getMessage());
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
    public List<Produto> listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("descricao"));
                produto.setId(rs.getInt("id"));
                produto.setPreco(rs.getDouble("preco"));
                lista.add(produto);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de produtos. Origem=" + ex.getMessage());
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
    public List<Produto> listar(String query) {
        query = "%" + query + "%";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtPesquisar);
            stmt.setString(1, query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto(rs.getString("descricao"));
                produto.setId(rs.getInt("id"));
                produto.setPreco(rs.getDouble("preco"));
                lista.add(produto);
            }
            return lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de produtos. Origem=" + ex.getMessage());
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
    public long count() {
        return 0;
    }

    private int getId(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    @Override
    public Produto consultarPorId(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Produto produto = null;
            while (rs.next()) {
                produto = new Produto(rs.getString("descricao"));
                produto.setId(rs.getInt("id"));
                produto.setPreco(rs.getDouble("preco"));
            }
            return produto;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao consultar uma lista de produtos. Origem=" + ex.getMessage());
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
