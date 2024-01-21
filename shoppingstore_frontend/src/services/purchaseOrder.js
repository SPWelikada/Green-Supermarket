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

        $("#cartViewFinal").append(listItem);
        let getTot =  localStorage.getItem('total')
        $("#total").text( "$ "+getTot);
    });

}

addCart()


