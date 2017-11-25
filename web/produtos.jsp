<%-- 
    Document   : produtos
    Created on : 25/11/2017, 13:42:12
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produtos</title>
    </head>
    <body>
        <h1>Produtos</h1>

        <a href="produto-form.jsp">Novo Produto</a>

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
                <tr>
                    <td>1</td>
                    <td>Test</td>
                    <td>2,50</td>
                    <td><a href="">Remover</a></td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
