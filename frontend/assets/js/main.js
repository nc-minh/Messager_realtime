function modalSwitch(){
    // Get the modal
    let modal = document.getElementById('modal');
    let modal__overlay = document.getElementById('modal__overlay');
    let register = document.querySelector(".register--sw");
    let login = document.querySelector(".login--sw");
    let btnLogin = document.querySelectorAll(".login");
    let btnRegister = document.querySelectorAll('.register');
    let btnBack = document.querySelectorAll('.back');
    let register_form = document.getElementById('register_form');
    let login_form = document.getElementById('login_form');
    let forgot = document.getElementById('forgot');

    register.addEventListener("click", ()=>{
        modal.style.display = "flex";
        login_form.style.display = "none";
        register_form.style.display = "block";
        forget_form.style.display = "none";
    });

    login.addEventListener("click", ()=>{
        modal.style.display = "flex";
        register_form.style.display = "none";
        login_form.style.display = "block";
        forget_form.style.display = "none";
    });

    forgot.addEventListener("click", ()=>{
        modal.style.display = "flex";
        register_form.style.display = "none";
        login_form.style.display = "none";
        forget_form.style.display = "block";
    });

    window.onclick = function(event) {
        if (event.target == modal__overlay) {
        modal.style.display = "none";
        }
    }

    btnLogin.forEach(element => {
        element.addEventListener("click", ()=>{
            modal.style.display = "flex";
            login_form.style.display = "block";
            register_form.style.display = "none";
            forget_form.style.display = "none";
        });
    });

    btnRegister.forEach(element => {
        element.addEventListener("click", ()=>{
            modal.style.display = "flex";
            login_form.style.display = "none";
            register_form.style.display = "block";
            forget_form.style.display = "none";
        });
    });

    btnBack.forEach(element => {
        element.addEventListener("click", ()=>{
            modal.style.display = "none";
        });
    });

}
modalSwitch();

function showHidePass(){
    let x = document.querySelectorAll(".showHidePass");
    let wsShowHide = document.querySelectorAll(".show-hide-password");
    let changeColorIcon = document.querySelectorAll(".show-hide-password-icon");

    for (let i = 0; i < x.length; i++){
        wsShowHide[i].addEventListener("click", ()=>{
            if(x[i].type == "password"){
                x[i].type = "text";
                changeColorIcon[i].style.color = "#FFD700";
            }else{
                x[i].type = "password";
                changeColorIcon[i].style.color = "var(--black-color)";
            }
        })
    }
}
showHidePass();


// 
const login_form = document.getElementById('login-form');
const form_message_test = document.getElementById('form-message_test');
login_form.addEventListener('submit', (e)=>{
    e.preventDefault();
    const password_login = document.getElementById('password-login').value;
    if(password_login === 'adminMinh'){
        form_message_test.innerHTML = '';
        const app__container = document.querySelector('.app__container');
        const modal = document.getElementById('modal');
        const app__header = document.querySelector('.app__header');
        const app__body = document.querySelector('.app__body');
        app__header.style.display = 'flex';
        app__body.style.display = 'block';
        app__container.style.display = 'none';
        modal.style.display = 'none';
        renderChat();
    }else{
        let msg = 'bạn không có quyền được vào web đang fix';
        form_message_test.innerHTML = msg;
        form_message_test.style.color = 'red';
    }
  })