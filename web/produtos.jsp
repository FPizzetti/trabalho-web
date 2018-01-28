<%-- 
    Document   : produtos
    Created on : 25/11/2017, 13:42:12
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

        <title>Produtos</title>
    </head>
    <body>
        <div style="padding: 20px 40px">
            
            <div class="row">
                <div class="col-sm-3" style="text-align: left">
                    <a href="index.html" class="btn btn-sm btn-outline-primary" style="border-radius: 0">
                        <span class="fa fa-arrow-left"></span>
                    </a>
                </div>
                <div class="col-sm-6" style="text-align: center">
                     <h1>Gerenciar Produtos</h1>
                </div>
                <div class="col-sm-3" style="text-align: right">
                    <a href="produto-form.jsp" class="btn btn-success" style="border-radius: 0">
                        Novo Produto
                    </a>
                </div>
            </div>
            
            <h4 style="color:red">${erro}</h4>
            
            <br>

            <table class="table">
                <thead style="font-weight: bold; background-color: black; color: white;">
                    <tr>
                        <th>#</th>
                        <td>Descrição</td>
                        <td>Preço</td>
                        <td></td>
                        <td></td>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="produto" items="${produtos}" varStatus="inf">
                       <tr>
                           <th>${inf.count}</th>
                           <td>${produto.descricao}</td>
                           <td>${produto.preco}</td>
                           <td>
                               <a href="ProdutoServlet?action=editar&id=${produto.id}">
                                   Editar
                               </a>
                           </td>
                           <td>
                               <a href="ProdutoServlet?action=remover&id=${produto.id}" 
                                  onclick="return confirm('Deseja remover esse produto?');">
                                   Excluir
                               </a>
                           </td>
                       </tr>
                    </c:forEach>
                       
                </tbody>
            </table>
        
        </div>
    </body>
</html>
