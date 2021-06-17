
function createSendMessage(message)
{
    let messageElement = document.createElement('div');
    messageElement.classList.add('message', 'flex');
    messageElement.innerHTML = `<div class="message__detail sender">
                                    <div class="msg_right">
                                        <div class="max-width-msg">
                                            <p class="wrap_msg me right single">
                                                <span class="msg"></span>
                                            </p>
                                        </div>
                                        <div class="send_seen__status">
                                            <!-- <img src="./assets/img/avatar.jpg" alt=""> -->
                                            <i class="far fa-check-circle"></i>
                                        </div>
                                    </div>
                                </div>`
    messageElement.querySelector('.msg').innerText = message;
    return messageElement;
}


function createReceiveMessage(){

}

function createSelectedImage(src)
{
    return `<div class="image__selected">
                <div class="image__selected--remove" onclick="removeSelectedImage(this)">
                    <i class="fal fa-times"></i>
                </div>
                <div class="image__selected--value">
                    <img src="${src}" alt="">
                </div>
            </div>`
}