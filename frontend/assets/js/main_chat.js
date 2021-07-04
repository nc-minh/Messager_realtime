// event show hide options info chatuser



var frame_right__option__mobile = document.getElementById('right__option__mobile');

const btn_option__info = document.getElementById('option__info');
const frame_container__right__messages = document.getElementById('container__right__messages');
const frame_container__right__option = document.getElementById('container__right__option');
const container__left = document.querySelector('.container__left');

btn_option__info.addEventListener('click',()=>{
    if(window.innerWidth > 1200){
        frame_container__right__option.style.display = 'block';
        container__left.style.display = 'block';
    }else{
        frame_container__right__option.style.display = 'block';
        frame_container__right__messages.style.display = 'none';
        frame_container__right__option.style.flex = '1';
    }
});
const btn_right__option__mobile__back = document.getElementById('right__option__mobile--back');
btn_right__option__mobile__back.addEventListener('click',()=>{
    if(window.innerWidth > 1200){
        frame_container__right__option.style.display = 'none';
        container__left.style.display = 'block';
    }else{
        frame_container__right__option.style.display = 'none';
        frame_container__right__messages.style.display = 'flex';
    }
});


function clickShowMessage(){
    let Message = document.querySelectorAll('.left__list-messager-item');
    let container__left = document.querySelector('.container__left');
    let container__right = document.querySelector('.container__right');
    for(let i = 0; i < Message.length; i++){
        Message[i].onclick = ()=>{
            for(let i = 0; i < Message.length; i++){
                Message[i].classList.remove('clicked');
                frame_container__right__messages.style.display = 'flex';
                if(window.innerWidth < 769){
                    container__left.style.display = 'none';
                    container__right.style.display = 'flex';
                }
            }
            Message[i].classList.toggle('clicked');
        }
        
    }
}

clickShowMessage();


// event select photos to send
const listImg = document.querySelector('.listImg__send');
const showSelectedImg = document.querySelector('.container__right__messages-actions__input__list-img');
console.log([listImg])
console.log(listImg.childElementCount)
document.getElementById("select__img").addEventListener("change", (e) => {
    let files = e.target.files;
    if (FileReader && files && files.length)
    {
        for (let file of files)
        {
            let fileReader = new FileReader();
            fileReader.onload = function () {
                listImg.innerHTML = createSelectedImage(fileReader.result) + listImg.innerHTML;
            }
            fileReader.readAsDataURL(file)
        }
        if(listImg.childElementCount != -1){
            showSelectedImg.style.display = 'flex';
        }
    }
    console.log(listImg.childElementCount)
})

// render img selected
function createSelectedImage(src){
    return `
    <div class="img__selected">
        <div class="img__selected__delete-img" onclick="removeSelectedImage(this)">
            <i class="icofont-close-circled"></i>
        </div>
        <div class="img__selected__item">
            <img id="urlImg" src="${src}" alt="" class="img__selected__item__img">
        </div>
    </div>
    `
}

// delete img selected
const btn_delete_imgSelected = document.querySelectorAll('.img__selected__delete-img');


function removeSelectedImage(_this){
    _this.parentNode.remove();
    console.log(listImg.children)
    console.log(listImg.childElementCount)
    if(listImg.childElementCount === 0){
        showSelectedImg.style.display = 'none';
    }
}

function removeImgWhenSend(){
    let array = Array.from(listImg.children);
    console.log(array)
    for (let i = 0; i < array.length; i++) {
        array[i].remove();
        showSelectedImg.style.display = 'none';
    }
}




// handle user paste data to contenteditable
const chat_input = document.getElementById('chat_input');
chat_input.addEventListener('paste', (e) => {
    e.preventDefault();

    // handle paste file
    console.log(e.clipboardData.files[0])
    let file = e.clipboardData.files[0]
    if (FileReader && file)
    {
        let fileReader = new FileReader();
        fileReader.onload = function () {
            listImg.innerHTML = createSelectedImage(fileReader.result) + listImg.innerHTML;
        }
        fileReader.readAsDataURL(file)
        if(listImg.childElementCount != -1){
            showSelectedImg.style.display = 'flex';
        }

        
    }

    // handle paste text - anti auto add css in browser
    chat_input.innerText += e.clipboardData.getData('Text');
    console.log(listImg.childElementCount)
    
})

