<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Pedido</title>
    </head>
    <body>
        
        <c:if test="${not empty gravado}">
            <h2>PEDIDO INSERIDO COM SUCESSO!</h2>
            <hr>
        </c:if>
        
        <h1>Cadastrar Pedido</h1>
        
        <p>Selecione o cliente<br>
        <select name="cliente" id="cliente">
            <c:forEach var="cliente" items="${clientes}" varStatus="inf">
                <option value="${cliente.id}">${cliente.nome}</option>
            </c:forEach>
        </select>
        </p>
        
        <table border="1">
            <thead>
                <tr>
                    <td>Produto</td>
                    <td>Quantidade</td>
                    <td>Valor Unitário</td>
                    <td>Total</td>
                    <td>&nbsp</td>
                </tr>
            </thead>
            <tbody id="itens_pedido">
            </tbody>
        </table>
        
        Total do pedido: R$ <span id="total">0.00</span>
        <hr>
        
        <p>Adicionar produto<br>
        <fmt:setLocale value = "pt_BR"/>
        <select id="produto">
            <c:forEach var="produto" items="${produtos}" varStatus="inf">
                <option value="${produto.id}" data-nome="${produto.descricao}" data-preco="${produto.preco}">
                    ${produto.descricao} - <fmt:formatNumber type="currency" value="${produto.preco}" />
                </option>
            </c:forEach>
        </select>
        - Quantidade: <input type="number" id="quantidade" min="1" value="1"><br>
        <button type="button" id="add">Adicionar</button>
        </p>     
        
        <button type="button" id="send">Fechar pedido</button>
    </body>

    <script src="pedidos/jquery.min.js"></script>
    <script src="pedidos/novo.js"></script>

</html>
