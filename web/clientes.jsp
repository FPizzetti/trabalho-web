<%-- 
    Document   : clientes
    Created on : 25/11/2017, 14:01:10
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
    </head>
    <body>
        <h1>Clientes</h1>

        <a href="cliente-form.jsp">Novo Cliente</a>

        <h4 style="color:red">${erro}</h4>

        <table>
            <thead>
                <tr>
                    <td>#</td>
                    <td>CPF</td>
                    <td>Nome</td>
                    <td>Sobrenome</td>
                    <td></td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cliente" items="${clientes}" varStatus="inf">
                    <tr>
                        <td>${inf.count}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.nome}</td>
                        <td>${cliente.sobrenome}</td>
                        <td><a href="#" onclick="return confirm('Deseja remover esse cliente?');">Remover</a>
                            <a href="#">Editar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
