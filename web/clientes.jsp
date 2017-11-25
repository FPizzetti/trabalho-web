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
        <title>Lista de Clientes</title>
    </head>
    <body>
        <h1>Lista de Clientes</h1>
        <br>
        <a href="ClienteServlet?acao=novo">Novo Cliente</a>
        <br>
        <table>
            <tr>
                <td>
                    ID
                </td>
                <td>
                    Nome
                </td>
                <td>
                    Sobrenome
                </td>
                <td>
                    CPF
                </td>
            </tr>
        </table>
    </body>
</html>
