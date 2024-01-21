
let ADMIN_PRODUCT_MANAGE_BASE_URL = "http://localhost:8080/supermarket_war_exploded/products"


//admin panel load before check 
// window.addEventListener('DOMContentLoaded', function() {
//     let user =  localStorage.getItem('username')
//     if(user != null ){
//             window.location.href = '/src/admin/adminPannel.html';
//     }else{
//             window.location.href = '/src/admin/index.html';
//     }
//  }, true);
 
  

//load all products
function loadAll(){
    $.ajax({
        url: ADMIN_PRODUCT_MANAGE_BASE_URL,
        type: "GET",
        dataType: "json",
        success: function (res) {
            let items = res.data
            $("#productTable tbody").empty();
            $.each(items, function (index, product) {
                var imageElement = $("<img>").attr("src", product.imagePath).attr("alt", "").css({
                    "width": "50px",
                    "height": "50px"
                });
                $("#productTable tbody").append(
                    "<tr>" +
                    "<td>" + imageElement.prop('outerHTML') + "</td>" +
                    "<td>" + product.id + "</td>" +
                    "<td>" + product.name + "</td>" +
                    "<td>" + product.listPrice + "</td>" +
                    "<td>" + product.unitPrice + "</td>" +
                    "<td>" + product.qty + "</td>" +
                    "</tr>"
                );
            });
        },
        error: function () {
            console.error("Failed to load products.");
        }
      });
}
loadAll()



function loadProductData(id, name, listPrice, unitPrice, qty) {
    $("#productId").val(id);
    $("#name").val(name);
    $("#listPrice").val(listPrice);
    $("#unitPrice").val(unitPrice);
    $("#qty").val(qty);
}

$("#productTable tbody").on("click", "tr", function () {
    var id = $(this).find("td:eq(1)").text();
    var name = $(this).find("td:eq(2)").text();
    var listPrice = $(this).find("td:eq(3)").text();
    var unitPrice = $(this).find("td:eq(4)").text();
    var qty = $(this).find("td:eq(5)").text();

    loadProductData(id, name, listPrice, unitPrice, qty);
});

  //add products
$('#addProductBtn').on('click', function (e) {
      e.preventDefault()
    
      $('#loader_container').css('display', 'block');
      let id = $("#productId").val();
      let name = $("#name").val();
      let listPrice =  $("#listPrice").val();
      let unitPrice =  $("#unitPrice").val();
      let qty = $("#qty").val()


      var imgFileInput = $('#imageFile')[0];
      var imageFile = imgFileInput.files[0];
      const formData = new FormData();
      formData.append("imageFile", imageFile);
   
    
    $.ajax({
        url: `${ADMIN_PRODUCT_MANAGE_BASE_URL}?action=${'add'}&name=${name}&listPrice=${listPrice}&unitPrice=${unitPrice}&qty=${qty}`,
        type: "POST",
        processData: false,
        contentType:false,
        data: formData,
        success: function (res) {
            console.log(res.code + " dsd ");
            
            if(res.code == 200){
                $('#loader_container').css('display', 'none');
                alert(" new item add success !")   
                loadAll()
            }else if(res.code >= 400){
                console.log(res.data);
                $('#loader_container').css('display', 'none');
                alert("Error adding new item !")   
            }
        },
        error: function () {
            alert("Error adding new item")   
            $('#loader_container').css('display', 'none');
        }
    });


  });



  //update products
  $('#updateProductBtn').on('click', function (e) {
    e.preventDefault()
    $('#loader_container').css('display', 'block');
    var imgFileInput = $('#imageFile')[0];
    var imageFile = imgFileInput.files[0];
    const formData = new FormData();
    formData.append("imageFile", imageFile);

        var updatedData = {
            id: $("#productId").val(),
            name: $("#name").val(),
            listPrice: $("#listPrice").val(),
            unitPrice: $("#unitPrice").val(),
            qty: $("#qty").val()
        };
        

        $.ajax({
            url: `${ADMIN_PRODUCT_MANAGE_BASE_URL}?action=${'update'}&name=${updatedData.name}&listPrice=${updatedData.listPrice}&unitPrice=${updatedData.unitPrice}&qty=${updatedData.qty}&id=${updatedData.id}`,
            type: "PUT", // Change the method to PUT
            processData: false,
            contentType:false,
            data: formData,
            success: function (res) {

                if(res.code == 200){
                    alert("Updated success !")
                    $('#loader_container').css('display', 'none');
                    loadAll()
                }else{
                    alert("Failed to update product.")
                    $('#loader_container').css('display', 'none');
                }
              
            },
            error: function () {
                console.error("Failed to update product.");
                $('#loader_container').css('display', 'none');
            }

        });

  });

  //




  //delete products
  $('#deleteProductBtn').on('click', function () {
    
    var productId = $("#productId").val();

    $.ajax({
        url: `${ADMIN_PRODUCT_MANAGE_BASE_URL}?action=${'delete'}&id=${productId}`,
        type: "POST",
        success: function (res) {
            console.log(res);
            if(res.code == 200){
                alert("Delete Success !")
                loadAll();
            }else{
                alert("Cant delete item")
            }
          
        },
        error: function () {
            console.error("Failed to delete feedback.");
        }
    });

  });










$('#manageProducts').on('click',function (e) {

    e.preventDefault()
    $('#helpDiskView').css('display', 'none');
    $('#manageProductView').css('display', 'block');
    $('#manageFeedBackView').css('display', 'none');
    $('#manageProfilesView').css('display', 'none');

})


$('#helpdesk').on('click',function (e) {
    e.preventDefault()
    $('#helpDiskView').css('display', 'block');
    $('#manageProductView').css('display', 'none');
    $('#manageFeedBackView').css('display', 'none');
    $('#manageProfilesView').css('display', 'none');
    $('#manageOrderView').css('display', 'none');
})


$('#manageFeedBacks').on('click',function (e) {
    e.preventDefault()
    $('#helpDiskView').css('display', 'none');
    $('#manageProductView').css('display', 'none');
    $('#manageFeedBackView').css('display', 'block');
    $('#manageProfilesView').css('display', 'none');
    $('#manageOrderView').css('display', 'none');

})

$('#manageProfiles').on('click',function (e) {
    e.preventDefault()
    $('#helpDiskView').css('display', 'none');
    $('#manageProductView').css('display', 'none');
    $('#manageFeedBackView').css('display', 'none');
    $('#manageProfilesView').css('display', 'block');
    $('#manageOrderView').css('display', 'none');
})


$('#manageOrders').on('click',function (e) {
    e.preventDefault()
    $('#helpDiskView').css('display', 'none');
    $('#manageProductView').css('display', 'none');
    $('#manageFeedBackView').css('display', 'none');
    $('#manageProfilesView').css('display', 'none');
    $('#manageOrderView').css('display', 'block');
})


