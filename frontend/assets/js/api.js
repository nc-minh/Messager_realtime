
// https://apidevchat.herokuapp.com/api/v1/accounts api signup

// https://apidevchat.herokuapp.com/api/v1/accounts/verification api verify


// https://apidevchat.herokuapp.com/api/v1/accounts/authenticate api signin


// https://apidevchat.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

const API_SIGNUP = 'https://apidevchat.herokuapp.com/api/v1/accounts';
const CODE_CONFIRM = 'https://apidevchat.herokuapp.com/api/v1/accounts/verification';
const API_SIGNIN = 'https://apidevchat.herokuapp.com/api/v1/accounts/authenticate';

var formRegister = document.getElementById('register-form');
var btnForm = document.getElementById('register-confirm');
var email_message = document.getElementById('email-message');
var register_form = document.getElementById('register_form');
var register_form_confirm = document.getElementById('register_form_confirm');
var login = document.querySelectorAll('.login');

formRegister.addEventListener('submit', (e)=>{

  e.preventDefault();
  var raw = JSON.stringify({
    "fullname": document.getElementById('fullname').value,
    "email": document.getElementById('email-register').value,
    "password": document.getElementById('password-register').value,
  });
  
  var requestOptions = {
    method: 'POST',
    headers: {
      "content-type": "application/json; charset=UTF-8"
  },
    body: raw,
    redirect: 'follow'
  };
  
  fetch(API_SIGNUP, requestOptions)
  .then(function (response) {
      if(response.status == 400){
          console.log('nhập thông tin sai');
      }else if(response.ok){
        console.log('đăng ký thành công');
        register_form.style.display = 'none';
        register_form_confirm.style.display = 'block';
        for(let i = 0; i < login.length; i++){
            login[i].addEventListener('click',()=>{
                register_form_confirm.style.display = 'none';
            })
          
        }
      }
      console.log(response);
      return response.json();
  })
  .then(function (data) {
    if(data.errorCode == 'devchat001'){
      console.log('chưa nhập thông tin hoặc nhập thiếu');
    }else if(data.errorCode == "devchat000"){
      console.warn('Lỗi chưa xác định!');
    }else if(data.message == undefined){
      email_message.innerHTML = '';
    }
    else{
      email_message.innerHTML = data.message;
      email_message.style.color = "red";
    }
      return console.log(data);
  })
  .catch(function(error) {
      console.log("đã xảy ra lỗi!")
      console.log(error);
  });
})



