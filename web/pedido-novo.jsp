<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastrar Pedido</title>
                
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>

        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

    </head>
    <body style="padding: 20px 40px">
        
        <div class="row">
            <div class="col-sm-3" style="text-align: left">
                <a href="PedidoServlet" class="btn btn-sm btn-outline-primary" style="border-radius: 0">
                    <span class="fa fa-arrow-left"></span>
                </a>
            </div>
            <div class="col-sm-6" style="text-align: center">
                <h1>
                    <c:if test="${cliente == null}">Novo Pedido</c:if>
                    <c:if test="${cliente.id > 0}">Editar Pedido</c:if>
                </h1>
            </div>
            <div class="col-sm-3" style="text-align: right">
            </div>
        </div>
        
        <br>
                
        <c:if test="${not empty gravado}">
            <h2>PEDIDO INSERIDO COM SUCESSO!</h2>
            <hr>
        </c:if>
       
        <div class="form-group row">
            <label class="col-2 col-form-label">
                Selecione o cliente:
            </label>
            <div class="col-10">
                <select class="form-control" style="border-radius: 0" name="cliente" id="cliente">
                    <c:forEach var="cliente" items="${clientes}" varStatus="inf">
                        <option value="${cliente.id}">${cliente.nome}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        
            
        <br>
        
        <div class="form-group row">
            
            <label class="col-2 col-form-label">
                Produto:
            </label>
            <div class="col-4">
                <fmt:setLocale value = "pt_BR"/>
            
                <select class="form-control" id="produto" style="border-radius: 0">
                    <c:forEach var="produto" items="${produtos}" varStatus="inf">
                        <option value="${produto.id}" data-nome="${produto.descricao}" data-preco="${produto.preco}">
                            ${produto.descricao} - <fmt:formatNumber type="currency" value="${produto.preco}" />
                        </option>
                    </c:forEach>
                </select>
            
            </div>
                
            <label class="col-2 col-form-label">
                Quantidade:
            </label>
            <div class="col-2">
                <input class="form-control" style="border-radius: 0" type="number" id="quantidade" min="1" value="1">
            </div>
                
            <div class="col-2">
                <button class="btn btn-primary" style="border-radius: 0; float: right" type="button" id="add"><span class="fa fa-plus"></span> Adicionar</button>
            </div>
        </div>

        <br>    
        <hr>
        
        <table border="1" class="table">
            <thead style="font-weight: bold; background-color: black; color: white;">
                <tr style="font-weight: bold">
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
        
        <strong>Total do pedido:</strong> R$ <span id="total">0.00</span>
        <hr>
        
           
        
        <br>
        <button class="btn btn-success" style="border-radius: 0" type="button" id="send">Fechar pedido</button>
    </body>

    <script src="pedidos/jquery.min.js"></script>
    <script src="pedidos/novo.js"></script>

</html>
