/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos.cliente;

import daos.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;

/**
 *
 * @author felip
 */
public class ClienteDaoImpl implements ClienteDao {

    private final String stmtInserir = "INSERT INTO cliente(cpf,nome,sobrenome) VALUES(?,?,?)";
    private final String stmtConsultar = "SELECT * FROM cliente WHERE id = ?";
    private final String stmtConsultarCpf = "SELECT * FROM cliente WHERE cpf = ?";
    private final String stmtPesquisar = "SELECT * FROM cliente WHERE cpf LIKE ? OR nome LIKE ? OR sobrenome LIKE ?";
    private final String stmtListar = "SELECT * FROM cliente";
    private final String stmtRemover = "DELETE FROM cliente WHERE cpf = ?";
    private final String stmtAtualizar = "UPDATE cliente SET cpf = ?, nome = ?, sobrenome = ? WHERE id = ?";

    @Override
    public void criar(Cliente p) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtInserir, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getSobrenome());
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
    public void removerPorCpf(String cpf) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtRemover);
            stmt.setString(1, cpf);
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
    public void atualizar(Cliente p) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtAtualizar);
            stmt.setString(1, p.getCpf());
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getSobrenome());
            stmt.setInt(4, p.getId());
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
    public List<Cliente> listar() {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtListar);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                cliente.setId(rs.getInt("id"));
                lista.add(cliente);
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
    public List<Cliente> listar(String query) {
        query = "%" + query + "%";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtPesquisar);
            stmt.setString(1, query);
            stmt.setString(2, query);
            stmt.setString(3, query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                cliente.setId(rs.getInt("id"));
                lista.add(cliente);
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
    public Cliente consultarPorId(int id) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultar);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Cliente cliente = null;
            while (rs.next()) {
                cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                cliente.setId(rs.getInt("id"));
            }
            return cliente;
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
    public Cliente consultarPorCpf(String cpf) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConnection();
            stmt = con.prepareStatement(stmtConsultarCpf);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            Cliente cliente = null;
            while (rs.next()) {
                cliente = new Cliente(rs.getString("cpf"), rs.getString("nome"), rs.getString("sobrenome"));
                cliente.setId(rs.getInt("id"));
            }
            return cliente;
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
