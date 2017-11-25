<%-- 
    Document   : cliente-form
    Created on : 25/11/2017, 14:01:20
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Clientes</title>
    </head>
    <body>
        <h1>Cadastro de Clientes</h1>
        <br>
        <form method="POST" action="/ClienteServlet?action=salvar">
            <input name="id" type="hidden" value="" /><br/>
            <label>CPF</label><br/> 
            <input type="text" name="CPF" value="" /><br/>
            <label>Nome</label><br/>
            <input type="text" name="nome" value="" /><br/>
            <label>Sobrenome</label><br/>
            <input type="text" name="sobrenome" value="" /><br/>
            <br/>
            <button type="submit">Salvar</button>
        </form>
    </body>
</html>
