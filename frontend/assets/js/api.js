
// https://apidevchat.herokuapp.com/api/v1/account/signup

// var api = 'https://apidevchat.herokuapp.com/api/v1/account/signup';

// var raw = JSON.stringify({
//   "birthday": "27/10/2001",
//   "password": "Password is mandatory",
//   "firstname": "Minh",
//   "gender": "male",
//   "email": "Email@gmail.com",
//   "lastname": "Lastname "
// });

// var requestOptions = {
//   method: 'POST',
//   headers: {
//     "content-type": "application/json; charset=UTF-8"
// },
//   body: raw,
//   redirect: 'follow'
// };

// fetch(api, requestOptions)
// .then(function (response) {
//     if(response.status == 500){
//         console.log('trùng id');
//     }
//     return response.json();
// })
// .then(function (data) {
//     return console.log(data);
// })
// .catch(function(error) {
//     console.log("đã xảy ra lỗi!")
//     console.log(error);
// });



let formData = new FormData();
formData.append('name', fileName);
formData.append('data', file);
var myHeaders = new Headers();
myHeaders.append("", "");
myHeaders.append("", "");
myHeaders.append("Content-Type", "application/json");

var raw = JSON.stringify({});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("https://apidevchat.herokuapp.com/api/v1/account/signup", requestOptions)
  .then(response => response.text())
  .then(result => console.log(result))
  .catch(error => console.log('error', error));