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
        
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        
        <title>Produto</title>
    </head>
    <body>
        <div style="padding: 20px 40px">
            <div class="row">
                <div class="col-sm-3" style="text-align: left">
                    <a href="ProdutoServlet" class="btn btn-sm btn-outline-primary" style="border-radius: 0">
                        <span class="fa fa-arrow-left"></span>
                    </a>
                </div>
                <div class="col-sm-6" style="text-align: center">
                    <h1>
                        <c:if test="${produto==null}">Novo Produto</c:if>
                        <c:if test="${produto.id > 0}">Editar Produto</c:if>
                    </h1>
                </div>
                <div class="col-sm-3" style="text-align: right">
                </div>
            </div>

            <br>
                    
            <form method="POST" action="ProdutoServlet?action=salvar">
                
                <div class="form-group row">
                    <label class="col-2 col-form-label">Descrição</label>
                    <div class="col-10">
                        <input class="form-control" required name="descricao" value="${produto.descricao}" />
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-2 col-form-label">Preço</label>
                    <div class="col-10">
                        <input class="form-control" required type="number" name="preco" value="${produto.preco}" />
                    </div>
                </div>
                
                <input name="id" type="hidden" value="${produto.id}" />
                
                <br>
                
                <button class="btn btn-success" style="border-radius: 0" type="submit">Salvar</button>
            </form>
            
        </div>
    </body>
</html>
