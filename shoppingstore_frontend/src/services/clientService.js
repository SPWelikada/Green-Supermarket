let CLIENT_BASE_URL = "http://localhost:8080/supermarket_war_exploded/customer"


//login
$('#client_login').click(function (e) {
    e.preventDefault();

    let username = $('#client_login_Username').val();
    let password = $('#client_login_Password').val();
   

    $.ajax({
        url: `${CLIENT_BASE_URL}?action=${"login"}&username=${username}&password=${password}`,
        method: "POST",
        success: function (res) {
          if (res.code == 200) {
            alert("Successfully Login !");
            localStorage.setItem('customerId',res.data.id);
            localStorage.setItem('customerUserName',res.data.userName);
            localStorage.setItem('customerPassword',res.data.password);
            localStorage.setItem('customerEmail',res.data.email);

            window.location.href = '/src/index.html';
          }else if(res.code >=400){
            alert(" no user found !");
          }
        },
        error: function (ob) {
          alert(ob.responseJSON.message);
        },
      });
});





//register client
$('#register_client').click(function (e) {
    e.preventDefault();

    let name = $('#client_register_name').val();
    let contact = $('#client_register_phone').val();
    let username = $('#client_register_username').val();
    let password = $('#client_register_password').val();
    let email = $('#client_register_email').val();
     

    $.ajax({
        url: `${CLIENT_BASE_URL}?action=${"register"}&name=${name}&username=${username}&password=${password}&email=${email}&contact=${contact}`,
        method: "POST",
        success: function (res) {
          if (res.code == 200) {
            alert("Successfully register !");
           // localStorage.setItem('username',res.data.username);
            window.location.href = '/src/index.html';
          }else if(res.code >=400){
            alert(" no user found !");
          }
        },
        error: function (ob) {
          alert(ob.responseJSON.message);
        },
      });
});