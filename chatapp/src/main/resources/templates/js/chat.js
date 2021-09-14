let username = prompt("아이디를 입력하세요")
let roomNum = prompt("채팅방 번호를 입력하세요.")

document.querySelector("#username").innerHTML = username;

// const eventSource = new EventSource("http://localhost:8080/sender/aaa/receiver/bbb");
const eventSource = new EventSource(`http://localhost:8080/chat/roomNum/${roomNum}`);

eventSource.onmessage = (event)=>{
    const data = JSON.parse(event.data);
    if(data.sender === username ){
        // 파란박스
        initSendMsg(data);
    }else{
        // 회색박스
        initReceiveMsg(data);
    }
}

function initSendMsg(data){
    let chatBox = document.querySelector("#chat-box");

    let chatOutgoingBox = document.createElement("div");
    chatOutgoingBox.className = "outgoing_msg";

    chatOutgoingBox.innerHTML = getSendMsgBox(data);
    chatBox.append(chatOutgoingBox);
    document.documentElement.scrollTop = document.body.scrollHeight;
}

function initReceiveMsg(data){
    let chatBox = document.querySelector("#chat-box");

    let chatInComingBox = document.createElement("div");
    chatInComingBox.className = "incoming_msg";

    chatInComingBox.innerHTML = getReceiveMsgBox(data);
    chatBox.append(chatInComingBox);

}

async function addMsg(){
    let msgInput = document.querySelector("#chat-outgoing-msg");

    let chat = {
        sender : username,
        roomNum: roomNum,
        msg: msgInput.value
    };

    fetch("http://localhost:8080/chat",{
        method: "post",
        body: JSON.stringify(chat), // JS -> json
        headers:{
            "Content-Type":"application/json; charset=utf-8"
        }
    });

    msgInput.value = "";
}

function getSendMsgBox(data){
    let time = data.createdAt.toString().slice(0,10)+"  "+data.createdAt.toString().slice(11,19);
    return `<div class="sent_msg"><p>${data.msg}</p><span class="time_date"> ${time} </span></div>`;
}

function getReceiveMsgBox(data){
    let time = data.createdAt.toString().slice(0,10)+"  "+data.createdAt.toString().slice(11,19);
    return `<div class="received_withd_msg"><p>${data.msg}</p><span class="time_date"> ${time} / ${data.sender}</span></div>`;
}

document.querySelector("#chat-send").addEventListener("click",()=>{
    addMsg();
})

document.querySelector("#chat-outgoing-msg").addEventListener("keydown",(e)=>{
    if(e.keyCode === 13 ){
        addMsg();
    }
})