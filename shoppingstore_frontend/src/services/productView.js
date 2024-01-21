let PRODUCTS_BASE_URL = "http://localhost:8080/supermarket_war_exploded/products"


window.onload = function() {

    let urlParams = new URLSearchParams(window.location.search);
    let  id = urlParams.get('id');

    $.ajax({
        url: `${PRODUCTS_BASE_URL}?action=${"get_by_id"}&id=${id}`,
        method: "GET",
        contentType: "application/json",
        success: function (res) {
            if (res.code == 200) { 

                $('.product-image').attr('src', '');
                $('.product-name').html('');
                $('.product-price').html('')

                  $('.product-name').text(res.data.name);
               //   $('.product-description p').text(productDetails.description);
                  $('.product-price').text("$"+res.data.listPrice);
                  $('.product-image').attr('src', res.data.imagePath);
                  
            }else if(res.code >=400){
               alert(" no user found !");
            }
        },
        error: function (ob) {
            alert(ob.responseJSON.message);
        },
    });
    
};


//add-to-cart-button
$('.btnAddCartView').on('click', function () {
    let urlParams = new URLSearchParams(window.location.search);
    let  id = urlParams.get('id');

    if (typeof cart === 'undefined') {
        window.cart = [];
    }
    let producetName =  $('.product-name').text();    
    let productDescription =  $('.product-description').text();    
    let price =  $('.product-price').text();    
    let priceWithoutDollarSign = price.replace('$', '');
    let imageUrl = $('.product-image').attr('src');
    var quntity = $('#quantity').val();

    var clickedProduct = {
            id: id,
            imagePath: imageUrl,
            listPrice: priceWithoutDollarSign,
            name: producetName,
            quantity:quntity
    };
      
    let existingCart = JSON.parse(localStorage.getItem('cart')) || [];

    let idExists = existingCart.some(item => item.id == id);

    if (idExists) {
        let existingItem = existingCart.find(item => item.id == id);
        if (existingItem) {

            let qty   = parseInt(existingItem.quantity, 10) + parseInt(quntity, 10);
            existingItem.quantity = qty.toString();
            existingItem.total    = qty * existingItem.listPrice;
            console.log(`ID ${qty}`);

            let cartTotal = existingCart.reduce((accumulator, item) => {
                return  accumulator + item.listPrice * item.quantity;
            }, 0);

            let itemQty = existingCart.reduce((accumulator, item) => {
                return accumulator + parseInt(item.quantity);
            }, 0);

            console.log(cartTotal + " Tot ");
            console.log(itemQty + " CCC ");
    
            localStorage.setItem('total',cartTotal);
            localStorage.setItem('item_qty',itemQty);

            addCart();
            totals();

        }
        
      } else {
        console.log(`ID ${id} does not exist in the cart.`);
        existingCart.push({ id: id,imageUrl:imageUrl, quantity: quntity , listPrice: priceWithoutDollarSign , name: producetName,total:quntity*priceWithoutDollarSign});
      
        let cartTotal = existingCart.reduce((accumulator, item) => {
            return accumulator + item.listPrice * item.quantity;
        }, 0);

        let itemQty = existingCart.reduce((accumulator, item) => {
            return accumulator + parseInt(item.quantity);
        }, 0);

        localStorage.setItem('total',cartTotal);
        localStorage.setItem('item_qty',itemQty);
        console.log(itemQty + " bbbb ");
    }
  
      localStorage.setItem('cart', JSON.stringify(existingCart));
    
      addCart();
      totals();


});

//buy-now-button



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
                    <label>${product.quantity}</label>
                </div>
                <label>$${product.listPrice}</label>
                </div>
            </div>
            `);

        $("#productList").append(listItem);
  });

}


function totals(){
    let getTot =  localStorage.getItem('total')
    let getTotItems = localStorage.getItem('item_qty');

    console.log(getTot , " tot ");
    console.log(getTotItems, " items tot ");

    $("#totalCartItems").empty();
    $("#totalCartPrice").empty();

    $("#totalCartItems").text(getTotItems);
    $("#totalCartPrice").text("$" + getTot);
}