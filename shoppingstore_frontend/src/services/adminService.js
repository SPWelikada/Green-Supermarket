let ADMIN_BASE_URL = "http://localhost:8080/supermarket_war_exploded/admin/auth"


//admin login
$('#loginForm').submit(function (e) {
    e.preventDefault();
    localStorage.setItem('username',null);
    let username = $('#loginUsername').val();
    let password = $('#loginPassword').val();
    $.ajax({
        url: `${ADMIN_BASE_URL}?section=${"login"}&username=${username}&password=${password}`,
        method: "POST",
        success: function (res) {
          if (res.code == 200) {
            alert("Successfully Added!");
            localStorage.setItem('username',res.data.username);
            window.location.href = '/src/admin/adminPannel.html';
          }else if(res.code >=400){
            alert(" no user found !");
          }
        },
        error: function (ob) {
          alert(ob.responseJSON.message);
        },
      });
});









