/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.produto.ProdutoDao;
import daos.produto.ProdutoDaoImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Produto;

/**
 *
 * @author felip
 */
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/ProdutoServlet"})
public class ProdutoServlet extends HttpServlet {

    public ProdutoDao produtoDao;

    public Produto produto;

    public ProdutoServlet() {
        produtoDao = new ProdutoDaoImpl();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processSaveRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            id = 0;
        }
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));

        produto = new Produto(id, descricao, preco);

        if (id != 0) {
            produtoDao.atualizar(produto);
        } else {
            produtoDao.criar(produto);
        }
        redirectProducts(request, response);
    }

    protected void processListRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        redirectProducts(request, response);
    }

    protected void processEditRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto p = produtoDao.consultarPorId(id);
        if (p != null) {
            request.setAttribute("produto", p);
            request.getRequestDispatcher("/produto-form.jsp").forward(request,
                    response);
        } else {
            redirectProducts(request, response);
        }
    }

    protected void processRemoveRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (id > 0) {
            produtoDao.removerPorId(id);
            redirectProducts(request, response);
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
        String action = request.getParameter("action");
        if (action != null && action.equals("remover")) {
            processRemoveRequest(request, response);
        } else if (action != null && action.equals("editar")) {
            processEditRequest(request, response);
        } else {
            processListRequest(request, response);
        }
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
        String action = request.getParameter("action");
        if (action.equals("salvar")) {
            processSaveRequest(request, response);
        }
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

    private void redirectProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Produto> produtos = produtoDao.listar();
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/produtos.jsp").forward(request,
                response);
    }

}
