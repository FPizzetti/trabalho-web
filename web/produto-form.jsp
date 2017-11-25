<%-- 
    Document   : produto-form
    Created on : 25/11/2017, 13:44:52
    Author     : felip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto</title>
    </head>
    <body>
        <h3>
            <c:if test="${produto==null}">Novo Produto</c:if>
            <c:if test="${produto.id > 0}">Editar Produto</c:if>
        </h3>
        <form method="POST" action="ProdutoServlet?action=salvar">
            <label>Descrição</label><br/> 
            <input name="descricao" value="${produto.descricao}" /><br/>
            <label>Preço</label>  <br/>
            <input type="number" name="preco" value="${produto.preco}" /><br/>
            <input name="id" type="hidden" value="${produto.id}" /><br/>
            <button type="submit">Salvar</button>
        </form>
    </body>
</html>
