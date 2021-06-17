inpSendMessage.addEventListener('keypress', (e) => {
    if (e.key === 'Enter' && !e.shiftKey) {
        e.preventDefault();
        handleEventSend();
    }

})

function handleEventSend() {
    let message = inpSendMessage.innerText;
    if (!message)
        return;
    inpSendMessage.innerText = '';
    chatHistory.appendChild(createSendMessage(message))
    scrolTopToBottom();

    // handle send data
}