let PRODUCTS_BASE_URL = "http://localhost:8080/supermarket_war_exploded/products"




let cart = [];


//get all products
$.ajax({
    url: PRODUCTS_BASE_URL,
    method: "GET",
    contentType: "application/json",
    success: function (res) {
        if (res.code == 200) { 
            let products = res.data;
             console.log(products);
             $('#buyForm').empty();
            products.forEach(function (product,index) {
 
                var productInfo = '<article class="productInfo">' +
                    '<div style="width: 200px"> <img  style="background-size: cover;" alt="sample" src="' + product.imagePath + '" ></div>' +
                    '<p class="price" id="price_tag">$' + product.listPrice + '</p>' +
                    '<p class="productContent"><a href="' + product.imagePath + '" title="' + product.name + '">' + product.name + '</a></p>' +
                    '<input type="submit" id="btnAddCart" name="button" value="Add to Cart" class="btnAddCartItem">' +
                    '</article>';

                 $('#buyForm').append(productInfo);

                 $('.productInfo > div').eq(index).click(function () {

                    // var clickedProduct = {
                    //     id:product.id,
                    //     imagePath: product.imagePath,
                    //     listPrice: product.listPrice,
                    //     name: product.name
                    // };
                   
                    window.location.href = '/src/productView.html?id='+ product.id;

                });

                // add cart to items
                $('.btnAddCartItem').eq(index).on('click', function () {
            
                     let existingCart = JSON.parse(localStorage.getItem('cart')) || [];
                     
                     var clickedProduct = {
                        id: existingCart.id,
                        imagePath: existingCart.imageUrl,
                        listPrice: existingCart.priceWithoutDollarSign,
                        name: existingCart.producetName,
                        quantity:1
                     };

                    let idExists = existingCart.some(item => item.id == product.id);

                    if (idExists) {
                        let existingItem = existingCart.find(item => item.id == product.id);
                        if (existingItem) {
                            alert("Item already exists please go to the view item page and increse quentity !")
                        }
                    } else {
                    
                        let tot = 1  *  parseInt(product.listPrice);
                        existingCart.push({ id: product.id,imageUrl:product.imagePath, quantity: 1 , listPrice:product.listPrice , name: product.name , total:tot});
                        
                    }
                
                    localStorage.setItem('cart', JSON.stringify(existingCart));

                    let cartTotal = existingCart.reduce((accumulator, item) => {
                        return accumulator + item.listPrice * item.quantity;
                    }, 0);

                    
                    let itemQty = existingCart.reduce((accumulator, item) => {
                        return accumulator + parseInt(item.quantity);
                    }, 0);
            
                    localStorage.setItem('total',cartTotal);
                    localStorage.setItem('item_qty',itemQty);

                    let getTot =  localStorage.getItem('total')
                    let getTotItems = localStorage.getItem('item_qty');
                     
                     console.log(getTot);
                      addCart();
                      totals()

                });

            })

        }else if(res.code >=400){
           alert(" no user found !");
        }
    },
    error: function (ob) {
        alert(ob.responseJSON.message);
    },
    });



    function addCart(){

        let products = JSON.parse(localStorage.getItem('cart')) || [];
    
        $("#productList").empty();  
        products.forEach(function(product) {
           console.log(product.imageUrl);
            var listItem = $("<li>");
    
            listItem.html(`
            <div style="border:2px solid rgb(228, 228, 228);height: 70px;margin: 10px;border-radius: 5px;display: flex;">
                <div style="display: flex; flex-direction: row;align-items: center;justify-content: space-between;width: 100%;margin: 5px;">
                <img src= ${product.imageUrl} width="50px" style="background-size: cover;margin: 5px;"/>
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
    
        $("#totalCartItems").empty();
        $("#totalCartPrice").empty();
    
        $("#totalCartItems").text(getTotItems);
        $("#totalCartPrice").text("$" + getTot);
    }



  

    $('#cart-link').click(function(e) {
        e.preventDefault();
        toggleSlider();
      });
    
    function toggleSlider() {
        var slider = $('.slider-cart-container');
        var currentState = parseInt(slider.css('right'));
        
        if (currentState === 0) {
            slider.css('right', '-400px');
        } else {
            slider.css('right', '0');
        }
    }

    $('#btn-cartContainer-close').click(function(e) {
        e.preventDefault();
        toggleSlider();
      });