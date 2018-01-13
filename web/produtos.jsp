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
        <title>Produtos</title>
    </head>
    <body>
        <h1>Produtos</h1>

        <a href="produto-form.jsp">Novo Produto</a>

        <h4 style="color:red">${erro}</h4>

        <table>
            <thead>
                <tr>
                    <td>#</td>
                    <td>Descrição</td>
                    <td>Preço</td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="produto" items="${produtos}" varStatus="inf">
                    <tr>
                        <td>${inf.count}</td>
                        <td>${produto.descricao}</td>
                        <td>${produto.preco}</td>
                        <td><a href="ProdutoServlet?action=remover&id=${produto.id}" onclick="return confirm('Deseja remover esse produto?');">Remover</a>
                            <a href="ProdutoServlet?action=editar&id=${produto.id}">Editar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
