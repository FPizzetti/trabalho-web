<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Pedidos</h1>
        
        <table>
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Data</td>
                    <td>Cliente</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="pedido" items="${pedidos}" varStatus="inf">
                <tr>
                    <td>${pedido.id}</td>
                    <td>${pedido.data}</td>
                    <td>${pedido.cliente.cpf}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
