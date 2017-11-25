<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalhes do Pedidos</title>
    </head>
    <body>
        
        <h1>Detalhes do Pedido</h1>
        
        <table>
            <thead>
                <tr>
                    <td>Produto</td>
                    <td>Quantidade</td>
                    <td>Valor Unitário</td>
                    <td>Total</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${itens}" varStatus="inf">
                <tr>
                    <td>${item.produto.descricao}</td>
                    <td>${item.quantidade}</td>
                    <td>${item.produto.preco}</td>
                    <td>${item.quantidade * item.produto.preco}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        
        Total do pedido: R$ ${total}
    </body>
</html>
