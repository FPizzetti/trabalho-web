<%-- 
    Document   : produto-form
    Created on : 25/11/2017, 13:44:52
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto</title>
    </head>
    <body>
        <form method="POST" action="ProdutoServlet?action=salvar">
            <input name="id" type="hidden" value="${produto.id}" /><br/>
            <label>Descrição</label><br/> 
            <input name="descricao" value="${produto.descricao}" /><br/>
            <label>Preço</label>  <br/>
            <input type="number" name="preco" value="${produto.preco}" /><br/>
            <br/>
            <button type="submit">Salvar</button>
        </form>
    </body>
</html>
