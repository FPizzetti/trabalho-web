/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.cliente.ClienteDao;
import daos.cliente.ClienteDaoImpl;
import daos.pedido.PedidoDao;
import daos.pedido.PedidoDaoImpl;
import daos.produto.ProdutoDao;
import daos.produto.ProdutoDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Cliente;
import models.ItemPedido;
import models.Pedido;
import models.Produto;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Matheus
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/PedidoServlet"})
public class PedidoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            PedidoDao pedidoDao = new PedidoDaoImpl();
            ProdutoDao produtoDao = new ProdutoDaoImpl();
            Pedido pedido = new Pedido();
            ClienteDao clienteDao = new ClienteDaoImpl();

            String acao = request.getParameter("acao");

            if (acao == null) {

                List<Pedido> pedidos = pedidoDao.listarPedidos("");

                request.setAttribute("pedidos", pedidos);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pedidos.jsp");
                rd.forward(request, response);
            } else if (acao.equals("visualizar")) {

                int id = Integer.parseInt(request.getParameter("id"));

                float total = 0;

                List<ItemPedido> itens = pedidoDao.listarItens(id);

                for (ItemPedido element : itens) {
                    total += element.getProduto().getPreco() * element.getQuantidade();
                }

                request.setAttribute("itens", itens);
                request.setAttribute("total", total);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pedido-detalhes.jsp");
                rd.forward(request, response);
            } else if (acao.equals("novo")) {

                List<Cliente> clientes = clienteDao.listar();
                List<Produto> produtos = produtoDao.listar();

                request.setAttribute("clientes", clientes);
                request.setAttribute("produtos", produtos);
                
                if (!request.getParameter("gravado").equals(null))
                    request.setAttribute("gravado", request.getParameter("gravado"));

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pedido-novo.jsp");
                rd.forward(request, response);
                
            } else if (acao.equals("gravar")) {
                
                String result;
                String jsonData = request.getParameter("items");
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray jsonArray = jsonObject.getJSONArray("itens");

                Cliente c = clienteDao.consultarPorId(jsonObject.getInt("cliente"));

                if (c != null) {
                    
                    pedido.setCliente(c);
                    pedidoDao.criar(pedido);
 
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject produtoJson = jsonArray.getJSONObject(i);
                        
                        Produto produto = produtoDao.consultarPorId(produtoJson.getInt("id"));
                        ItemPedido item = new ItemPedido(pedido, produto, produtoJson.getInt("quantidade"));
                        pedidoDao.adicionarItem(item);
                    }

                } 
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
