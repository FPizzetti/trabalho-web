<%-- 
    Document   : cliente-form
    Created on : 25/11/2017, 14:01:20
    Author     : Matheus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente</title>
    </head>
    <body>
        <h3>
            <c:if test="${cliente == null}">Novo Cliente</c:if>
            <c:if test="${cliente.id > 0}">Editar Cliente</c:if>
        </h3>
        <form method="POST" action="ClienteServlet?acao=salvar">
            <label>CPF</label><br/> 
            <input required name="cpf" value="${cliente.cpf}" /><br/>
        
            <label>Nome</label><br/>
            <input required name="nome" value="${cliente.nome}" /><br/>
        
            <label>Sobrenome</label><br/>
            <input required name="sobrenome" value="${cliente.sobrenome}" /><br/>
            
            <input name="id" type="hidden" value="${cliente.id}" /><br/>
            <button type="submit">Salvar</button>
        </form>
    </body>
</html>
