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
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    
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
                        <td><a href="ClienteServlet?acao=remover&cpf=${cliente.cpf}" onclick="return confirm('Deseja remover esse cliente?');">Remover</a>
                            <a href="ClienteServlet?acao=editar&id=${cliente.id}">Editar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
