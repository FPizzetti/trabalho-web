/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import daos.produto.ProdutoDao;
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

    public ProdutoDao produtoDao = new ProdutoDao();
    public Produto produto;

    protected void salvarProduto(HttpServletRequest request, HttpServletResponse response)
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
        List<Produto> produtos = produtoDao.listar();
        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/produtos.jsp").forward(request,
                response);
    }

    protected void editarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produto p = produtoDao.consultarPorId(id);
        if (p != null) {
            request.setAttribute("produto", p);
            request.getRequestDispatcher("/produto-form.jsp").forward(request,
                    response);
        } else {
            List<Produto> produtos = produtoDao.listar();
            request.setAttribute("produtos", produtos);
            request.getRequestDispatcher("/produtos.jsp").forward(request,
                    response);
        }
    }

    protected void removerProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (id > 0) {
            try {
                produtoDao.removerPorId(id);
                List<Produto> produtos = produtoDao.listar();
                request.setAttribute("produtos", produtos);
                request.getRequestDispatcher("/produtos.jsp").forward(request,
                        response);
            } catch (Exception e) {
                request.setAttribute("erro", "Não foi possível remover este produto");
                List<Produto> produtos = produtoDao.listar();
                request.setAttribute("produtos", produtos);
                request.getRequestDispatcher("/produtos.jsp").forward(request,
                        response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Verificar qual o tipo de operação a ser feita
        if (request.getParameter("action") != null
                && request.getParameter("action").equals("editar")) {
            editarProduto(request, response);
        } else if (request.getParameter("action") != null
                && request.getParameter("action").equals("remover")) {
            removerProduto(request, response);

        } else {
            //listar produtos
            List<Produto> produtos = produtoDao.listar();
            request.setAttribute("produtos", produtos);
            request.getRequestDispatcher("/produtos.jsp").forward(request,
                    response);
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
            salvarProduto(request, response);
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

}
