$("#add").click(function() {
    
    let item = $("#produto option:selected");
    let qtd = parseInt($("#quantidade").val());
    
    let total = parseFloat(item.data('preco')) * qtd;
    $("#total").html(parseFloat(parseFloat($("#total").html()) + total).toFixed(2));
    
    $("#itens_pedido").append(
                '<tr data-item="'+ item.val() +'">' + 
                '<td>' + item.data('nome') + '</td>' +
                '<td>' + qtd + '</td>' +
                '<td>' + parseFloat(item.data('preco')).toFixed(2) + '</td>' +
                '<td data-total>' + parseFloat(total).toFixed(2) + '</td>' +
                '<td><button class="remove">X</button></td>' +
                '</tr>'
            );
    
})

$(document).on('click', '.remove', function() {
   
   let valor = parseFloat($(this).parent().parent().children("td[data-total]").html());
   $("#total").html(parseFloat($("#total").html()) - valor);  
   
   $(this).parent().parent().remove();
    
})

$("#send").click(function() {
    
    var pedido = {
        cliente: parseInt($("#cliente").val()),
        total: parseFloat($("#total").html()).toFixed(2),
        itens: []
    };
   
    $("#itens_pedido tr").each(function() {
       
        let item = {}
        item.id = $(this).attr('data-item');
        item.nome = $(this).children().eq(0).html();
        item.quantidade = $(this).children().eq(1).html();
        item.preco = $(this).children().eq(2).html();
        item.total = $(this).children().eq(3).html();
        pedido.itens.push(item) 
    })
    
    console.log(pedido)
    
    $.ajax({
        url: 'PedidoServlet?acao=gravar',
        type: 'POST',
        data: {items: JSON.stringify(pedido)}
    })
    .always(function() {
        window.location.replace("PedidoServlet?acao=novo&gravado=1");
    });
    
});