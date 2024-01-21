let ORDER = "http://localhost:8080/supermarket_war_exploded/order"


window.onload = function() {
   
    addCart()
    totals()
}

     
function totals(){
    let getTot =  localStorage.getItem('total')
    let getTotItems = localStorage.getItem('item_qty');

    $("#totalCartItems").empty();
    $("#totalCartPrice").empty();

    $("#totalCartItems").text(getTotItems);
    $("#totalCartPrice").text("$" + getTot);
}

function addCart(){

    let products = JSON.parse(localStorage.getItem('cart')) || [];

    $("#productList").empty();  
    products.forEach(function(product) {
        
        var listItem = $("<li>");

        listItem.html(`
        <div style="border:2px solid rgb(228, 228, 228);height: 70px;margin: 10px;border-radius: 5px;display: flex;">
            <div style="display: flex; flex-direction: row;align-items: center;justify-content: space-between;width: 100%;margin: 5px;">
            <img src=${product.imageUrl} width="50px" style="background-size: cover;margin: 5px;"/>
            <label style="margin: 5px;font-size: 1.2rem;" id="lbl-product-name-card">${product.name}</label>
            <div>
                <label>quantity: ${product.quantity}</label>
            </div>
            <label>$${product.listPrice}</label>
            </div>
        </div>
        `);

        $("#productList").append(listItem);
  });

}

//clear order
$('#btn_clear_order').on('click', function () {

       localStorage.clear('cart');
       localStorage.clear('total');
       localStorage.clear('item_qty');
       localStorage.setItem('total',0);
       
       addCart()
       totals()
})

//place order
$('#btnPlaceOrder').on('click', function (e) {
    e.preventDefault()

    $('#loader_container').css('display', 'block');
    console.log("sdsdsdsdsdsd");
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var day = currentDate.getDate().toString().padStart(2, '0');
    
    var formattedDate = year + '-' + month + '-' + day;
    
    const customerId  =  localStorage.getItem('customerId');
    console.log(customerId);
    let email = localStorage.getItem('customerEmail');

    console.log(email);
    if(customerId === null && email == null){
        alert("customer not registered please login as a customer")
        window.location.href = '/src/login.html';
    }


    $.ajax({
        url: `${ORDER}?customer_id=${customerId}&order_date=${formattedDate}&id=1&status=${'pending'}&action=${'add'}`,
        method: "POST",
        contentType: "application/json",
        success: function (res) {
            if (res.code == 200) { 
                   localStorage.setItem("orderId",res.data.id)
                   place_OrderItem();
            }else if(res.code >=400){
               alert(" error found !");
               $('#loader_container').css('display', 'none');
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
            $('#loader_container').css('display', 'none');
        },
    });

})


function place_OrderItem(){
    let existingCart = JSON.parse(localStorage.getItem('cart')) || [];
    
    let email = localStorage.getItem('customerEmail');
    const orderId =  localStorage.getItem("orderId");
    let transformedCart = existingCart.map(item => {
       return {
            id: "",
            orderId:orderId,
            productId: parseInt(item.id),
            quantity: parseInt(item.quantity),
            price:  parseInt(item.total)
        };
    });
    $.ajax({
        url: `${ORDER}?action=${'purchase'}&email=${email}`,
        method: "POST",
        contentType: "application/json",
        data:JSON.stringify(transformedCart),
        success: function (res) {
            console.log(res.data);
            if (res.code == 200) { 
                $('#loader_container').css('display', 'none');
                alert("Place order Success !");
                let customerId = localStorage.getItem('customerId');
                localStorage.clear('cart');
                localStorage.clear('total');
                localStorage.clear('item_qty');
                localStorage.setItem('customerId',customerId)
                localStorage.setItem('total',0);
                window.location.href = '/src/index.html';
            }else if(res.code >=400){
               alert(" error found !");
               $('#loader_container').css('display', 'none');
            }else{
                alert(" error found !");
                $('#loader_container').css('display', 'none');
            }
        },
        error: function (ob) {
            $('#loader_container').css('display', 'none');
            alert(ob.responseJSON.message);
        },
    });
}


//btn_purchace_order

$('#btn_purchace_order').on('click', function (e) {
    e.preventDefault()
    let customerId = localStorage.getItem('customerId');
    window.location.href = '/src/purchaseOrder.html?customerId='+customerId;

})



$('#cartViewFinal').append()