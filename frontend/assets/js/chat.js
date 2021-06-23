// event show hide options info chatuser

let btn_option__info = document.getElementById('option__info');
var frame_container__right__messages = document.getElementById('container__right__messages');
let frame_right__option__mobile = document.getElementById('right__option__mobile');
let btn_right__option__mobile__back = document.getElementById('right__option__mobile--back');
let frame_container__right__option = document.getElementById('container__right__option');
btn_option__info.addEventListener('click',showHideOptions);
btn_right__option__mobile__back.addEventListener('click',showHideOptions);
function showHideOptions(){
    if(frame_container__right__option.style.display == 'none'){
        frame_container__right__option.style.display = 'block';
        if (window.innerWidth < 1200){
            frame_container__right__messages.style.display = 'none';
            frame_container__right__option.style.width = '100%';
        }else{
            frame_container__right__option.style.width = '35%'; 
            frame_container__right__messages.style.display = 'block';
        }
    }
    else
    {
        frame_container__right__option.style.display = 'none';
        if (window.innerWidth < 1200){
        frame_container__right__messages.style.display = 'block'
        }
    }
}
showHideOptions();


function clickShowMessage(){
    let Message = document.querySelectorAll('.left__list-messager-item');
    for(let i = 0; i < Message.length; i++){
        Message[i].onclick = ()=>{
            for(let i = 0; i < Message.length; i++){
                Message[i].classList.remove('clicked');
                frame_container__right__messages.style.display = 'block'
            }
            Message[i].classList.toggle('clicked');
        }
        
    }
}

clickShowMessage();

const listChat = document.getElementById('container__left__list-messager');

listChat.onmouseover = function(){
    this.style.overflowY = 'scroll';
}
listChat.onmouseout = function(){
    this.style.overflowY = 'hidden';
}

