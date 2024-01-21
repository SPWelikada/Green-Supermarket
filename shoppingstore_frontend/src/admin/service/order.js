let ADMIN_ORDER_MANAGE_BASE_URL = "http://localhost:8080/supermarket_war_exploded/order"

// orders
function loadAllOrders(){
    $.ajax({
        url: `${ADMIN_ORDER_MANAGE_BASE_URL}?action=list`,
        method: "GET",
        success: function (res) {
            let items = res.data
        
            $("#orderTable tbody").empty();
            $.each(items, function (index, product) {
                console.log(product.status);
                $("#orderTable tbody").append(
                    "<tr>" +
                    "<td>" + product.id + "</td>" +
                    "<td>" + product.customerId + "</td>" +
                    "<td>" + convertMillisToDateString(product.orderDate)+ "</td>" +
                    "<td>" + product.status + "</td>" +
                    "</tr>"
                );
                
            });
        },
        error: function () {
            console.error("Failed to load products.");
        }
      });
}

loadAllOrders()


$('#loadOrdersBtn').on('click', function (e) {
    e.preventDefault();
    console.log("dsdsd");
    loadAllOrders();
});


$("#orderTable tbody").on("click", "tr", function () {
    var id = $(this).find("td:eq(0)").text();
    var customerId = $(this).find("td:eq(1)").text();
    var orderDate = $(this).find("td:eq(2)").text();
    var status = $(this).find("td:eq(3)").text();
    
   console.log(id , " " , customerId , " " , orderDate , " ",status);

   const date =  convertMillisToDateString(orderDate);

    loadTableDataToFields(id, customerId, date, status);
});

function loadTableDataToFields(id, customerId, orderDate, status){
    $("#OrderId").val(id);
    $("#orderCustomerId").val(customerId);
    $("#orderDate").val(orderDate);
    $("#orderStatus").text(status.toLowerCase());
};

function convertMillisToDateString(millis) {
    var date = new Date(millis);
    var year = date.getFullYear();
    var month = ('0' + (date.getMonth() + 1)).slice(-2); 
    var day = ('0' + date.getDate()).slice(-2);
    return year + '-' + month + '-' + day;
    
}



$('#updateOrderBtn').on('click', function (e) {
    e.preventDefault();
    $('#loader_container').css('display', 'block');
    let orderId =  $("#OrderId").val();
    let customerId =  $("#orderCustomerId").val();
    let orderDate =  $("#orderDate").val();
    let status = $("#status").val();
    console.log(status , " " , customerId , " ", customerId , " " , orderDate);
    $.ajax({
        url: `${ADMIN_ORDER_MANAGE_BASE_URL}?action=deliver&status=${status.toLowerCase()}&customer_id=${customerId}&orderId=${orderId}&order_date=${orderDate}`,
        method: "POST",
        success:  function (res) {
            if(res.code == 200){
               $('#loader_container').css('display', 'none');
               alert(`${status.toLowerCase()} order Success`);
               console.log(res.data);
               loadAllOrders();
            }else{
                alert("error delever ");
                $('#loader_container').css('display', 'none');
            }
            
            },
        error: function () {
            console.error("Failed to load products.");
            $('#loader_container').css('display', 'none');
        }
      });

});
