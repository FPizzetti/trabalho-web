<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
    </head>
    <body>
        
        <h1>Pedidos</h1>
        <p><a href="PedidoServlet?acao=novo">+ Novo pedido</a></p>
        
        <table>
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Data</td>
                    <td>Cliente</td>
                    <td>&nbsp;</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pedido" items="${pedidos}" varStatus="inf">
                <tr>
                    <td>${pedido.id}</td>
                    <td>${pedido.data}</td>
                    <td>${pedido.cliente.nome}</td>
                    <td><a href="PedidoServlet?acao=visualizar&id=${pedido.id}">Ver detalhes</a></td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
