let searchPersonButton = document.getElementById('search__person');
let logoMain = document.querySelector('.logo');
let listSelectedImages = document.querySelector('.list__images');
let sendMessageElement = document.querySelector('.send__message');
let chatHistory = document.getElementById("chat__history");
let inpSendMessage = document.getElementById("txtMessage");

// handle envent for input search

searchPersonButton.addEventListener('focus', function handlle(e) {
    showInputSearch(this);
});

searchPersonButton.addEventListener('blur', function handlle(e) {
    resetInputSearch(this)
});

const showInputSearch = (_this) => {
    let nodeWrap = _this.parentNode.parentNode;

    // hide logo
    let nodeLogo = nodeWrap.querySelector('.logo');
    if (nodeLogo)
        nodeLogo.classList.add('none');

    // hide search icon
    nodeWrap.querySelector(".search__button").classList.add('none')

    // show back icon
    nodeWrap.querySelector('.back_icon').classList.remove('none');
    
    // style input
    _this.style.paddingLeft = '15px'
}

const resetInputSearch = (_this) => {
    logoMain.classList.remove("none");
}

// edit name
window.addEventListener('resize', editFullname)

function editFullname(){
    let fullname = document.getElementById('chat_with');
    if (window.innerWidth <= 400 && fullname.innerText.length >= 10)
    {
        let words = fullname.innerText.split(" ");
        fullname.innerText = words[words.length - 1];
    }
}

editFullname();


// envent click show/hide chat infomation
let formMessage = document.querySelector(".messages");
let backMessage = document.querySelector(".back_icon_message");

document.querySelector(".chat_information").addEventListener("click", showHideOptionChat)
document.querySelector(".back_icon_message").addEventListener("click", showHideOptionChat)

function showHideOptionChat() {
    let chatOption = document.querySelector('.chat__option');
    if (!chatOption.style.display || chatOption.style.display == 'none')
    {
        chatOption.style.display = 'block';
        if (window.innerWidth < 740)
            formMessage.style.display = 'none';
    }
    else
    {
        chatOption.style.display = 'none';
        if (window.innerWidth <= 740)
            formMessage.style.display = 'block'
    }

}



// scroll bar chat history to bottom
function scrolTopToBottom()
{
    let chatHistory = document.querySelector(".chat__history");
    chatHistory.scrollTop = chatHistory.scrollHeight;
}


// event select photos to send
document.getElementById("select__img").addEventListener("change", (e) => {
    let files = e.target.files;
    if (FileReader && files && files.length)
    {
        for (let file of files)
        {
            let fileReader = new FileReader();
            fileReader.onload = function () {
                listSelectedImages.innerHTML = createSelectedImage(fileReader.result) + listSelectedImages.innerHTML;
            }
            fileReader.readAsDataURL(file)
        }
        if (listSelectedImages.style.display == 'none')
        {
            listSelectedImages.style.display = 'flex'
            showHideOptionSendMessage('none')
        }
    }
})

// event remove image selected
function removeSelectedImage(_this)
{
    _this.parentNode.remove();
    if (listSelectedImages.children.length <= 1)
    {
        listSelectedImages.style.display = 'none';
        showHideOptionSendMessage('block')
    }
}

// show or hide option left input send
function showHideOptionSendMessage(type)
{
    for (let i = 0; i < 4; i++)
    sendMessageElement.children[i].style.display = type
}

// handle user paste data to contenteditable
inpSendMessage.addEventListener('paste', (e) => {
    e.preventDefault();

    // handle paste file
    let file = e.clipboardData.files[0]
    if (FileReader && file)
    {
        let fileReader = new FileReader();
        fileReader.onload = function () {
            listSelectedImages.innerHTML = createSelectedImage(fileReader.result) + listSelectedImages.innerHTML;
        }
        fileReader.readAsDataURL(file)
        if (listSelectedImages.style.display == 'none')
        {
            listSelectedImages.style.display = 'flex'
            showHideOptionSendMessage('none')
        }

        
    }

    // handle paste text - anti auto add css in browser
    inpSendMessage.innerText += e.clipboardData.getData('Text');
    
})