function modalSwitch(){
    // Get the modal
    let modal = document.getElementById('modal');
    let modal__overlay = document.getElementById('modal__overlay');
    let register = document.querySelector(".register--sw");
    let login = document.querySelector(".login--sw");
    let btnLogin = document.querySelectorAll(".login");
    let btnRegister = document.querySelectorAll('.register');

    register.addEventListener("click", ()=>{
        modal.style.display = "flex";
        login_form.style.display = "none";
        register_form.style.display = "block";
    });

    login.addEventListener("click", ()=>{
        modal.style.display = "flex";
        login_form.style.display = "block";
        register_form.style.display = "none";
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
        });
    });

    btnRegister.forEach(element => {
        element.addEventListener("click", ()=>{
            modal.style.display = "flex";
            login_form.style.display = "none";
            register_form.style.display = "block";
        });
    });
}
modalSwitch();

