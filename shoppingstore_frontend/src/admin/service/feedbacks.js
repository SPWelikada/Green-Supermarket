const FEED_BACKS_BASE_URL = "http://localhost:8080/supermarket_war_exploded/feedback"

$("#btnFeedBackClient").on('click',function(e){
    e.preventDefault()
    $('#loader_container').css('display', 'block');
    const cust_id =  localStorage.getItem('customerId');
    if (cust_id == null){
         alert("plese register and login first")
    }
   
   const message =  $('#custMessage').val();
   const rating  =  $('#custRating').val();
    console.log(message + " "+ rating);
    $.ajax({
        url: `${FEED_BACKS_BASE_URL}?customerId=${cust_id}&rating=${rating}&message=${message}`,
        method: "POST",
        success: function (res) {
             if(res.code == 200){
                  alert("add feed back success !")
                  $('#loader_container').css('display', 'none');
             }
        },
        error: function () {
             alert("feed back add error !")
             $('#loader_container').css('display', 'none');
        }
      });
});

function getAllFeedBacks(){
    $.ajax({
        url: `${FEED_BACKS_BASE_URL}`,
        method: "GET",
        success: function (res) {
            console.log(res.data);
             if(res.code == 200){
                console.log(res.data);
                $("#feedbackTableBody").empty();
                $.each(res.data, function (index, product) {
                    
                    $("#feedbackTableBody").append(
                        "<tr>" +
                        "<td>" + product.id + "</td>" +
                        "<td>" + product.customerId + "</td>" +
                        "<td>" + product.message + "</td>" +
                        "<td>" + product.rating + "</td>" +
                        "</tr>"
                    );
                });
             }

        },
        error: function () {
             alert("feed back add error !")
        }
      });
}

getAllFeedBacks()