// scroll  onbottom when send or receiver
function updateScroll(){
    var element = document.querySelector('.container__right__chat-content');
    element.scrollTop = element.scrollHeight;
}
updateScroll();

//handle event send text
const chat_wrap = document.getElementById('chat_wrap');

chat_input.addEventListener('keypress', (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
        e.preventDefault();
        handleEventSendImg();
        handleEventSend();
    }

})

function handleEventSend() {
    let message = chat_input.innerText.trim();
    if (!message){
        chat_input.innerText = '';
        console.error('không được chát khoảng trắng');
        return 0;
    }else{
        chat_input.innerText = '';
        chat_wrap.appendChild(createSendMessage(message))
        updateScroll();
    }
    // handle send data
}

function createSendMessage(message)
{
    let messageElement = document.createElement('div');
    messageElement.classList.add('container__right__chat-content__item');
    messageElement.innerHTML = `
    <div class="container__right__chat-content__item__detail msg-send">
        <span class="container__right__chat-content__item__detail__wrap-msg">
            <div id="msg-send"></div>
        </span>

        <div class="container__right__chat-content__item--active">
            <i class="icofont-check"></i>
        </div>
    </div>
    `;
    messageElement.querySelector('#msg-send').innerText = message;
    return messageElement;
}

function handleEventSendImg(){
    const send_img = document.getElementById('send_img');
    const arr = Array.from(send_img.children);
    const urlImg = document.querySelectorAll('#urlImg');
    
    for (let i = 0; i < arr.length; i++) {
        console.log(arr[i].className)
        if(arr[i].className == 'img__selected'){
            console.log('đang có ảnh được chọn')
            chat_wrap.appendChild(createSendImg(urlImg[i].src))
            removeImgWhenSend();
            updateScroll();
        }else{
            console.log('không có ảnh nào được chọn')
        }
    }

    
    console.log([send_img.children]);
}

function createSendImg(ulrImg)
{
    let messageElement = document.createElement('div');
    messageElement.classList.add('container__right__chat-content__item');
    messageElement.innerHTML = `
    <div class="container__right__chat-content__item__detail msg-send">
        <span class="container__right__chat-content__item__detail__wrap-msg">
        <img id="img" src="${ulrImg}">
        </span>

        <div class="container__right__chat-content__item--active">
            <i class="icofont-check"></i>
        </div>
    </div>
    `;
    messageElement.querySelector('#img').innerText = ulrImg;
    return messageElement;
}

// back list msg
function backListMsg(){
    const messages__header_info_back = document.querySelector('.messages__header-info-back');
    const container__right = document.querySelector('.container__right');
    const container__left = document.querySelector('.container__left');
    messages__header_info_back.onclick = ()=>{
        container__right.style.display = 'none';
        container__left.style.display = 'block';
    }
}
backListMsg();


function handleEventReceiver(url) {

    fetch(url)
        .then(response => response.json())
        .then((datas)=>{
            datas.map((data)=>{
                chat_wrap.appendChild(createReceiverMessage(data.message, data.timeSend, data.avatarUserSend));
            })
            
            return console.log(datas)
        })
        .catch(function(){
            console.error('error!')
        });

}
handleEventReceiver("http://localhost:3000/messenger");//api

function createReceiverMessage(message, timeSend, avatarUserSend)
{
    let messageElement = document.createElement('div');
    messageElement.classList.add('container__right__chat-content__item');
    messageElement.innerHTML = `
    <div class="container__right__chat-content__item__wrap-msg-receive">
        <div class="container__right__chat-content__item__avatar-msg-receive">
            <img src="${avatarUserSend}" alt="" class="container__right__chat-content__item__avatar__img-msg-receive">
        </div>

        <span class="container__right__chat-content__item__detail__wrap-msg-msg-receive">
            <div id="msg-receiver" class="container__right__chat-content__item__detail__wrap-msg-msg-receive__content"></div>
            <div id="timeReceiver" class="container__right__chat-content__item__detail__wrap-msg-msg-receive__time"></div>
        </span>
    </div>
    `;
    messageElement.querySelector('#msg-receiver').innerText = message;
    messageElement.querySelector('#timeReceiver').innerText = timeSend;
    return messageElement;
}